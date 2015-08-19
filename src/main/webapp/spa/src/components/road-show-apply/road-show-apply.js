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

        $('.file-uploader').find('.upload').on('click', function() {
            $(this).closest('.file-uploader').find('.file').click();
        });

        $('.file-uploader').find('.file').on('change', function() {

            var $this = $(this);
            var fd = new FormData();

            $this.siblings('.name').val($this.val());

            fd.append($this.attr('name'), $this[0].files[0]);

            $.ajax({
                url: $this.data('url'),
                type: "POST",
                data: fd,
                processData: false, // tell jQuery not to process the data
                contentType: false // tell jQuery not to set contentType
            });
        });

    }

    return {
        viewModel: RoadShowApply,
        template: templateMarkup
    };

});

