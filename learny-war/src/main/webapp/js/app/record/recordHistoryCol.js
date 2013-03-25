define([ 'backbone', 'util', 'common/baseCol' ], function(Backbone, util, BaseCol) {

    var recordHistoryCol = BaseCol.extend({
        initialize : function() {
            BaseCol.prototype.initialize.apply(this, arguments);
        },
        url : 'services/record/history/',

        parse : function(resp, xhr) {
            _.each(resp, function(histModel) {
                histModel.dateCreatedFormatted = util.formatDateTime(histModel.dateCreated);
            });
            return resp;

        }
    });
    return recordHistoryCol;
});