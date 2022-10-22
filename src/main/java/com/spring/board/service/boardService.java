package com.spring.board.service;

import java.util.List;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;

public interface boardService {

	public String selectTest() throws Exception;

	public List<BoardVo> SelectBoardList(BoardVo boardVo) throws Exception;
	
	public List<CodeVo> SelectCodeList(CodeVo codeVo) throws Exception;
	public List<CodeVo> SelectPhoneList(CodeVo codeVo) throws Exception;

	public BoardVo selectBoard(String boardType, int boardNum) throws Exception;

	public int selectBoardCnt(BoardVo boardVo) throws Exception;

	public int boardInsert(BoardVo boardVo) throws Exception;
	
	public int boardUpdate(BoardVo boardVo);
	
	public int boardDelete(String boardType, int boardNum) throws Exception;

}
