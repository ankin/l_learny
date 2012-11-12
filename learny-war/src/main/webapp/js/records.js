var Records = {};

Records.load = function(id) {
    $.getJSON("services/translate/name", function(data) {
	$.each(data, function(recordKey, record) {
	    var recordDivId = "record" + recordKey;
	    $(id).append("<div id=\"" + recordDivId + "\"></div>");
	    $("#" + recordDivId).load("html/record.html", function() {
		Records.appendDateCreated(record.dateCreated, recordDivId);
		Records.appendWords(record.words, recordDivId);
		Records.appendRules(record.rules, recordDivId);
	    });

	});
    });
};

Records.appendWords = function(words, recordDivId) {
    $.each(words, function(wordKey, word) {

	$("#" + recordDivId + " .translations").append(
		"<tr><td>" + (wordKey + 1) + "</td><td>" + word.source
			+ "</td><td> " + word.target + "</td></tr>");
    });
};

Records.appendRules = function(rules, recordDivId) {
    $.each(rules, function(ruleKey, rule) {
	$("#" + recordDivId + " .rules").append(
		"<div class=\"well\">" + rule.text + "</div>");
    });
};

Records.appendDateCreated = function(dateCreated, recordDivId) {
    $("#" + recordDivId + " .dateCreated").append(dateCreated);
};
