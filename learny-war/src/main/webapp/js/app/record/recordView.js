define([ 'jquery', 'backbone', 'util', 'text!record/record.html', 'record/recordModel', 'record/recordHistoryCol',
        'word/wordColView', 'comment/commentsColView', 'calendar/calendarView' ], function($, Backbone, util,
        recordTpl, RecordModel, RecordHistoryCol, WordColView, CommentColView, CalendarView) {

    var recordView = Backbone.View.extend({
        className : 'record',
        _template : _.template(recordTpl),
        model : new RecordModel(),

        events : {
            'click #calendar-open-link' : 'showCalendar'
        },

        initialize : function() {
            this.rendered = $.Deferred();
            this.recordUuid = this.options.recordUuid;
        },

        render : function() {
            var self = this;
            if (self.recordUuid) {
                self.model.recordUuid = self.recordUuid;
            }
            self.model.fetch({
                success : function() {
                    var recordJson = self.model.toJSON();
                    self.$el.html(self._template({
                        record : recordJson,
                        util : util
                    }));

                    // add words widget
                    self.$el.find('#words-table').html(new WordColView({
                        data : recordJson.words,
                        recordUuid : recordJson.uuid
                    }).el);

                    // add calendar widget
                    self.calView = new CalendarView({
                        date : new Date(recordJson.dateCreated),
                        model : RecordHistoryCol,
                        dateClickHandler : self
                    });
                    self.calView.on('closeCalendar', function() {
                        self.hideCalendar();
                    });
                    self.$el.find('#record-calendar').html(self.calView.el);

                    // add comments widget
                    var commentColView = new CommentColView({
                        recordUuid : recordJson.uuid
                    });

                    $.when(commentColView.rendered).done(function() {
                        self.$el.find('#record-comments').html(commentColView.el);
                        self.rendered.resolve('rendered');
                        self.rendered = $.Deferred();
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
            return false;
        },

        showCalendar : function() {
            var self = this;
            if (!$('#record-calendar').hasClass('open')) {
                util.showGlobalSpinner();
                self.calView.render();
                $.when(this.calView.rendered).done(function() {
                    $('#record-calendar').addClass('open');
                    util.hideGlobalSpinner();
                    self.calView.rendered = $.Deferred();
                });

                return false;
            }
        },

        handleDateClick : function(recordUuid) {
            var self = this;
            self.hideCalendar();
            util.showGlobalSpinner();
            self.model = new RecordModel();
            self.recordUuid = recordUuid;
            self.render();
            $.when(this.rendered).done(function() {
                util.hideGlobalSpinner();
                self.rendered = $.Deferred();
            });
        }

    });
    return recordView;
});