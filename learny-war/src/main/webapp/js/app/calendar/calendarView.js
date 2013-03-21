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
            this.rendered = $.Deferred();
            this.options.date = new Date();
            this.render();
        },

        render : function() {
            var currentDate = this.options.date;
            this.$el.html(this._template({
                currentDate : currentDate,
            }));
            this.$el.find('table').append(new MonthView({
                date : currentDate
            }).el);
        },

        prevMonth : function(e) {
            e.preventDefault();
            var currentDate = this.options.date;
            currentDate.setMonth(currentDate.getMonth() - 1);
            this.options.date = currentDate;
            this.render();
        },

        nextMonth : function(e) {
            e.preventDefault();
            var currentDate = this.options.date;
            currentDate.setMonth(currentDate.getMonth() + 1);
            this.options.date = currentDate;
            this.render();
        },
        
        closeCalendar : function(){
            this.trigger('closeCalendar');
        }

    });

    return calendarView;
});