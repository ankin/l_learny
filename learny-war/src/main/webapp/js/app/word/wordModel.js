define([ 'backbone', 'common/baseModel'], function(Backbone, BaseModel) {

    var wordModel = BaseModel.extend({
        initialize : function() {
            BaseModel.prototype.initialize.apply(this, arguments);
        },
        url : 'services/record/word/'
    });
    return wordModel;
});