package com.spring.board.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.UserDao;
import com.spring.board.service.UserService;
import com.spring.board.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userdao;

	

	@Override
	public int idCheck(UserVo userVo) throws Exception {
		// TODO Auto-generated method stub
		return userdao.idCheck(userVo);
	}



	@Override
	public int userJoin(UserVo userVo) throws Exception {
		// TODO Auto-generated method stub
		return userdao.userJoin(userVo);
	}



	@Override
	public UserVo loginUser(String userId, String userPw) throws Exception {
		UserVo vo = new UserVo();
		vo.setUserId(userId);
		vo.setUserPw(userPw);
		return userdao.loginUser(vo);
	}

}
