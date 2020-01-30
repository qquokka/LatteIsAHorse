package com.latte.service;

import java.util.List;

import com.latte.dto.CommentDto;

public interface ICommentService {
	// DB에 저장된 모든 Comment를 반환
	List<CommentDto> getAllCommentList();

	// Create
	int addComment(CommentDto post);

	// Read
	CommentDto getCommentByCommentId(int commentid);

	// Update
	int updateCommentInfo(CommentDto comment);

	// Delete
	int deleteComment(int commentid);

}
