package com.spring.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.HomeController;
import com.spring.board.service.UserService;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.UserVo;
import com.spring.common.CommonUtil;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired 
	boardService boardService;
	
	@Autowired
	UserService userSerivce;
	
	@RequestMapping(value = "/user/userJoin.do", method = RequestMethod.GET)
	public String userJoin(Locale locale , Model model) throws Exception {
		
		CodeVo codeVo = new CodeVo();
		
		List<CodeVo> codeList = new ArrayList<>();
		
		codeList = boardService.SelectPhoneList(codeVo);
		
		model.addAttribute("codeList",codeList);
		
		return "user/userJoin";
	}
	
	@RequestMapping(value = "/user/userCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale,UserVo userVo) throws Exception{

		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = userSerivce.idCheck(userVo);
		
		System.out.println("getUserId() : "+resultCnt);
		
		result.put("success", (resultCnt != 0)?"notNull" : "n");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	@RequestMapping(value = "/user/userJoinAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String userJoinAction(Locale locale,UserVo userVo) throws Exception{

		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = userSerivce.userJoin(userVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	@RequestMapping(value = "/user/userLogin.do", method = RequestMethod.GET)
	public String userLogin(Locale locale , Model model) throws Exception {
		
		return "user/userLogin";
				
	}
	
	@RequestMapping(value = "/user/userLoginAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String userLoginAction(Locale locale,UserVo userVo , HttpServletRequest req) throws Exception{

		HashMap<String, UserVo> result = new HashMap<String, UserVo>();
		CommonUtil commonUtil = new CommonUtil();
		UserVo vo;
		vo = userSerivce.loginUser(userVo.getUserId(), userVo.getUserPw());
		
		
		result.put("success",vo);
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		HttpSession session = req.getSession();
		if(vo ==null) {
			session.setAttribute("login", null);
		}else {
			session.setAttribute("login", vo);
		}
		
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	
	@RequestMapping(value = "/user/userLogoutAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String userLogoutAction(Locale locale,UserVo userVo , HttpServletRequest req) throws Exception{

		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		HttpSession session = req.getSession();
		session.setAttribute("login", null);
		
		result.put("success", ((session.getAttribute("login") == null)?"Y":"N"));
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
	
		
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}

}
