define([ 'backbone', 'common/baseModel' ], function(Backbone, BaseModel) {

    var recordModel = BaseModel.extend({
        initialize : function() {
            BaseModel.prototype.initialize.apply(this, arguments);
        },
        url : 'services/record/get/'
    });
    return recordModel;
});