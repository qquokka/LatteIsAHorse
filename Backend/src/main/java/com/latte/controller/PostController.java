package com.latte.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.latte.dto.PostDto;
import com.latte.help.NumberResult;
import com.latte.service.IPostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value = "LATTE", description = "Post APIs")
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private IPostService postservice;

	@Autowired
	ApplicationContext applicationContext;

	@ApiOperation(value = "post list", response = List.class)
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public ResponseEntity<List<PostDto>> getAllPostList() throws Exception {
		logger.info("1-------------getAllPostList-----------------------------" + new Date());
		List<PostDto> postlist = postservice.getAllPostList();
		if (postlist.isEmpty() || postlist == null) {
			return new ResponseEntity<List<PostDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PostDto>>(postlist, HttpStatus.OK);
	}

	@ApiOperation(value = "get post", response = List.class)
	@RequestMapping(value = "/post/{postid}", method = RequestMethod.GET)
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable int postid) throws Exception {
		logger.info("1-------------getPostByPostId-----------------------------" + new Date());
		PostDto post = postservice.getPostByPostId(postid);
		if (post == null) {
			return new ResponseEntity<PostDto>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}

	@ApiOperation(value = "modify post", response = NumberResult.class)
	@RequestMapping(value = "/post/{postid}", method = RequestMethod.PUT)
	public ResponseEntity<NumberResult> updatePostInfo(@RequestBody PostDto post) throws Exception {
		logger.info("-------------updatePostInfo-----------------------------" + new Date());
		logger.info("-------------updatePostInfo-----------------------------" + post.toString());
		int confirm = postservice.updatePostInfo(post);
		NumberResult nr = new NumberResult();
		nr.setName("updatepost");
		nr.setState("succ");
		logger.info("-------------updatePost confirm-------------" + confirm);
		if (confirm <= 0) {
			nr.setCount(-1);
			nr.setName("updatePostInfo");
			nr.setState("fail");
			return new ResponseEntity<NumberResult>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
	}

	@ApiOperation(value = "delete post", response = NumberResult.class)
	@RequestMapping(value = "/post/{postid}", method = RequestMethod.DELETE)
	public ResponseEntity<NumberResult> deletePost(@PathVariable int postid) throws Exception {
		logger.info("-------------deletePost-----------------------------" + new Date());

		int confirm = postservice.deletePost(postid);
		NumberResult nr = new NumberResult();
		nr.setName("deletePost");
		nr.setState("succ");

		logger.info("-------------deletePost confirm-------------" + confirm);

		if (confirm <= 0) {
			nr.setCount(-1);
			nr.setName("deletePost");
			nr.setState("fail");
			return new ResponseEntity<NumberResult>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
	}

	@ApiOperation(value = "add post", response = NumberResult.class)
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<NumberResult> addMember(@RequestBody PostDto post) throws Exception {
		logger.info("-------------addPost-------------" + new Date());

		int total = postservice.addPost(post);

		NumberResult nr = new NumberResult();
		nr.setCount(total);
		nr.setName("addPost");
		nr.setState("succ");
		logger.info("-------------addPost-------id------------------" + total);
		if (total <= 0) {
			nr.setCount(-1);
			nr.setName("addPost");
			nr.setState("fail");
			return new ResponseEntity<NumberResult>(nr, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
	}

//	@ApiOperation(value = "beans test", response = List.class)
//	@RequestMapping(value = "/beans", method = RequestMethod.GET)
//	public ResponseEntity<String[]> getBeans() throws Exception{
//		String[] beans = applicationContext.getBeanDefinitionNames(); 
//		return new ResponseEntity<String[]>(beans, HttpStatus.OK);
//	}
//
//	@ApiOperation(value = "회원 정보를 반환한다.", response = Member.class)
//	@RequestMapping(value = "/getMember", method = RequestMethod.POST)
//	public ResponseEntity<Member> getMember(@RequestBody Login info) throws Exception {
//		logger.info("-------------getMember-------------" + new Date());
//		logger.info("-------------getMember-------------" + info.getAccess_token());
//
//		Member member = null;
//		if (loginMap.containsKey(info.getAccess_token())) {
//			member = memberservice.getMember(loginMap.get(info.getAccess_token()).getMid());
//		}
//
//		if (member == null) {
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//		}
//
//		return new ResponseEntity<Member>(member, HttpStatus.OK);
//	}
//
//	@ApiOperation(value = "모든 회원 정보수를 반환", response = NumberResult.class)
//	@RequestMapping(value = "/getTotalMembers", method = RequestMethod.GET)
//	public ResponseEntity<NumberResult> getTotalMembers() throws Exception {
//		logger.info("-------------getTotalMembers-------------" + new Date());
//
//		int total = memberservice.getTotalMembers();
//
//		NumberResult nr = new NumberResult();
//		nr.setCount(total);
//		nr.setName("addEmployee");
//		nr.setState("succ");
//v
//

//
//	@ApiOperation(value = "회원 정보 수정", response = BoolResult.class)
//	@RequestMapping(value = "/updateMember", method = RequestMethod.PUT)
//	public ResponseEntity<BoolResult> updateMember(@RequestBody Member member) throws Exception {
//		logger.info("1-------------updateMember-----------------------------" + new Date());
//		logger.info("1-------------updateMember-----------------------------" + member.toString());
//
//		memberservice.updateMember(member);
//		BoolResult nr = new BoolResult();
//		nr.setName("updateMember");
//		nr.setState("succ");
//		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
//	}
//
//	@ApiOperation(value = "회원 삭제", response = BoolResult.class)
//	@RequestMapping(value = "/deleteMember/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<BoolResult> deleteMember(@PathVariable String id) throws Exception {
//		logger.info("-------------deleteMember-----------------------------" + new Date());
//		// delflag 변경
//		boolean total = memberservice.deleteMember(id);
//		BoolResult nr = new BoolResult();
//		nr.setCount(total);
//		nr.setName("deleteMember");
//		nr.setState("succ");
//		if (!total) {
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
//	}
//
//	// =================================================================================
//	@ApiOperation(value = "로그인", response = BoolResult.class)
//	@RequestMapping(value = "/login", method = RequestMethod.POST) // GET으로 하면 RequestBody 안됨
//	public ResponseEntity<BoolResult> login(@RequestBody Login info) throws Exception {
//		logger.info("-------------login-------------" + new Date());
//		BoolResult nr = new BoolResult();
//		int isLogin = memberservice.login(info);
//		logger.info("-------------login-------------" + "isLogin : " + isLogin);
//
//		if (isLogin == 0) {
//			nr.setState("fail");
//			return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
//		}
//
//		boolean tokenGenerated = false;
//		StringBuilder token = new StringBuilder();
//		token.append(info.getMid());
//		token.append(info.getPwd());
//		loginMap.put(token.toString(), new Login(info.getMid(), info.getPwd()));
//		tokenGenerated = true;
//
//		logger.info("-------------login-------------" + loginMap.get(token.toString()).getMid());
//		logger.info("-------------login-------------" + loginMap.get(token.toString()).getPwd());
//
//		nr.setName(token.toString());
//		if (isLogin > 0 && tokenGenerated) {
//			nr.setState("succ");
//		} else {
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
//	}
//
//	@ApiOperation(value = "아이디 체크", response = BoolResult.class)
//	@RequestMapping(value = "/checkId/{uid}", method = RequestMethod.GET) // GET으로 하면 RequestBody 안됨
//	public ResponseEntity<BoolResult> checkId(@PathVariable String uid) throws Exception {
//		logger.info("-------------checkId-------------" + new Date());
//		logger.info("-------------checkId-------------" + uid);
//		int isExist = memberservice.checkId(uid);
//		logger.info("-------------checkId-------------" + isExist);
//		BoolResult nr = new BoolResult();
//		nr.setName("checkId");
//		if (isExist == 0) { // not exist 사용할 수 있는 아이디
//			nr.setState("true");
//		} else {
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
//	}
//
//	@ApiOperation(value = "알러지 정보 추가", response = BoolResult.class)
//	@RequestMapping(value = "/addAllergies", method = RequestMethod.POST) // GET으로 하면 RequestBody 안됨
//	public ResponseEntity<BoolResult> addAllergies(@RequestBody Member member) throws Exception {
//		logger.info("-------------addAllergies-------------" + new Date());
//		logger.info("-------------addAllergies-------------" + member.getAllergies());
//		logger.info("-------------addAllergies-------------" + member.getMid());
//		String[] allers = member.getAllergies().trim().split(",");
//
//		MemberAllergy ma = null;
//		for (int i = 0; i < allers.length; i++) {
//			ma = new MemberAllergy(member.getMid(), (int) amap.get(allers[i]));
//			memberservice.addAllergies(ma);
//		}
//
//		BoolResult nr = new BoolResult();
//		nr.setName("checkId");
//		nr.setState("true");
//
//		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
//	}
//
//	@ApiOperation(value = "내 알러지 정보 삭제", response = BoolResult.class)
//	@RequestMapping(value = "/deleteMyAllergies/{mid}", method = RequestMethod.DELETE) // GET으로 하면 RequestBody 안됨
//	public ResponseEntity<BoolResult> deleteMyAllergies(@PathVariable String mid) throws Exception {
//		logger.info("-------------deleteMyAllergies-------------" + new Date());
//
//		int result = memberservice.deleteMyAllergies(mid);
//
//		BoolResult nr = new BoolResult();
//		nr.setName("deleteMyAllergies");
//		if (result > 0)
//			nr.setState("succ");
//		else
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//
//		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
//	}
//
//	@ApiOperation(value = "로그아웃", response = BoolResult.class)
//	@RequestMapping(value = "/logout", method = RequestMethod.POST)
//	public ResponseEntity<BoolResult> logout(@RequestBody Login info) {
//		logger.info("-------------logout-------------" + new Date());
//		boolean isLogout = false;
//
//		if (loginMap.containsKey(info.getAccess_token()) && info.getAccess_token() != null) {
//			loginMap.remove(info.getAccess_token());
//			isLogout = true;
//		}
//
//		BoolResult nr = new BoolResult();
//		nr.setName("logout");
//
//		if (isLogout) {
//			nr.setState("succ");
//		} else {
//			nr.setState("fail");
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
//	}
//
//	@ApiOperation(value = "회원 권한 수정", response = BoolResult.class)
//	@RequestMapping(value = "/updateMemAuth", method = RequestMethod.PUT)
//	public ResponseEntity<BoolResult> updateMemAuth(@RequestBody MemAuth memauth) throws Exception {
//		logger.info("1-------------updateMemberAuth-----------------------------" + new Date());
//		boolean total = memberservice.updateMemAuth(memauth);
//		BoolResult nr = new BoolResult();
//		nr.setCount(total);
//		nr.setName("updateMemAuth");
//		nr.setState("succ");
//		if (!total) {
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
//	}
}
