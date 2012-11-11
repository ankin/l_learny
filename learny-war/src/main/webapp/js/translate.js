var load = function(id) {
    $.getJSON("services/translate/name", function(data) {
	$.each(data, function(recordKey, record) {
	    var recordDivId = "record" + recordKey;
	    $(id).append("<div id=\"" + recordDivId + "\"></div>");
	    $("#" + recordDivId).load("html/record.html", function() {
		appendWords(record.words, recordDivId);
		appendRules(record.rules, recordDivId);
	    });

	});
    });
};

var appendWords = function(words, recordDivId) {
    // Words
    $.each(words, function(wordKey, word) {

	$("#" + recordDivId + " .translations").append(
		"<tr><td>" + (wordKey + 1) + "</td><td>" + word.source
			+ "</td><td> " + word.target + "</td></tr>");
    });
};

var appendRules = function(rules, recordDivId) {
    // Rules
    $.each(rules, function(ruleKey, rule) {
	$("#" + recordDivId + " .rules").append(
		"<div class=\"well\">" + rule.text + "</div>");
    });
};
