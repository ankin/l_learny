define([ 'jquery', 'backbone', 'util', 'text!word/words.html', 'text!word/words_edit.html',
        'text!word/words_save.html', 'word/wordCol', 'word/wordView' ], function($, Backbone, util, wordsTpl,
        wordsEditCtrlsTpl, wordsSaveCtrlsTpl, WordCol, WordView) {

    var wordColView = Backbone.View.extend({
        className : 'record-component',
        _template : _.template(wordsTpl),
        showSaveControls : false,
        showEditControls : true,

        events : {
            'click #edit-words' : 'toggleEdit',
            'click #cancel-edit-words' : 'toggleEdit',
            'click #save-words' : 'saveAll'
        },

        initialize : function() {
            var self = this;
            this.rendered = $.Deferred();
            this.model = new WordCol(this.options.data, {
                recordUuid : self.options.recordUuid
            });
            this.render();
        },

        render : function() {
            var self = this;

            self.$el.html(self._template());

            if (this.showEditControls) {
                self.$el.prepend(_.template(wordsEditCtrlsTpl));
            }

            if (this.showSaveControls) {
                self.$el.prepend(_.template(wordsSaveCtrlsTpl));
            }

            self.model.each(function(wordModel) {

                self.$el.find("tbody").append(new WordView({
                    model : wordModel,
                    editMode : self.showSaveControls
                }).render().el);

            });

            self.rendered.resolve('rendered');

            return this;
        },

        toggleEdit : function() {
            this.showEditControls = !this.showEditControls;
            this.showSaveControls = !this.showSaveControls;
            this.render();
        },

        saveAll : function() {
            var self = this;
            Backbone.sync('update', this.model, {
                success : function(data, textResponse, jqXHR) {
                    self.toggleEdit();
                    self.model.set(data);
                    self.render();
                }
            });
        }
        
    });
    return wordColView;
});