define([ 'backbone', 'common/baseCol', 'util' ], function(Backbone, BaseCol, util) {

    var wordCol = BaseCol.extend({
        initialize : function() {
            BaseCol.prototype.initialize.apply(this, arguments);
        }
    });
    return wordCol;
});