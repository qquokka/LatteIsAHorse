package com.latte.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latte.dto.CafeDto;
import com.latte.model.post.Post;
import com.latte.service.ISearchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/v1")
@Api(value = "Search APIs")
public class SearchController {
	// post title, 주소, 가장 가까운,최신순, 좋아요순, 카페 이름
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private ISearchService searchService;

	@ApiOperation(value = "통합 검색(Post, Cafe)", response = Map.class)
	@GetMapping("/search/{query}")
	public ResponseEntity<Map<String, Object>> doSearch(@PathVariable("query") String query) throws Exception {
		logger.info("SearchController-------------Do Search-------------" + new Date());
//		query = query.replace(',', '|');
		
		List<Post> posts = searchService.searchInPosts(query);
		List<CafeDto> cafes = searchService.searchInCafe(query);
		
		Map<String, Object> reponse = new HashMap<String, Object>();
		
		reponse.put("posts", posts);
		reponse.put("cafes", cafes);		

		return new ResponseEntity<Map<String,Object>>(reponse, HttpStatus.OK);
	}
}
