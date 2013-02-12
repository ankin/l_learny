define([ 'backbone' , 'common/errorHandler'], function(Backbone, ErrorHandler) {

    var baseModel = Backbone.Model.extend({
        initialize : function() {
            ErrorHandler.attachErrorHandler(this);
        }
    });
    return baseModel;
});