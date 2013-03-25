define([ 'backbone', 'common/baseModel'], function(Backbone, BaseModel) {

    var recordHistoryModel = BaseModel.extend({
        initialize : function() {
            BaseModel.prototype.initialize.apply(this, arguments);
        },
        url : 'services/record/history/'
    });
    return recordHistoryModel;
});