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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	
	
	//목록
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale
							,Model model
							,PageVo pageVo
							,BoardVo boardVo
							,CodeVo codeVo
							,HttpServletRequest req
							) throws Exception{
		
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		List<CodeVo> codeList = new ArrayList<CodeVo>();
		HttpSession session =  req.getSession();
		
		int page = 1;
		int totalCnt = 0;
		
		if(boardVo.getPageNo() == 0){
			boardVo.setPageNo(page);;
		}
		
		boardList = boardService.SelectBoardList(boardVo);
		totalCnt = boardService.selectBoardCnt(boardVo);
		codeList = boardService.SelectCodeList(codeVo);
		System.out.println(boardList);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", page);
		model.addAttribute("codeList", codeList);
		if (session.getAttribute("login") != null) {			
			model.addAttribute("login", session.getAttribute("login"));
		}
		
		

		
		
		
		
		return "board/boardList";
	}
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardUpdate.do", method = RequestMethod.GET)
	public String boardUpdate(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		
		
		BoardVo boardVo = new BoardVo();
		
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardUpdate";
	}
	
	
	
	
	
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale
							 ,Model model 
							 ,CodeVo codeVo
							 ,HttpServletRequest req) throws Exception{
		
		HttpSession session =  req.getSession();
		List<CodeVo> codeList = new ArrayList<CodeVo>();
		
		codeList = boardService.SelectCodeList(codeVo);
		
		
		model.addAttribute("codeList", codeList);
		
		if (session.getAttribute("login") != null) {			
			model.addAttribute("login", session.getAttribute("login"));
		}
		
		
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale,BoardVo boardVo) throws Exception{
		
		System.out.println("사이즈 : "+ boardVo.getInsertList().size());
		int allCnt = 0;
		int resultCnt = 0;
		int size = boardVo.getInsertList().size()-1;;
		
		for (int i = 0; i <= size; i++) {
			if(boardVo.getInsertList().get(i).getBoardType() != null) {
			
				allCnt +=1;
				
			    BoardVo bVo= new BoardVo();
			    bVo = boardVo.getInsertList().get(i);
			    bVo.setCreator(boardVo.getInsertList().get(0).getCreator());
			    int result = boardService.boardInsert(bVo);
			    resultCnt += result;
			    System.out.println("allCnt : " + allCnt);
			    System.out.println("resultCnt : " + resultCnt);
			}
		}
		
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		result.put("success", (resultCnt == allCnt )?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	//수정
	@RequestMapping(value = "/board/boardUPdate.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardUpdate(Locale locale,BoardVo boardVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardUpdate(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	//삭제
	@RequestMapping(value = "/board/boardDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardDelete(Locale locale,String boardType,int boardNum)throws Exception{
		
		
		System.out.println("타입 : "+boardType);
		System.out.println("번호 : "+boardNum);
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		
		
		
		int resultCnt = boardService.boardDelete(boardType,boardNum);
		
		
		
		result.put("success", (resultCnt > 0)?"Y" : "삭제된 게시물 입니다.");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}

	
	
	//test
		@RequestMapping(value = "/board/boardListTest.do", method = RequestMethod.POST , produces = "text/plain; charset=EUC-KR")
		//@ResponseBody
		public String boardListTest(BoardVo boardVo
								   ,Model model
								   ,@RequestParam(value="arr[]",required=false) List<String> arr)throws Exception {
			
			boardVo = new BoardVo();
			boardVo.setBoardArr(arr);
			
			List<BoardVo> boardList = new ArrayList<BoardVo>();
			
			int page = 1;
			int totalCnt = 0;
			
			if(boardVo.getPageNo() == 0){
				boardVo.setPageNo(page);;
			}
			boardList = boardService.SelectBoardList(boardVo);
			totalCnt = boardService.selectBoardCnt(boardVo);
			
			
			model.addAttribute("boardList", boardList);
			model.addAttribute("totalCnt", totalCnt);
			
			System.out.println(boardList);
					
			return "/board/searchList";
		}
		
	
}
