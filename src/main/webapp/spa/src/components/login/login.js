define([
    'jquery',
    'knockout',
    'common/Api',
    'common/Validation',
    'common/Aes',
    'hasher',
    'text!./login.html',
    'common/Cookie'
], function($, ko, Api, Validation, Aes, hasher, loginTmpl) {

    var secretKey = '08090A0B0D0E0F10121314151718191A1C1D1E1F21222324262728292B2C2D2E';
    var keySize = 256;

    function LoginViewModel(params) {

        var _self = this;

        _self.remeberDays = 7;
        _self.encryptCode = 'whatever';

        var storeU = $.cookie('username') && Aes.Ctr.decrypt($.cookie('username'), secretKey, keySize) || '';
        var storeP = $.cookie('password') && Aes.Ctr.decrypt($.cookie('password'), secretKey, keySize) || '';
        var storeI = $.cookie('isRemeberMe') || false;

        _self.username = ko.observable(storeU).extend({
            required: true,
            pattern: {
                params: Validation.regex.username,
                message: Validation.msg.username
            }
        });
        _self.password = ko.observable(storeP).extend({
            required: true,
            pattern: {
                params: Validation.regex.password,
                message: Validation.msg.password
            }
        });
        _self.isRemeberMe = ko.observable(storeI);

        _self.errors = ko.validation.group(_self);
        _self.message = ko.observable();

    };

    LoginViewModel.prototype.submit = function() {

        var _self = this;

        if (_self.errors().length === 0) {

            if (_self.isRemeberMe()) {
                _self.writeCookie();
            } else {
                _self.clearCookie();
            }
            _self.signin();
        } else {

            _self.errors.showAllMessages();
        }
    };

    LoginViewModel.prototype.writeCookie = function() {

        var _self = this;

        var encryptU = Aes.Ctr.encrypt(_self.username(), secretKey, keySize);
        var encryptP = Aes.Ctr.encrypt(_self.password(), secretKey, keySize);

        $.cookie('username', encryptU, {
            expries: _self.remeberDays
        });
        $.cookie('password', encryptP, {
            expries: _self.remeberDays
        });
        $.cookie('isRemeberMe', true, {
            expries: _self.remeberDays
        });
    };

    LoginViewModel.prototype.clearCookie = function() {

        var _self = this;

        $.removeCookie('username');
        $.removeCookie('password');
        $.removeCookie('isRemeberMe');
    };

    LoginViewModel.prototype.signin = function() {

        var _self = this;

        $.ajax({
            type: 'POST',
            crossDomain: true,
            cache: false,
            dataType: "json",
            url: Api.url.login,
            data: {
                name: _self.username(),
                pwd: _self.password()
            },
            success: function(data, textStatus, jqXHR) {

                _self.message("");

                if (Api.code.loginSuccess == data.code) {

                    sessionStorage.setItem('token', jqXHR.getResponseHeader('jwt-token'));
                    // location.href = '#home';
                    hasher.setHash('home');
                } else {
                    _self.message(data.msg);
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                _self.message(textStatus);
            }
        });
    };

    return {
        viewModel: LoginViewModel,
        template: loginTmpl
    };
});
