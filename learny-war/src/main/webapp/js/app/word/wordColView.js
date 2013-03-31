define([ 'jquery', 'backbone', 'util', 'text!word/words.html', 'text!word/words_edit.html',
        'text!word/words_save.html', 'text!word/words_add.html', 'word/wordCol', 'word/wordModel', 'word/wordView' ],
        function($, Backbone, util, wordsTpl, wordsEditCtrlsTpl, wordsSaveCtrlsTpl, addWordCtrlsTpl, WordCol,
                WordModel, WordView) {

            var wordColView = Backbone.View.extend({
                className : 'record-component',
                _template : _.template(wordsTpl),

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
                        var wordView = new WordView({
                            model : wordModel,
                            editMode : self.editMode
                        });
                        self.$el.find("tbody").prepend(wordView.render().el);
                    });

                    self.rendered.resolve('rendered');

                    return this;
                },

                triggerAdd : function() {
                    var self = this;

                    var newWordModel = new WordModel();

                    self.model.add(newWordModel);

                    var wordView = new WordView({
                        model : newWordModel,
                        editMode : true
                    });
                    self.$el.find("tbody").prepend(wordView.render().el);

                    return false;
                },

                triggerEdit : function() {
                    var self = this;

                    self.editMode = true;

                    self.render();

                    self.$el.find('#words-controls').html(_.template(wordsSaveCtrlsTpl));
                    self.$el.find('#words-controls').append(_.template(addWordCtrlsTpl));
                    return false;
                },

                triggerCancelEdit : function() {
                    var self = this;
                    self.newWordCol = null;
                    self.off();

                    self.editMode = false;

                    self.render();
                    return false;
                },

                saveOrUpdateWords : function() {
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
                }

            });
            return wordColView;
        });