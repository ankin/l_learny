define([ 'backbone', 'common/baseCol', 'util' ], function(Backbone, BaseCol, util) {

    var wordCol = BaseCol.extend({
        initialize : function(models, options) {
            BaseCol.prototype.initialize.apply(this, arguments);
            this.recordUuid = options.recordUuid;
        },
        url : function() {
            return 'services/record/' + this.recordUuid + '/words/';
        }
    });
    return wordCol;
});