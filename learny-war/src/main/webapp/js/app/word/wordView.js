define([ 'jquery', 'backbone', 'util', 'text!word/word.html', 'text!word/word_edit.html', 'word/wordModel' ], function(
        $, Backbone, util, wordTpl, wordEditTpl, WordModel) {

    var wordView = Backbone.View.extend({
        tagName : 'tr',
        _template : _.template(wordTpl),
        model : WordModel,

        events : {
            'change input' : 'originalChange'
        },
        
        initialize : function() {
            this.rendered = $.Deferred();
        },

        render : function() {
            var self = this;
            if (this.options.editMode) {
                this._template = _.template(wordEditTpl);
            } else {
                this._template = _.template(wordTpl);
            }
            var wordJson = self.model.toJSON();
            self.$el.html(self._template({
                word : wordJson
            }));
            self.rendered.resolve('rendered');

            return this;
        },
        
        originalChange : function(e) {
            var target = $(e.currentTarget);
            this.model.set('original', target.val());
        }

    });
    return wordView;
});