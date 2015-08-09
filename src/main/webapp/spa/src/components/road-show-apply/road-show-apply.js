define([
    'knockout',
    'text!./road-show-apply.html'
], function(ko, templateMarkup) {

    function RoadShowApply(params) {
    	this.route = params.route;
    }

    // This runs when the component is torn down. Put here any logic necessary to clean up,
    // for example cancelling setTimeouts or disposing Knockout subscriptions/computeds.
    RoadShowApply.prototype.dispose = function() {

    };

    return {
        viewModel: RoadShowApply,
        template: templateMarkup
    };

});

