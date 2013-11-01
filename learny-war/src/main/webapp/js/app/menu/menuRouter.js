define([ 'jquery', 'backbone', 'menu/menuView', 'record/recordView', 'history/historyView', 'util' ], function($,
        Backbone, MenuView, RecordView, HistoryView, util) {

    var menuRouter = Backbone.Router.extend({

        routes : {
            'record' : 'loadRecord',
            'record/:uuid' : 'loadRecord',
            'about' : 'loadAbout',
            'news' : 'loadNews',
            'settings' : 'loadSettings',
            'contact' : 'loadContact'

        },

        initialize : function() {
            this.loadMenu();
        },

        loadMenu : function() {
            this.header = $('#mainMenu').prepend(new MenuView().render().el);
        },

        loadRecord : function(uuid) {
            this.removeActiveSelection();
            this.changeSelection('.id_record');
            this.changeView(new RecordView({
                recordUuid : uuid
            }));

        },

        loadAbout : function() {
            this.removeActiveSelection();
            this.changeSelection('.id_about');
            this.changeView(new HistoryView());
        },

        loadNews : function() {
            this.removeActiveSelection();
            this.changeSelection('.id_news');
            $('#ajax-content').html('News page content...<div style="height:300px;"></div>');
        },

        loadSettings : function() {
            this.removeActiveSelection();
            this.changeSelection('.id_settings');
            $('#ajax-content').html('Settings page content...<div style="height:300px;"></div>');
        },

        loadContact : function() {
            this.removeActiveSelection();
            this.changeSelection('.id_contact');
            $('#ajax-content').html('Contact page content...<div style="height:300px;"></div>');
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