define([ 'jquery', 'common/modalView', 'text!history/add_record.html', 'record/recordModel' ], function($, ModalView,
        addRecordTpl, RecordModel) {

    var ModalInputView = Backbone.View.extend({

        className : 'modal-input',

        events : {
            "click .submit-btn" : "submitPopup",
            "click .cancel-btn" : "closePopup",
            "click .modal-close" : "closePopup",
            "click" : "nop",
            "keypress #newRecord" : "handleKeyPress"
        },

        initialize : function(options) {

        },

        render : function() {
            var self = this;
            self._template = _.template(addRecordTpl);
            self.$el.append(self._template());

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
                var recordModel = new RecordModel();
                var target = $(e.currentTarget);
                var value = target.val();
                recordModel.set('name', value);
                recordModel.save({success: function(){
                  alert('test');  
                }});
                this.closePopup();
            }

        }

    });

    return ModalInputView;
});
