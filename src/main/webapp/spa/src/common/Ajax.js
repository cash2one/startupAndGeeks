define(['jquery', './Api', "hasher"], function($, Api, hasher) {

    return function(options) {

        var defaultSetting = {
            type: 'POST',
            crossDomain: true,
            cache: false,
            dataType: "json",
            // beforeSend: function(xmlHttpRequest) {
            //     xmlHttpRequest['jwt-token'] = sessionStorage.getItem('token');
            // },
            headers: {
                'jwt-token': sessionStorage.getItem('token')
            }
        };

        var setting = $.extend({}, defaultSetting, {
            url: options.url || '/',
            data: $.extend(options.data, {
                'jwt-token': sessionStorage.getItem('token')
            }) || {},
            success: function(data, textStatus, jqXHR) {

                if (typeof data === 'string') {
                    try {
                        data = $.parseJSON(data);
                    } catch (error) {
                        alert('数据解析错误： ' + error);
                    }
                }

                //--login in auth?
                if (data.code == Api.code.tokenFail) {
                    // location.href = '/';
                    hasher.setHash('');
                }

                typeof options.success === 'function' && options.success(data);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert('请求出错： ' + textStatus);
            }
        });

        $.ajax(setting);
    };
});

