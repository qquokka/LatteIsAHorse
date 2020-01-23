package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.CommentDto;

@Repository
public class CommentDaoImpl {

	String ns = "latte.comment."; // ns : namespace

	@Autowired
	private SqlSession sqlSession;

	public List<CommentDto> getAllCommentList() {
		return sqlSession.selectList(ns + "getAllCommentList");
	}

	public int addComment(CommentDto comment) {
		return sqlSession.insert(ns + "addComment", comment);
	}

	public CommentDto getCommentByCommentId(int commentid) {
		return sqlSession.selectOne(ns + "getCommentByCommentId", commentid);
	}

	public int updateCommentInfo(CommentDto comment) {
		return sqlSession.update(ns + "updateCommentInfo", comment);
	}

	public int deleteComment(int commentid) {
		return sqlSession.delete(ns + "deleteComment", commentid);
	}

}
