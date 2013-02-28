define([ 'backbone', 'common/baseModel', 'util' ], function(Backbone, BaseModel, util) {

    var recordModel = BaseModel.extend({
        initialize : function() {
            BaseModel.prototype.initialize.apply(this, arguments);
        },
        url : 'services/record/',

        parse : function(resp, xhr) {
            var model = resp;
            model.dateCreated = util.formatDateTime(model.dateCreated);
            return model;
        }
    });
    return recordModel;
});