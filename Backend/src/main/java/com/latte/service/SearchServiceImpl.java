package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.SearchDaoImpl;
import com.latte.dto.CafeDto;
import com.latte.model.post.Post;

@Service
public class SearchServiceImpl implements ISearchService {

	@Autowired
	SearchDaoImpl searchDao;

	@Override
	@Transactional(readOnly = true)
	public List<Post> searchInPosts(String query) {
		return searchDao.searchInPosts(query);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CafeDto> searchInCafe(String query) {
		return searchDao.searchInCafe(query);
	}

}
