define([ 'jquery', 'backbone', 'util', 'text!word/words.html', 'word/wordCol', 'word/wordModel', 'word/wordView',
        'word/addWordModalView' ],
        function($, Backbone, util, wordsTpl, WordCol, WordModel, WordView, AddWordModalView) {

            var wordColView = Backbone.View.extend({
                className : 'record-component',
                _template : _.template(wordsTpl),

                events : {
                    'click #add-word' : 'triggerAdd'
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

                    self.model.each(function(wordModel) {
                        var wordView = new WordView({
                            model : wordModel
                        });
                        wordModel.on('destroy', function() {
                            self.model.remove(wordModel);
                            self.render();
                        }, self);
                        self.$el.find("tbody").prepend(wordView.render().el);
                    });

                    self.rendered.resolve('rendered');

                    return this;
                },

                triggerAdd : function() {
                    var self = this;

                    var newWordModel = new WordModel(null, {
                        url : 'services/record/' + self.model.recordUuid + '/word/'
                    });
                    self.model.add(newWordModel);
                    newWordModel.on('sync', function() {
                        var wordView = new WordView({
                            model : newWordModel
                        });
                        this.$el.find("tbody").prepend(wordView.render().el);
                    }, this);

                    self.modal = new AddWordModalView({
                        model : newWordModel
                    });

                    self.modal.show();

                    return false;
                }

            });
            return wordColView;
        });