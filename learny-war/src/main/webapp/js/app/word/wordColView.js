define([ 'jquery', 'backbone', 'util', 'text!word/words.html', 'word/wordCol', 'word/wordModel', 'word/wordView',
        'word/addWordModalView' , 'text!word/empty_words.html'],
        function($, Backbone, util, wordsTpl, WordCol, WordModel, WordView, AddWordModalView, emptyWordsTpl) {

            var wordColView = Backbone.View.extend({
                className : 'record-component',

                events : {
                    'click #add-word' : 'triggerAdd',
                    'click #initial-add' : 'triggerInitialAdd'
                },

                initialize : function() {
                    var self = this;
                    this.rendered = $.Deferred();
                    _.forEach(this.options.data, function(model) {
                        model.genderLocalized = $.i18n.prop(model.gender);
                        model.typeLocalized = $.i18n.prop(model.type);
                    });
                    this.model = new WordCol(this.options.data, {
                        recordUuid : self.options.recordUuid
                    });
                    this.render();
                },

                render : function() {
                    var self = this;

                    if (self.model.isEmpty()) {
                        self.$el.html(_.template(emptyWordsTpl));
                    } else {
                        self.$el.html(_.template(wordsTpl));

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
                    }
                    self.rendered.resolve('rendered');

                    return this;
                },

                triggerAdd : function() {
                    var self = this;

                    var newWordModel = new WordModel(null, {
                        urlRoot : 'services/record/' + self.model.recordUuid + '/word/'
                    });
                    self.model.add(newWordModel);
                    newWordModel.once('sync', function() {
                        var wordView = new WordView({
                            model : newWordModel
                        });
                        this.$el.find("tbody").prepend(wordView.render().el);
                    }, this);
                    newWordModel.on('destroy', function() {
                        self.model.remove(newWordModel);
                        self.render();
                    }, self);

                    self.modal = new AddWordModalView({
                        model : newWordModel
                    });

                    self.modal.show();

                    return false;
                },
                
                triggerInitialAdd : function() {
                    var self = this;

                    var newWordModel = new WordModel(null, {
                        urlRoot : 'services/record/' + self.model.recordUuid + '/word/'
                    });
                    self.model.add(newWordModel);
                    newWordModel.once('sync', function() {
                        var wordView = new WordView({
                            model : newWordModel
                        });
                        self.$el.html(_.template(wordsTpl));
                        this.$el.find("tbody").prepend(wordView.render().el);
                    }, this);
                    newWordModel.on('destroy', function() {
                        self.model.remove(newWordModel);
                        self.render();
                    }, self);

                    self.modal = new AddWordModalView({
                        model : newWordModel
                    });

                    self.modal.show();

                    return false;
                }

            });
            return wordColView;
        });