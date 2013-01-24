requirejs.config({
    // By default load any module IDs from js/lib
    // baseUrl: 'js/lib',
    // except, if the module ID starts with "app",
    // load it from the js/app directory. paths
    // config is relative to the baseUrl, and
    // never includes a ".js" extension since
    // the paths config could be for a directory.

    paths : {
	jquery : "../lib/jquery-1.9.0",
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

// Start the main app logic.
requirejs([ 'jquery', 'backbone', 'util/util', 'record/recordView' ], function($, Backbone, util, RecordView) {

    util.initLanguage('en_EN');

    var App = Backbone.View.extend({
	initialize : function() {
	    this.render();
	},

	render : function() {
	    var recordView = new RecordView();
	    this.$el.html(recordView.render().$el);

	    return this;
	}
    });

    var app = new App({
	el : $('#ajax-content')
    });

});