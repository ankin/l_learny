define([ 'jquery', 'backbone', 'util', 'text!history/history.html', 'record/recordHistoryCol',
        'history/historyItemView', 'history/addRecordModalView' ], function($, Backbone, util, historyTpl,
        RecordHistoryCol, HistoryItemView, AddRecordModalView) {

    var historyView = Backbone.View.extend({
        className : 'span12',
        _template : _.template(historyTpl),
        collection : new RecordHistoryCol(),

        events : {
            'click a#remove-history' : 'removeHistory',
            'click a#add-record' : 'addRecord'
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
        },

        addRecord : function() {
            self.modal = new AddRecordModalView();
            self.modal.show();
        }
    });
    return historyView;
});