package com.learny.ejb.service.local;

import java.util.List;

import javax.ejb.Local;

import com.learny.persistence.entity.RecordComment;

@Local
public interface CommentLocal {

    void createRecordComment(String userUuid, String recordUuid, String commentText);

    List<RecordComment> findRecordCommentsByRecordUuid(String recordUuid);

}
