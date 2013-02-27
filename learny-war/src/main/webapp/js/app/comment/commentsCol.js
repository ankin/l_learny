define([ 'backbone', 'common/baseCol', 'util'], function(Backbone, BaseCollection, util) {

    var commentCollection = BaseCollection.extend({
        initialize : function() {
            BaseCollection.prototype.initialize.apply(this, arguments);
        },
        url : 'services/comment/record/getall',
        
        parse : function(resp, xhr) {
            var model = resp;
            _.forEach(resp, function(comment, index) {
                comment.dateCreated = util.formatDateTime(comment.dateCreated);
            });
            return model;
        }
    });
    return commentCollection;
});