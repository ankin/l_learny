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
	$(recordSelector + ' .id_translations').append(
		'<tr><td>' + (wordKey + 1) + '</td><td>' + word.source
			+ '</td><td> ' + word.target + '</td></tr>');
    });
};

Records.appendRules = function(rules, recordSelector) {
    $.each(rules, function(ruleKey, rule) {
	$(recordSelector + ' .id_rules').append(rule.text);
    });
};

Records.appendComments = function(comments, parentSelector) {
    $.each(comments, function(commentKey, comment) {
	Records.appendSingleComment(comment, parentSelector);
    });

    // attach submit new comment handler to from
    var newCommentFormSelector = parentSelector + ' .id_comment_form';
    $(newCommentFormSelector).ajaxForm({
	url : 'services/records/newcomment/',
	type : 'post',
	dataType:  'json',
	success : function(data){
	    Records.appendSingleComment(data, parentSelector);
	    $(newCommentFormSelector).clearForm();
	}
    });

};

Records.appendSingleComment = function(comment, parentSelector) {
    // look for latest comment key and add new comment
    var commentDivId = 'comment' + comment.uuid;
    var commentSelector = parentSelector + ' #' + commentDivId;
    $(parentSelector + ' .id_record_comments').append(
	    '<div id="' + commentDivId + '"></div>');
    $(commentSelector).load(
	    'html/records/comment.html',
	    function() {
		Records.appendDateCreated(comment.dateCreated, commentSelector);
		$(commentSelector + ' .id_author').append(
			comment.user.displayName + ":");
		$(commentSelector + ' .id_text').append(comment.text);
	    });
};

Records.appendDateCreated = function(dateCreated, parentSelector) {
    var formattedDate = Learny_date.formatDateTime(dateCreated);
    $(parentSelector + ' .id_dateCreated').append(formattedDate);
};
