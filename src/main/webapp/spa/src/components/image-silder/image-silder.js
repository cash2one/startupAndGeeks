define([
    'knockout',
    "jquery",
    'text!./image-silder.html',
    "owlcarousel"
], function(ko, $, templateMarkup) {

    var images = [
        "imgs/3.jpg",
        "imgs/4.jpg",
        "imgs/5.jpg",
        "imgs/6.jpg",
        "imgs/7.jpg",
        "imgs/8.jpg",
    ];

    function ImageSilder(params) {

        var _self = this;

        _self.imageList = ko.observableArray(images);
    }

    ImageSilder.prototype.initOwl = function() {

        $('.owl-carousel').owlCarousel({
            items: 1,
            animateOut: 'fadeOut',
            navText: ['<span class="glyphicon glyphicon-menu-left"></span>', '<span class="glyphicon glyphicon-menu-right"></span>'],
            dots: true,
            loop: true,
            margin: 10,
            nav: true,
            autoplay: true,
            autoplayTimeout: 1000,
            autoplayHoverPause: true
        });
    };
    // This runs when the component is torn down. Put here any logic necessary to clean up,
    // for example cancelling setTimeouts or disposing Knockout subscriptions/computeds.
    ImageSilder.prototype.dispose = function() {
        images = null;
        $('.owl-carousel').data('owl.carousel').destory();
    };

    return {
        viewModel: ImageSilder,
        template: templateMarkup,
        // synchronous: true
    };

});

