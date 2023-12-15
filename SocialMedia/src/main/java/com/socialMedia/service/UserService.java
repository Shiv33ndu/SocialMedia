package com.socialMedia.service;

import java.util.List;

import com.socialMedia.modelEntity.UserInfo;


public interface UserService{
	
	public UserInfo RegisterUser(UserInfo user) throws Exception;
	
	public UserInfo findUserById(Integer userId) throws Exception;
	
	public UserInfo findUserByEmail(String email) throws Exception;
	
	public UserInfo followUser(Integer userId, Integer followUserId) throws Exception;
	
	public List<UserInfo> searchUser(String query);
	
	public UserInfo updateUserDetail(UserInfo updatedUser, Integer userId) throws Exception;
	
	public UserInfo findUserByJwt(String jwt) throws Exception;
	
	public List<UserInfo> findAllUser();
	
}
