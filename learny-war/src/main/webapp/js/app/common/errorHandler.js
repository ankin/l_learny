define([ 'backbone' ], function(Backbone) {
    return {

        attachErrorHandler : function(obj) {
            obj.on('error', function(model, xhr, options) {
                var redirectTo = xhr.getResponseHeader('redirectTo');
                if (redirectTo) {
                    window.location.href = redirectTo;
                }
            });
        }

    };
});