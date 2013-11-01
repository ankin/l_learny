define([ 'jquery', 'backbone', 'util', 'text!history/history_item.html' ], function($, Backbone, util, historyItemTpl) {

    var historyView = Backbone.View.extend({
        tagName : 'tr',
        _template : _.template(historyItemTpl),

        events : {
            'click td' : 'loadRecord'
        },

        initialize : function() {
            this.rendered = $.Deferred();
        },

        render : function() {
            var self = this;
            self.$el.html(self._template(self.model.toJSON()));
            return this;
        },

        loadRecord : function() {
            Backbone.history.navigate('record/'+ this.model.toJSON().recordUuid, {trigger: true});
        }
    });
    return historyView;
});