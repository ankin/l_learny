define([ 'jquery', 'backbone', 'menu/menuView', 'record/recordView', 'history/historyView', 'util' ], function($,
        Backbone, MenuView, RecordView, HistoryView, util) {

    var menuRouter = Backbone.Router.extend({

        routes : {
            'record' : 'loadRecord',
            'record/:uuid' : 'loadRecord',
            'popular' : 'loadPopular',
            'history' : 'loadHistory'

        },

        initialize : function() {
            this.loadMenu();
        },

        loadMenu : function() {
            this.header = $('#mainMenu').prepend(new MenuView().render().el);
            if (!window.location.hash) {
                this.loadRecord();
            }
        },

        loadRecord : function(uuid) {
            this.removeActiveSelection();
            this.changeSelection('.id_record');
            this.changeView(new RecordView({
                recordUuid : uuid
            }));

        },

        loadHistory : function() {
            this.removeActiveSelection();
            this.changeSelection('.id_history');
            this.changeView(new HistoryView());
        },

        loadPopular : function() {
            this.removeActiveSelection();
            this.changeSelection('.id_popular');
            $('#ajax-content').html('Popular page content...<div style="height:300px;"></div>');
        },

        removeActiveSelection : function() {
            _.forEach($('#mainMenu').find('li'), function(liEl) {
                if ($(liEl).hasClass('active')) {
                    $(liEl).removeClass('active');
                }
            });
        },

        changeSelection : function(menuItemSltr) {
            if (!$(menuItemSltr).hasClass('active')) {
                $(menuItemSltr).addClass('active');
            }
        },

        changeView : function(view) {
            util.showGlobalSpinner();
            $.when(view.rendered).done(function() {
                $('#ajax-content').html(view.el);
                util.hideGlobalSpinner();
            });
            view.render();
        }

    });

    return menuRouter;
});