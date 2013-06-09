define([ 'backbone', 'common/baseModel' ], function(Backbone, BaseModel) {

    var wordModel = BaseModel.extend({
        initialize : function(options) {
            BaseModel.prototype.initialize.apply(this, arguments);
        },

        url : function() {
            if (this.get("uuid")) {
                return this.urlRoot + this.get("uuid");
            } else {
                return this.urlRoot;
            }
        },

        parse : function(resp, xhr) {
            var model = resp;
            model.genderLocalized = $.i18n.prop(model.gender);
            return model;
        }

    });
    return wordModel;
});