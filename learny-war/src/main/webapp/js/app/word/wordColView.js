define([ 'jquery', 'backbone', 'util', 'text!word/words.html', 'text!word/words_edit.html',
        'text!word/words_save.html', 'word/wordCol', 'word/wordModel', 'word/wordView' ], function($, Backbone, util,
        wordsTpl, wordsEditCtrlsTpl, wordsSaveCtrlsTpl, WordCol, WordModel, WordView) {

    var wordColView = Backbone.View.extend({
        className : 'record-component',
        _template : _.template(wordsTpl),
        showSaveControls : false,
        showEditControls : true,

        events : {
            'click #edit-words' : 'triggerEdit',
            'click #cancel-edit-words' : 'triggerCancelEdit',
            'click #add-word' : 'triggerAdd',
            'click #save-words' : 'saveOrUpdateWords'
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

            if (!self.editMode) {
                self.$el.find('#words-controls').html(_.template(wordsEditCtrlsTpl));
            }

            self.model.each(function(wordModel) {
                self.$el.find("tbody").prepend(new WordView({
                    model : wordModel,
                    editMode : self.editMode
                }).render().el);
            });

            self.rendered.resolve('rendered');

            return this;
        },

        triggerAdd : function() {
            var self = this;

            var newWordModel = new WordModel();

            if (!self.newWordCol) {
                self.newWordCol = new WordCol(newWordModel, {
                    recordUuid : self.options.recordUuid
                });
            } else {
                self.newWordCol.add(newWordModel);
            }

            var wordView = new WordView({
                model : newWordModel,
                editMode : true
            });
            self.$el.find("tbody").prepend(wordView.render().el);

            self.$el.find('#words-controls').html(_.template(wordsSaveCtrlsTpl));

        },

        triggerEdit : function() {
            var self = this;

            self.editMode = true;

            self.render();

            self.$el.find('#words-controls').html(_.template(wordsSaveCtrlsTpl));
        },

        triggerCancelEdit : function() {
            var self = this;
            self.newWordCol = null;
            self.off();

            self.editMode = false;

            self.render();
        },

        saveOrUpdateWords : function() {
            if (this.editMode) {
                this.updateAll();
            } else {
                this.saveAllNew();
            }
        },

        updateAll : function() {
            var self = this;

            self.model.each(function(wordModel) {
                if (wordModel.isDeleted) {
                    self.model.remove(wordModel);
                }

                wordModel.set('translations', null); // don't send translations
            });

            Backbone.sync('update', this.model, {
                success : function(data, textResponse, jqXHR) {
                    self.triggerCancelEdit();
                    self.model.set(data);
                    self.render();
                }
            });
        },

        saveAllNew : function() {
            var self = this;

            self.newWordCol.each(function(wordModel) {
                if (wordModel.isDeleted) {
                    self.newWordCol.remove(wordModel);
                }

                wordModel.set('translations', null); // don't send translations back
            });

            Backbone.sync('create', this.newWordCol, {
                success : function(data, textResponse, jqXHR) {
                    self.triggerCancelEdit();
                    self.model.set(data);
                    self.render();
                }
            });
        }

    });
    return wordColView;
});