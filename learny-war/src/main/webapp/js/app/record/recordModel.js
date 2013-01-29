define([ 'backbone' ], function(Backbone) {

    var recordModel = Backbone.Model.extend({
        url : 'services/record/get/'
    });
    return recordModel;
});