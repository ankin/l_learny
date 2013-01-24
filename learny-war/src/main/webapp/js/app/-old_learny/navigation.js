//History based navigation

// ajaxContent - variable defining html place holder of ajax-content

var NavigationCache = new Array();
$(document).ready(function() {
    NavigationCache[window.location.pathname] = $(ajaxContent).html();
});

window.onpopstate = function(event) {
    if (event.state.type.length > 0) {
	if (NavigationCache[event.state.page].length > 0) {
	    $(ajaxContent).html(NavigationCache[event.state.page]);
	}
    }
};

function setPage(page) {
    $.ajax({
	type : 'POST',
	url : page,
	dataType : 'html',
	success : function(data) {
	    $(ajaxContent).html(data);
	    NavigationCache[page] = data;
	    history.pushState({
		page : page,
		type : 'page'
	    }, document.title, page);
	}
    });
}
