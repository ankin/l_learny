define([ 'backbone', 'common/baseCol', 'util', 'word/wordModel' ], function(Backbone, BaseCol, util, WordModel) {

    var wordCol = BaseCol.extend({
        initialize : function(models, options) {
            BaseCol.prototype.initialize.apply(this, arguments);
            this.recordUuid = options.recordUuid;
        },
        url : function() {
            return 'services/record/' + this.recordUuid + '/words/';
        },

        model : function(attrs, options) {
            return new WordModel(attrs, {
                url : 'services/record/' + options.recordUuid + '/word/' + attrs.uuid
            });

        }
    });
    return wordCol;
});