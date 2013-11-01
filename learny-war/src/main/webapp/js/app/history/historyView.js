define([ 'jquery', 'backbone', 'util', 'text!history/history.html', 'record/recordHistoryCol',
        'history/historyItemView' ], function($, Backbone, util, historyTpl, RecordHistoryCol, HistoryItemView) {

    var historyView = Backbone.View.extend({
        _template : _.template(historyTpl),
        collection : new RecordHistoryCol(),

        events : {
            'click a#remove-word' : 'removeWord'
        },

        initialize : function() {
            this.rendered = $.Deferred();
        },

        render : function() {
            var self = this;
            self.$el.html(self._template());
            self.collection.fetch({
                success : function() {
                    _.forEach(self.collection.models, function(recordInfo, index) {
                        var historyItemView = new HistoryItemView();
                        historyItemView.model = recordInfo;
                        self.$el.find('tbody').append(historyItemView.render().el);
                    });
                    self.rendered.resolve('rendered');
                }
            });

            return this;
        }
    });
    return historyView;
});