define([ 'jquery', 'backbone', 'menu/menuView', 'record/recordView' ], function($, Backbone, MenuView, RecordView) {

    var menuRouter = Backbone.Router.extend({

	routes : {
	    'record' : 'loadRecord',
	    'about' : 'loadAbout',
	    'news' : 'loadNews',
	    'settings' : 'loadSettings',
	    'contact' : 'loadContact'
	    
	},

	initialize : function() {
	    this.loadMenu();
	},

	loadMenu : function() {
	    this.header = $('#mainMenu').prepend(new MenuView().render().el);
	},

	loadRecord : function() {
	    this.updateActiveSelection();
	    $('#ajax-content').html(new RecordView().render().el);
	    if( !$('.id_record').hasClass('active')) {
		$('.id_record').addClass('active');
	    }
	},

	loadAbout : function() {
	    this.updateActiveSelection();
	    $('#ajax-content').html('loadAbout');
	    if( !$('.id_about').hasClass('active')) {
		$('.id_about').addClass('active');
	    }
	},

	loadNews : function() {
	    this.updateActiveSelection();
	    $('#ajax-content').html('loadNews');
	    if( !$('.id_news').hasClass('active')) {
		$('.id_news').addClass('active');
	    }
	},

	loadSettings : function() {
	    this.updateActiveSelection();
	    $('#ajax-content').html('loadSettings');
	    if( !$('.id_settings').hasClass('active')) {
		$('.id_settings').addClass('active');
	    }
	},
	
	loadContact : function() {
	    this.updateActiveSelection();
	    $('#ajax-content').html('loalContact');
	    if( !$('.id_contact').hasClass('active')) {
		$('.id_contact').addClass('active');
	    }
	},

	updateActiveSelection : function() {
	    _.forEach($('#mainMenu').find('li'), function(liEl){
		if($(liEl).hasClass('active')) {
		    $(liEl).removeClass( 'active' );
		}
	    });
	}

    });

    return menuRouter;
});