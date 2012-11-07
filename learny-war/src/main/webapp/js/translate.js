var Translate = {
    load : function(id) {
	$.getJSON("services/translate/name", function(data) {
	    $.each(data, function(i, item) {
		$(id).append(
			"Source: " + item.source + " Target: " + item.target
				+ "<br>");
	    });
	});
    }
};