define([ 'jquery', 'common/modalView', 'text!word/add_word.html' ], function($, ModalView, addWordTpl) {

    var ModalInputView = Backbone.View.extend({

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
            return this;
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
                this.model.set('original', target.val());
                this.model.save();
                this.closePopup();
            }

        }

    });

    return ModalInputView;
});
