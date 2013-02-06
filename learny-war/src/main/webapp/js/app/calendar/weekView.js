define([ 'jquery', 'backbone', 'calendar/dayView' ], function($, Backbone, DayView) {

    var weekView = Backbone.View.extend({
        tagName : 'tr',
        initialize : function() {
            this.rendered = $.Deferred();
            this.render();
        },

        render : function() {
            var currentDate = new Date(this.options.date.getTime());
            var currentMonth = this.options.date.getMonth();
            if (currentDate.getDate() == 1) {
                while (currentDate.getDay() != 0) {
                    currentDate.setDate(currentDate.getDate() - 1);
                }
            }
            var isAnotherMonth;
            while (currentDate.getDay() != 6) {
                isAnotherMonth = currentMonth != currentDate.getMonth(); // change style from non-current month dates
                this.$el.append(new DayView({
                    date : new Date(currentDate.getTime()),
                    isAnotherMonth : isAnotherMonth,
                    isLink : true
                }).el);
                currentDate.setDate(currentDate.getDate() + 1);
            }
            // add last day in week
            isAnotherMonth = currentMonth != currentDate.getMonth();
            this.$el.append(new DayView({
                date : new Date(currentDate.getTime()),
                isAnotherMonth : isAnotherMonth,
                isLink : true
            }).el);
            return this;
        }
    });
    return weekView;
});