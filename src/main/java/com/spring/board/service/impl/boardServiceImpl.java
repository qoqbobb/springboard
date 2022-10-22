package com.spring.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;

@Service
public class boardServiceImpl implements boardService{
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectTest();
	}
	
	@Override
	public List<BoardVo> SelectBoardList(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		
		return boardDao.selectBoardList(boardVo);
	}
	
	@Override
	public List<CodeVo> SelectCodeList(CodeVo codeVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectCodeList(codeVo);
	}
	@Override
	public List<CodeVo> SelectPhoneList(CodeVo codeVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectPhoneList(codeVo);
	}
	
	@Override
	public int selectBoardCnt(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCnt(boardVo);
	}
	
	@Override
	public BoardVo selectBoard(String boardType, int boardNum) throws Exception {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.selectBoard(boardVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.boardInsert(boardVo);
	}

	@Override
	public int boardUpdate(BoardVo boardVo) {
		// TODO Auto-generated method stub
		return boardDao.boardUpdate(boardVo);
	}

	@Override
	public int boardDelete(String boardType, int boardNum) throws Exception {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		return boardDao.boardDelete(boardVo);
	}



	
	
}
