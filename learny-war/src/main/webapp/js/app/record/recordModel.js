define([ 'backbone', 'common/baseModel', 'util' ], function(Backbone, BaseModel, util) {

    var recordModel = BaseModel.extend({
        initialize : function() {
            BaseModel.prototype.initialize.apply(this, arguments);
        },

        recordUuid : null,

        url : function() {
            if (this.recordUuid) {
                return 'services/record/' + this.recordUuid;
            } else {
                return 'services/record/';
            }
        },

        parse : function(resp, xhr) {
            var model = resp;
            model.dateCreatedFormatted = util.formatDateTime(model.dateCreated);
            return model;
        }
    });
    return recordModel;
});