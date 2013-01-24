define([ 'backbone' ], function(Backbone) {

    var commentCollection = Backbone.Collection.extend({
	url : 'services/comment/getall'
    });
    return commentCollection;
});