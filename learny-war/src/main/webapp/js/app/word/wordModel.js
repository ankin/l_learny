define([ 'backbone', 'common/baseModel' ], function(Backbone, BaseModel) {

    var wordModel = BaseModel.extend({
        initialize : function(options) {
            BaseModel.prototype.initialize.apply(this, arguments);
        },

    });
    return wordModel;
});