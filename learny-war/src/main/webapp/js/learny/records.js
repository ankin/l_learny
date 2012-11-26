var Records = {};

Records.load = function(id) {
    $.getJSON('services/records/current/', function(data) {
	$.each(data, function(recordKey, record) {
	    var recordDivId = 'record' + recordKey;
	    var recordSelector = '#' + recordDivId;
	    $(id).append('<div id="' + recordDivId + '"></div>');
	    var loadRecordContent = function() {
		Records.appendDateCreated(record.dateCreated, recordSelector);
		Records.appendWords(record.words, recordSelector);
		Records.appendRules(record.rules, recordSelector);
		Records.appendComments(record.comments, recordSelector);
	    };
	    Learny_nav.loadAndTranslate(recordSelector,
		    'html/records/record.html', loadRecordContent);
	});
    });
};

Records.appendWords = function(words, recordSelector) {
    $.each(words, function(wordKey, word) {
	$(recordSelector + ' .translations').append(
		'<tr><td>' + (wordKey + 1) + '</td><td>' + word.source
			+ '</td><td> ' + word.target + '</td></tr>');
    });
};

Records.appendRules = function(rules, recordSelector) {
    $.each(rules, function(ruleKey, rule) {
	$(recordSelector + ' .rules').append(
		'<p class="text-info">' + rule.text + '</p>');
    });
};

Records.appendComments = function(comments, recordSelector) {
    $.each(comments, function(commentKey, comment) {
	var commentDivId = 'comment' + commentKey;
	var commentSelector = recordSelector + ' #' + commentDivId;
	$(recordSelector + ' .comments').append(
		'<div id="' + commentDivId + '"></div>');
	$(commentSelector).load(
		'html/records/comment.html',
		function() {
		    var formattedDate = Learny_date
			    .formatDateTime(comment.dateCreated);
		    $(commentSelector + ' .dateCreated').append(formattedDate);
		    $(commentSelector + ' .author').append(
			    comment.user.displayName);
		    $(commentSelector + ' .text').append(comment.text);
		});
    });

    $(recordSelector + ' form').submit(function() {
	$(this).ajaxSubmit({
	    url : 'services/records/newcomment/',
	    type : 'post'
	});
	return false;
    });
};

Records.appendSingleComment = function(comments, recordSelector) {
    // look for latest comment key and add new comment
};

Records.appendDateCreated = function(dateCreated, recordSelector) {
    var formattedDate = Learny_date.formatDateTime(dateCreated);
    $(recordSelector + ' .dateCreated').append(formattedDate);
};
