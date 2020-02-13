package com.latte.service;

import java.util.List;

import com.latte.model.Hashtag;
import com.latte.model.PostHashtag;

public interface IHashTagService {
	//해쉬태그 ID가 DB에 존재 하는지
	int isHashtagIdExist(Integer hashtag_id);
	
	// 해쉬태그명이 DB에 존재 하는지
	int isHashtagNameExist(String name);

	// 해쉬태그명 추가
	int addHashtagName(String name);

	// Post에 등록된 해쉬태그의 id 변경 UPDATE
	int updateHashtagId(Long post_id);

	// Post에 등록된 해쉬태그의 id 삭제 DELETE
	int deleteHashtagId(Long post_id);

	// Post에 등록된 해쉬태그의 id 추가 INSERT
	int addHashtagId(PostHashtag post_hashtag);
	
	// Post_hashtag 제거
	int deletePostHashtag(PostHashtag post_hashtag);

	// Post에 등록된 모든 해쉬태그명 반환 String List
	List<String> hashtagsInThePost(Long post_id);

	// 해쉬태그명으로 hashtag 모두 검색
	List<Hashtag> getAllHashtagByNames(List<String> names);
	
	// Post id로 등록된 모든 hashtag id 조회
	List<Integer> getAllHashtagIdByPostId(Long posts_id);
}
