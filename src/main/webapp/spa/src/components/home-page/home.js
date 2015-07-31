define(["knockout", 'jquery', "text!./home.html"], function(ko, $, homeTemplate) {

    function HomeViewModel(params) {
        this.route = params.route;
        this.message = ko.observable('Welcome to kostart!');
    }

    HomeViewModel.prototype.doSomething = function() {
        this.message('You invoked doSomething() on the viewmodel.');
    };

    return {
        viewModel: HomeViewModel,
        template: homeTemplate
    };

});
