var Main = {};

/**
 * Method called once index page was loaded
 */
Main.init = function() {

    ajaxContent = '#ajax-content';

    // init i18n
    Learny_i18n.init('en');
    Learny_i18n.translate('#nav');

    $('#nav li a').click(function(e) {

	// $("#ajax-content").empty().append("<div id='loading'><img
	// src='images/loading.gif' alt='Loading' /></div>");

	// update selected menu
	$('#nav li').removeClass('active');
	$(this).parent().addClass('active');

	var url = $(this).attr('href');
	// disabled history based navigation
	if (false && history.pushState) {
	    setPage(url);
	} else {
	    Learny_nav.loadAndTranslate(ajaxContent, url);
	}
	e.preventDefault();
	return false;
    });

    // loaded by default page
    Learny_nav.loadAndTranslate(ajaxContent, 'html/about.html');

};

/**
 * i18n utils =====================================================
 */
var Learny_i18n = {};

Learny_i18n.init = function(language) {
    jQuery.i18n.properties({
	name : 'Messages',
	path : 'i18n/',
	mode : 'map',
	language : language
    });
};

/**
 * Translate fragment specified by selector
 */
Learny_i18n.translate = function(fragmentSelector) {
    $(fragmentSelector + ' [i18n]').each(function(index) {
	this.innerHTML = jQuery.i18n.prop(this.attributes['i18n'].value);
    });
};

/**
 * Navigation utils =====================================================
 */
var Learny_nav = {};

/**
 * Load page in fragment specified by selector and translate it
 */
Learny_nav.loadAndTranslate = function(selector, page, complete) {
    $(selector).load(page, function() {
	if (complete != null && complete != 'undefined') {
	    complete();
	}
	Learny_i18n.translate(selector);
    });
};

/**
 * Date utils =====================================================
 */
var Learny_date = {};

/**
 * Format and translate date
 */
Learny_date.formatDateTime = function(date) {
    return $.format.date(date, jQuery.i18n.prop('FotmattedDateTime'));

};