package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserVo;

public interface UserDao {
	
	
	public int idCheck(UserVo userVo) throws Exception;
	
	public int userJoin(UserVo userVo) throws Exception;
	
	public UserVo loginUser(UserVo userVo) throws Exception;
	
	



	
	

}
