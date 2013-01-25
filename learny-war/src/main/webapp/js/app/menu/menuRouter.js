define([ 'jquery', 'backbone', 'menu/menuView', 'record/recordView' ], function($, Backbone, MenuView, RecordView) {

    var menuRouter = Backbone.Router.extend({

	routes : {
	    "record" : "loadRecord",
	    "about" : "loadAbout",
	    "news" : "loadNews",
	    "settings" : "loadSettings"
	},

	initialize : function() {
	    this.loadMenu();
	},

	loadMenu : function() {
	    this.header = $('#mainMenu').prepend(new MenuView().render().el);
	},

	loadRecord : function() {
	    $('#ajax-content').html(new RecordView().render().el);
	},

	loadAbout : function() {
	    $('#ajax-content').html('loadAbout');
	},

	loadNews : function() {
	    $('#ajax-content').html('loadNews');
	},

	loadSettings : function() {
	    $('#ajax-content').html('loadSettings');
	},

    });

    return menuRouter;
});