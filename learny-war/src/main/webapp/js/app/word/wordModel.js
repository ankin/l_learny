define([ 'backbone', 'common/baseModel' ], function(Backbone, BaseModel) {

    var wordModel = BaseModel.extend({
        initialize : function(options) {
            BaseModel.prototype.initialize.apply(this, arguments);
        },

        parse : function(resp, xhr) {
            var model = resp;
            _.forEach(model.translations, function(translation) {
                translation.genderLocalized = $.i18n.prop(translation.gender);
            });
            return model;
        }

    });
    return wordModel;
});