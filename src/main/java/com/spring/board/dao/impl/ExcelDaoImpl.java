package com.spring.board.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.ExcelDao;
import com.spring.board.vo.BoardVo;

@Repository
public class ExcelDaoImpl implements ExcelDao {
	
	@Autowired
	SqlSession sqlsession;

	@Override
	public List<BoardVo> selectExcelList(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList("excel.excelList", boardVo);
	}

}
