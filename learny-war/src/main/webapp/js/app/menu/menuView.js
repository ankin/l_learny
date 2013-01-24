define([ 'jquery', 'backbone', 'text!menu/menu.html' ], function($, Backbone, menuTpl) {

    var recordView = Backbone.View.extend({
	_template : _.template(menuTpl),

	render : function() {
	    this.$el.html(this._template());
	    return this;
	}
    });
    return recordView;
});