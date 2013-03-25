define([ 'jquery', 'backbone', 'menu/menuView', 'record/recordView', 'util' ], function($, Backbone, MenuView,
        RecordView, util) {

    var menuRouter = Backbone.Router.extend({

        routes : {
            'record' : 'loadRecord',
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

        loadRecord : function() {
            this.removeActiveSelection();
            this.changeSelection('.id_record');
            this.changeView(RecordView);

        },

        loadAbout : function() {
            this.removeActiveSelection();
            this.changeSelection('.id_about');
            $('#ajax-content').html('About page content...<div style="height:300px;"></div>');
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

        changeView : function(View) {
            util.showGlobalSpinner();
            var view = new View();
            $.when(view.rendered).done(function() {
                $('#ajax-content').html(view.el);
                util.hideGlobalSpinner();
            });
            view.render();
        }

    });

    return menuRouter;
});