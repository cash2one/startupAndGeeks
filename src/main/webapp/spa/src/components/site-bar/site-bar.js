define(['knockout', 'text!./site-bar.html'], function(ko, templateMarkup) {

    function SiteBar(params) {
        this.message = ko.observable('Hello from the site-bar component!');
    }

    // This runs when the component is torn down. Put here any logic necessary to clean up,
    // for example cancelling setTimeouts or disposing Knockout subscriptions/computeds.
    SiteBar.prototype.dispose = function() {};

    return {
        viewModel: SiteBar,
        template: templateMarkup
    };

});

