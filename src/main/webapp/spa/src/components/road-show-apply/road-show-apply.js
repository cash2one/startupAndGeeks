define([
    'knockout',
    'text!./road-show-apply.html'
], function(ko, templateMarkup) {

    function RoadShowApply(params) {

        var _self = this;

        _self.route = params.route;

        _self.basicInfoToggle = ko.observable(true);
        _self.otherInfoToggle = ko.observable(false);

        _self.collapseBasic = function(data, event) {
            _self.basicInfoToggle(!_self.basicInfoToggle());
        };

        _self.collapseOther = function(data, event) {
            _self.otherInfoToggle(!_self.otherInfoToggle());
        };

        // This runs when the component is torn down. Put here any logic necessary to clean up,
        // for example cancelling setTimeouts or disposing Knockout subscriptions/computeds.
        _self.dispose = function() {

        };
    }

    return {
        viewModel: RoadShowApply,
        template: templateMarkup
    };

});

