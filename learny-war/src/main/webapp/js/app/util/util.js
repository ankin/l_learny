define([ 'jquery', 'jqueryDateFormat', 'jqueryi18n' ], function($, jqueryDateFormat, jqueryi18n) {
    return {

        initLanguage : function(language) {
            $.i18n.properties({
                name : 'Messages',
                path : 'i18n/',
                mode : 'map',
                language : language
            });
        },

        // Format and translate date
        formatDateTime : function(date) {
            return $.format.date(date, $.i18n.prop('FotmattedDateTime'));
        },

        showGlobalSpinner : function() {
            $('#spinner-container').trigger('showSpinner');
        },

        hideGlobalSpinner : function() {
            $('#spinner-container').trigger('hideSpinner');
        }

    };
});