define([
    'jquery',
    'knockout',
    './router',
    'bootstrap',
    'knockout-mapping',
    'knockout-projections',
    'knockout-postbox',
    'common/Settings',
    'common/Util',
    'jquery-confirm'
], function($, ko, router) {

    // ... or for template-only components, you can just point to a .html file directly:

    ko.components.register('about-page', {
        template: {
            require: 'text!components/about-page/about.html'
        }
    });

    // Components can be packaged as AMD modules, such as the following:

    ko.components.register('site-search', {
        require: 'components/site-search/site-search'
    });
    ko.components.register('site-header', {
        require: 'components/site-header/site-header'
    });
    ko.components.register('site-footer', {
        require: 'components/site-footer/site-footer'
    });
    ko.components.register('nav-bar', {
        require: 'components/nav-bar/nav-bar'
    });
    ko.components.register('login', {
        require: 'components/login/login'
    });
    ko.components.register('home-page', {
        require: 'components/home-page/home'
    });
    ko.components.register('register', {
        require: 'components/register/register'
    });

    ko.components.register('site-bar', {
        require: 'components/site-bar/site-bar'
    });

    ko.components.register('image-silder', {
        require: 'components/image-silder/image-silder'
    });

    ko.components.register('road-show-apply', {
        require: 'components/road-show-apply/road-show-apply'
    });

    // [Scaffolded component registrations will be inserted here. To retain this feature, don't remove this comment.]

    // Start the application
    ko.applyBindings({
        route: router.currentRoute
    });

});

