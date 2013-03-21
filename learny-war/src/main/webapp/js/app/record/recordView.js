define([ 'jquery', 'backbone', 'util', 'text!record/record.html', 'record/recordModel', 'word/wordColView',
        'comment/commentsColView', 'calendar/calendarView' ], function($, Backbone, util, recordTpl, RecordModel,
        WordColView, CommentColView, CalendarView) {

    var recordView = Backbone.View.extend({
        className : 'record',
        _template : _.template(recordTpl),
        model : new RecordModel(),

        events : {
            'click #calendar-open-link' : 'showCalendar'
        },

        initialize : function() {
            this.rendered = $.Deferred();
        },

        render : function() {
            var self = this;
            self.model.fetch({
                success : function() {
                    var recordJson = self.model.toJSON();
                    self.$el.html(self._template({
                        record : recordJson,
                        util : util
                    }));

                    self.$el.find('#words-table').html(new WordColView({
                        data : recordJson.words,
                        recordUuid : recordJson.uuid
                    }).el);
                    var calView = new CalendarView();
                    calView.on('closeCalendar', function() {
                        self.hideCalendar();
                    });
                    self.$el.find('#record-calendar').append(calView.el);
                    // add comments
                    var commentColView = new CommentColView({
                        recordUuid : recordJson.uuid
                    });
                    $.when(commentColView.rendered).done(function() {
                        self.$el.find('.id_comments').html(commentColView.el);
                        self.rendered.resolve('rendered');
                    });
                    commentColView.render();
                }
            });
            return this;
        },

        hideCalendar : function() {
            if ($('#record-calendar').hasClass('open')) {
                $('#record-calendar').removeClass('open');
            }
        },

        showCalendar : function() {
            if (!$('#record-calendar').hasClass('open')) {
                $('#record-calendar').addClass('open');
                return false;
            }
        }

    });
    return recordView;
});