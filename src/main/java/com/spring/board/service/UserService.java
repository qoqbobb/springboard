package com.spring.board.service;

import java.util.List;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.CodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserVo;

public interface UserService {
	
	
	public int idCheck(UserVo userVo) throws Exception;
	
	public int userJoin(UserVo userVo) throws Exception;
	
	public UserVo loginUser(String userId , String userPw) throws Exception;

}
