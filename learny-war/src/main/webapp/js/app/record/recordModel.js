define([ 'backbone' ], function(Backbone) {

    var recordModel = Backbone.Model.extend({
        initialize : function() {
            this.on('error', function(model, xhr, options) {
                var redirectTo = xhr.getResponseHeader('redirectTo');
                if (redirectTo) {
                    window.location.href = redirectTo;
                }
            });
        },

        url : 'services/record/get/'
    });
    return recordModel;
});