// require.js looks for the following global when initializing
var require = {
    baseUrl: '.',
    paths: {
        "text": "bower_modules/requirejs-text/text",
        "underscore": "bower_modules/underscore/underscore",
        "jquery": "bower_modules/jquery/dist/jquery",
        "bootstrap": "bower_modules/components-bootstrap/js/bootstrap.min",
        "knockout": "bower_modules/knockout/dist/knockout.debug",
        "knockout-mapping": "bower_modules/bower-knockout-mapping/dist/knockout.mapping",
        "knockout-projections": "bower_modules/knockout-projections/dist/knockout-projections",
        "knockout-validation": "bower_modules/knockout-validation/dist/knockout.validation",
        "knockout-postbox": "bower_modules/knockout-postbox/build/knockout-postbox",
        "signals": "bower_modules/js-signals/dist/signals.min",
        "hasher": "bower_modules/hasher/dist/js/hasher.min",
        "crossroads": "bower_modules/crossroads/dist/crossroads.min",
        "datatables": "bower_modules/DataTables/media/js/jquery.dataTables",
        "jquery-confirm": "bower_modules/jquery-confirm2/js/jquery-confirm",
        "owlcarousel": "bower_modules/owl-carousel2/dist/owl.carousel"
    },
    shim: {
        "bootstrap": {
            deps: ["jquery"]
        },
        "datatables": {
            deps: ["jquery"]
        },
        "jquery-confirm": {
            deps: ["jquery"]
        },
        "owlcarousel": {
            deps: ["jquery"]
        }
    }
};

