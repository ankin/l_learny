define([ 'jquery', 'backbone', 'calendar/monthView', 'text!calendar/calendar.html' ], function($, Backbone, MonthView,
        calendarTpl) {

    var calendarView = Backbone.View.extend({
        _template : _.template(calendarTpl),

        events : {
            'click a#cal_left_month' : 'prevMonth',
            'click a#cal_right_month' : 'nextMonth',
            'click a#cal_close' : 'closeCalendar'
        },

        initialize : function() {
            this.model = new this.options.model();
            this.rendered = $.Deferred();
        },

        render : function() {
            var self = this;

            self.model.fetch({
                success : function() {
                    self.createCalendarBody();
                }
            });

            return this;
        },

        createCalendarBody : function() {
            var self = this;
            var currentDate = self.options.date;
            self.$el.html(self._template({
                currentDate : currentDate,
            }));
            self.$el.find('table').append(new MonthView({
                date : currentDate
            }).el);

            self.rendered.resolve('rendered');
        },

        prevMonth : function(e) {
            e.preventDefault();
            var currentDate = this.options.date;
            currentDate.setMonth(currentDate.getMonth() - 1);
            this.options.date = currentDate;
            this.createCalendarBody();
        },

        nextMonth : function(e) {
            e.preventDefault();
            var currentDate = this.options.date;
            currentDate.setMonth(currentDate.getMonth() + 1);
            this.options.date = currentDate;
            this.createCalendarBody();
        },

        closeCalendar : function() {
            this.trigger('closeCalendar');
            return false; // to not jump to the top of page
        }

    });

    return calendarView;
});