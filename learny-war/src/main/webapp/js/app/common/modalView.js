define([ 'jquery', 'backbone'], function($, Backbone) {

    var ModalView = Backbone.View.extend({

        className : 'modal',

        events : {
            'click' : 'stop'
        },

        stop : function(e) {
            e.stopPropagation();
        },

        initialize : function(options) {
            var self = this;
            if (options) {
                if (options.view) {
                    this.view = options.view;
                    this.view.on('HideModalEvent', this.hideModal, this);
                }
            }
            this.$bg = $('#modal-container');
            this.$bg.on('click', function(e) {
                if (self.$bg[0] === e.target)
                    self.hideModal();
            });

            $('body').on('pageChanging', function() {
                self.hideModal();
            });

        },

        showModal : function() {
            // if view supports deferred 'rendered', then show busy animation and delay
            // rendering of the modal frame.
            if (this.view.hasOwnProperty('rendered')) {
                var self = this, $body = $('body');
                $body.trigger('loading');
                this.view.rendered.done(function() {
                    $body.trigger('removeLoading');
                    self.render(self.view.el); // render modal view with prerendered child view
                });
                self.view.render();
            } else {
                this.render();
            }
            this.$bg.addClass("show");
        },

        hideModal : function() {
            this.view.unbind();
            this.view.remove();
            this.unbind();
            this.remove(); // remove the modal from the DOM
            this.$bg.removeClass("show");
            // this.close();
        },

        render : function(viewEl) {
            if (viewEl === undefined) {
                viewEl = this.view.render().el;
            }
            this.$bg.html(this.$el.html(viewEl));
        }

    });

    return ModalView;

});
