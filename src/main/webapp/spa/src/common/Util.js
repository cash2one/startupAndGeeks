define(['knockout', 'jquery'], function(ko, $) {

    ko.utils.addJsTemplate = function(templateName, templateMarkup) {
        if ($('#' + templateName).length === 0) {
            $('body').append("<script type='text/html' id='" + templateName + "'>" + templateMarkup + "<" + "/script>");
        }
    }

    // Helper function so we know what has changed
    // http://stackoverflow.com/questions/12166982
    ko.observableArray.fn.subscribeArrayChanged = function(addCallback, deleteCallback) {
        var previousValue = undefined;
        this.subscribe(function(_previousValue) {
            previousValue = _previousValue.slice(0);
        }, undefined, 'beforeChange');
        this.subscribe(function(latestValue) {
            var editScript = ko.utils.compareArrays(previousValue, latestValue);
            for (var i = 0, j = editScript.length; i < j; i++) {
                switch (editScript[i].status) {
                    case "retained":
                        break;
                    case "deleted":
                        if (deleteCallback)
                            deleteCallback(editScript[i].value);
                        break;
                    case "added":
                        if (addCallback)
                            addCallback(editScript[i].value);
                        break;
                }
            }
            previousValue = undefined;
        });
    };

    // Here's a custom Knockout binding that makes elements shown/hidden via jQuery's slideDown()/slideUp() methods
    // Could be stored in a separate utility library
    ko.bindingHandlers.slideVisible = {
        init: function(element, valueAccessor) {
            // Initially set the element to be instantly visible/hidden depending on the value
            var value = valueAccessor();
            $(element).toggle(ko.unwrap(value)); // Use "unwrapObservable" so we can handle values that may or may not be observable
        },
        update: function(element, valueAccessor) {
            // Whenever the value subsequently changes, slowly slide the element up or down
            var value = valueAccessor();
            ko.unwrap(value) ? $(element).slideDown() : $(element).slideUp();
        }
    };

    //jquery
    $.fn.extend({
        serializeObject: function() {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function() {
                if (o[this.name] !== undefined) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        }
    });
});

