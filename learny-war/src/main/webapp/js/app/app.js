requirejs.config({

    paths : {
        jquery : "../lib/jquery-1.9.1",
        lodash : "../lib/lodash-1.0.0-rc.3",
        backbone : "../lib/backbone-0.9.10",
        jqueryDateFormat : "../lib/jquery.dateFormat-1.0",
        jqueryForm : "../lib/jquery.form-3.25.0",
        jqueryi18n : "../lib/jquery.i18n.properties-min-1.0.9",
        text : "../lib/text-2.0.4"
    },

    shim : {
        'jqueryDateFormat' : [ 'jquery' ],
        'jqueryForm' : [ 'jquery' ],
        'jqueryi18n' : [ 'jquery' ],
        'backbone' : {
            deps : [ 'lodash', 'jquery' ],
            exports : "Backbone"
        },
    }
});

requirejs([ 'jquery', 'backbone', 'util/util', 'menu/menuRouter' ], function($, Backbone, util, MenuRouter) {

    $('#ajax-content').on('showSpinner', function() {
        if (!$('#ajax-content').hasClass('spinner')) {
            $('#ajax-content').addClass('spinner');
        }
    });
    $('#ajax-content').on('hideSpinner', function() {
        $('#ajax-content').removeClass('spinner');
    });

    util.initLanguage('en_EN');

    this.router = new MenuRouter();
    Backbone.history.start();

});