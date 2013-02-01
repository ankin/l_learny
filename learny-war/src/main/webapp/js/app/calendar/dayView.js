define([ 'jquery', 'backbone' ], function($, Backbone) {

    var dayView = Backbone.View.extend({
        tagName : 'td',
        initialize : function() {
            this.rendered = $.Deferred();
            this.render();
        },
        render : function() {
            var currentDate = new Date(this.options.date.getTime());
            this.$el.html(currentDate.getDate());
            if (this.options.isAnotherMonth) {
                this.$el.addClass('another-month');
            }
            var today = new Date();
            if (currentDate.getDate() == today.getDate() && currentDate.getMonth() == today.getMonth()
                    && currentDate.getFullYear() == today.getFullYear()) {
                this.$el.addClass('today');
            }
                return this;
        }
    });
    return dayView;
});