define([ 'backbone' ], function(Backbone) {
    return {

        attachErrorHandler : function(obj) {
            obj.on('error', function(model, xhr, options) {
                // show some kind of message to user before redirect
                window.location.href = '/learny/';
            });
        }

    };
});