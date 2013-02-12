define([ 'backbone', 'common/errorHandler' ], function(Backbone, ErrorHandler) {

    var baseCollection = Backbone.Collection.extend({
        initialize : function() {
            ErrorHandler.attachErrorHandler(this);
        }
    });
    return baseCollection;
});