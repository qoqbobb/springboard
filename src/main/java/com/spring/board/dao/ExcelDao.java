package com.spring.board.dao;

import java.util.List;

import com.spring.board.vo.BoardVo;

public interface ExcelDao {
	
	public List<BoardVo> selectExcelList(BoardVo boardVo) throws Exception;

}
