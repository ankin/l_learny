define([ 'jquery', 'backbone' ], function($, Backbone) {

    var dayView = Backbone.View
            .extend({
                tagName : 'td',
                className : 'dateCell',
                initialize : function() {
                    this.rendered = $.Deferred();
                    this.render();
                },

                events : {
                    'click a' : 'dateClicked'
                },

                render : function() {
                    var currentDate = new Date(this.options.date.getTime());

                    if (this.options.isLink && !this.options.isAnotherMonth) {
                        this.$el.html('<a href="#">' + currentDate.getDate() + '</a>');
                        this.$el.addClass('record');
                    } else {
                        this.$el.html(currentDate.getDate());
                    }

                    if (this.options.isAnotherMonth) {
                        this.$el.addClass('another-month');
                    }
                    var today = new Date();
                    if (currentDate.getDate() == today.getDate() && currentDate.getMonth() == today.getMonth()
                            && currentDate.getFullYear() == today.getFullYear()) {
                        this.$el.addClass('today');
                    }

                  
                    return this;
                },

                dateClicked : function(e) {
                    e.preventDefault();
                    alert(this.options.date.toString());
                }
            });
    return dayView;
});