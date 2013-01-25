define([ 'jquery', 'backbone', 'text!menu/menu.html' ], function($, Backbone, menuTpl) {

    var menuView = Backbone.View.extend({
	tagName : 'ul',
	className : 'nav nav-tabs span2',
	
	events : {
	    'click li' : 'updateActiveStyle'
	},

	_template : _.template(menuTpl),

	render : function() {
	    this.$el.html(this._template());
	    return this;
	},
	
	updateActiveStyle : function(e){
	    _.forEach($(this.el).find('li'), function(liEl){
		if($(liEl).hasClass('active')) {
		    $(liEl).removeClass( 'active' );
		}
	    });
	    $(e.currentTarget).addClass( 'active' );
	}
    });
    return menuView;
});