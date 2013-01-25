define(
	[ 'jquery', 'backbone', 'util/util', 'text!record/record.html', 'record/recordModel', 'comment/commentsColView' ],
	function($, Backbone, util, recordTpl, RecordModel, CommentColView) {

	    var recordView = Backbone.View.extend({
		className : 'record',
		_template : _.template(recordTpl),
		model : new RecordModel(),
		initialize : function() {
		    // this.render();
		},

		render : function() {
		    self = this;
		    self.model.fetch({
			success : function() {
			    var recordJson = self.model.toJSON();
			    self.$el.html(self._template({
				record : recordJson,
				util : util
			    }));
			    self.$el.find('.id_comments').html(new CommentColView({
				objectType : recordJson.objectType,
				objectId : recordJson.uuid
			    }).render().el);// add comments
			}
		    });
		    return this;
		}
	    });
	    return recordView;
	});