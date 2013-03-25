define([ 'jquery', 'backbone', 'calendar/dayView' ], function($, Backbone, DayView) {

    var weekView = Backbone.View.extend({
        tagName : 'tr',
        initialize : function() {
            this.rendered = $.Deferred();
            this.render();
        },

        render : function() {
            var self = this;
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
                    isLink : self.isLink(currentDate)
                }).el);
                currentDate.setDate(currentDate.getDate() + 1);
            }
            // add last day in week
            isAnotherMonth = currentMonth != currentDate.getMonth();
            this.$el.append(new DayView({
                date : new Date(currentDate.getTime()),
                isAnotherMonth : isAnotherMonth,
                isLink : self.isLink(currentDate)
            }).el);
            return this;
        },

        isLink : function(currentDate) {
            var result = this.options.history
                    .filter(function(histItem) {
                        var histDate = new Date(histItem.get('dateCreated'));
                        return histDate.getFullYear() == currentDate.getFullYear()
                                && histDate.getMonth() == currentDate.getMonth()
                                && histDate.getDate() == currentDate.getDate();
                    });
            if (result && result.length > 0) {
                if (result.length == 1) {
                    return true;
                } else {
                    console.error('Found more than one record for day: ' + new Date(time));
                    return false;
                }
            }
            return false;
        }
    });
    return weekView;
});