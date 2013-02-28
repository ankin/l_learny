define([ 'jquery', 'backbone', 'util', 'text!word/words.html', 'word/wordCol' ], function($, Backbone, util, wordTpl,
        WordCol) {

    var wordColView = Backbone.View.extend({
        className : 'record-component',
        _template : _.template(wordTpl),

        // events : {
        // 'click #calendar-open-link' : 'toggleCalendar'
        // },

        initialize : function() {
            this.rendered = $.Deferred();
            this.model = new WordCol(this.options.data);
            this.render();
        },

        render : function() {
            var self = this;
            var wordsJson = self.model.toJSON();
            self.$el.html(self._template({
                words : wordsJson
            }));
            self.rendered.resolve('rendered');

            return this;
        }
    });
    return wordColView;
});