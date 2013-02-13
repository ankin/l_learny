define([ 'backbone' , 'common/baseCol'], function(Backbone, BaseCollection) {

    var commentCollection = BaseCollection.extend({
        initialize : function() {
            BaseCollection.prototype.initialize.apply(this, arguments);
        },
        url : 'services/comment/record/getall'
    });
    return commentCollection;
});