package com.latte.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.HashTagDaoImpl;
import com.latte.model.Hashtag;
import com.latte.model.PostHashtag;

@Service
public class HashTagServiceImpl implements IHashTagService {

	@Autowired
	HashTagDaoImpl hashtagDao;

	@Override
	@Transactional
	public int isHashtagNameExist(String name) {
		return hashtagDao.isHashtagNameExist(name);
	}

	@Override
	@Transactional
	public int addHashtagName(String name) {
		return hashtagDao.addHashtagName(name);
	}

	@Override
	@Transactional
	public int updateHashtagId(Long post_id) {
		return hashtagDao.updateHashtagId(post_id);
	}

	@Override
	@Transactional
	public int deleteHashtagId(Long post_id) {
		return hashtagDao.deleteHashtagId(post_id);
	}

	@Override
	@Transactional
	public int addHashtagId(PostHashtag post_hashtag) {
		return hashtagDao.addHashtagId(post_hashtag);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> hashtagsInThePost(Long post_id) {
		return hashtagDao.hashtagsInThePost(post_id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Hashtag> getAllHashtagByNames(List<String> names) {
		return hashtagDao.getAllHashtagByNames(names);
	}
	

}
