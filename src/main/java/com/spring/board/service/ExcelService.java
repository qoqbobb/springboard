package com.spring.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.board.vo.BoardVo;

public interface ExcelService {
	
	
	void excelDown(BoardVo boardVo, HttpServletResponse response) throws Exception;

}
