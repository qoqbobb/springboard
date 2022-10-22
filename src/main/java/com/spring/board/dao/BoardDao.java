package com.spring.board.dao;

import java.util.List;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;

public interface BoardDao {

	public String selectTest() throws Exception;

	public List<BoardVo> selectBoardList(BoardVo boardVo) throws Exception;
	
	public List<CodeVo> selectCodeList(CodeVo codeVo) throws Exception;
	public List<CodeVo> selectPhoneList(CodeVo codeVo) throws Exception;

	public BoardVo selectBoard(BoardVo boardVo) throws Exception;

	public int selectBoardCnt(BoardVo boardVo) throws Exception;

	public int boardInsert(BoardVo boardVo) throws Exception;
	
	public int boardUpdate(BoardVo boardVo);
	
	public int boardDelete(BoardVo boardVo);
	
	
	
	

}
