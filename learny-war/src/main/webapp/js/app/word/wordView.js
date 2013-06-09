define([ 'jquery', 'backbone', 'util', 'text!word/word.html', 'word/wordModel', 'word/addWordModalView' ], function($,
        Backbone, util, wordTpl, WordModel, AddWordModalView) {

    var wordView = Backbone.View.extend({
        tagName : 'tr',
        _template : _.template(wordTpl),
        model : WordModel,

        events : {
            'click a#remove-word' : 'removeWord'
        },

        initialize : function() {
            this.rendered = $.Deferred();
        },

        render : function() {
            var self = this;
            self.model.on('sync', function() {
                this.render();
            }, this);
            this._template = _.template(wordTpl);
            var wordJson = self.model.toJSON();
            self.$el.html(self._template({
                word : wordJson
            }));
            self.rendered.resolve('rendered');

            return this;
        },

        removeWord : function() {
            // TODO add spinner
            var self = this;
            this.model.destroy(function() {
                $(self.el).removeData().unbind();
                self.remove();
            });

            return false;
        }
    });
    return wordView;
});