define([ 'backbone', 'common/baseModel', 'util' ], function(Backbone, BaseModel, util) {

    var wordModel = BaseModel.extend({
        initialize : function() {
            BaseModel.prototype.initialize.apply(this, arguments);
        },
        url : 'services/record/word/'
    });
    return wordModel;
});