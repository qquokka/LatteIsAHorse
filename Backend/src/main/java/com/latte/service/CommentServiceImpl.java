package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.latte.dao.CommentDaoImpl;
import com.latte.dto.CommentDto;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	CommentDaoImpl commentDao;

	@Override
	public List<CommentDto> getAllCommentList() {
		return commentDao.getAllCommentList();
	}

	@Override
	public CommentDto getCommentByCommentId(int commentid) {
		return commentDao.getCommentByCommentId(commentid);
	}

	@Override
	public int addComment(CommentDto comment) {
		return commentDao.addComment(comment);
	}

	@Override
	public int updateCommentInfo(CommentDto comment) {
		return commentDao.updateCommentInfo(comment);
	}

	@Override
	public int deleteComment(int commentid) {
		return commentDao.deleteComment(commentid);
	}

}
