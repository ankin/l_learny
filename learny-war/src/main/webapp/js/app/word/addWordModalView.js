define(
        [ 'jquery', 'common/modalView', 'text!word/add_word.html', 'typeahead', 'word/wordModel' ],
        function($, ModalView, addWordTpl, typeahead, WordModel) {

            var ModalInputView = Backbone.View
                    .extend({

                        className : 'modal-input',

                        events : {
                            "click .submit-btn" : "submitPopup",
                            "click .cancel-btn" : "closePopup",
                            "click .modal-close" : "closePopup",
                            "click" : "nop",
                            "keypress #newWord" : "handleKeyPress"
                        },

                        initialize : function(options) {

                        },

                        render : function() {
                            var self = this;
                            self._template = _.template(addWordTpl);
                            self.$el.append(self._template({
                                word : self.model.toJSON()
                            }));

                            self.renderAutocomplete();

                            return this;
                        },

                        renderAutocomplete : function() {
                            var self = this;
                            var engine = {
                                compile : function(template) {
                                    var compiled = _.template(template);

                                    return {
                                        render : function(context) {
                                            return compiled(context);
                                        }
                                    };
                                }
                            };

                            self.$el
                                    .find('#newWord')
                                    .typeahead(
                                            [ {
                                                name : 'words',
                                                remote : {
                                                    url : 'services/search?query=%QUERY',
                                                    filter : function(response) {
                                                        var datums = [];
                                                        _.forEach(response, function(word) {
                                                            var description = '';
                                                            _.forEach(word.translations, function(translation) {
                                                                description += ' ' + translation.value;
                                                            });
                                                            datums.push({
                                                                value : word.value,
                                                                description : description,
                                                                type : word.type,
                                                                uuid : word.uuid
                                                            });
                                                        });
                                                        return datums;
                                                    }
                                                },
                                                engine : engine,
                                                template : '<p class="word-type"><%=type%></p><p class="word-value"><%=value%></p><p class="word-description"><%=description%></p>'
                                            } ]);

                            self.$el.find('#newWord').on("typeahead:selected", function(e, datum) {
                                self.selectedDatum = datum;
                            });
                        },

                        submitPopup : function() {

                        },

                        closePopup : function() {
                            // Fire HideModalEvent to tell the ModalView class to hide the modal
                            this.trigger('HideModalEvent');
                        },

                        nop : function(e) {
                            e.stopImmediatePropagation();
                        },

                        show : function() {
                            return new ModalView({
                                view : this
                            }).showModal();
                        },

                        handleKeyPress : function(e) {
                            if (e.keyCode == 13) {
                                // TODO add spinner
                                var target = $(e.currentTarget);
                                this.model.set('translations', null); // don't send translations
                                var value = target.val();
                                if (this.selectedDatum.value == value.trim()) {
                                    this.model.set('uuid', this.selectedDatum.uuid);
                                }
                                this.model.set('value', value);
                                this.model.save();

                                this.closePopup();
                            }

                        }

                    });

            return ModalInputView;
        });
