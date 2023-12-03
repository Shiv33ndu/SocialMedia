package com.socialMedia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserInfo RegisterUser(UserInfo user) {
		
		UserInfo updatedUser = userRepository.save(user);
		
		return updatedUser;
	}

	@Override
	public UserInfo findUserById(Integer userId) throws Exception {
				
		Optional<UserInfo> user = userRepository.findById(userId);
		
		if(user.isPresent()) {
			return user.get();
		}
		else
			throw new Exception("User doesn't exist with ID : "+ userId);
	}

	@Override
	public UserInfo findUserByEmail(String email) {
		
		return userRepository.findByEmail(email);
		
	}

	@Override
	public UserInfo followUser(Integer userId, Integer followUserId) throws Exception {
		
		UserInfo followingUser = findUserById(userId); 		//user A who is going to follow To B
		UserInfo followUser = findUserById(followUserId);	//user B who is being followed By A
		
		followUser.getFollowers().add(followingUser.getId()); //added user A in follower list of user B
		followingUser.getFollowing().add(followUser.getId()); //added user B in following list of user A	
				
		//update the DB for both user
		RegisterUser(followUser);
		RegisterUser(followingUser);
		
		return followingUser; //returning user A's updated info with increased following list number
	}

	@Override
	public List<UserInfo> searchUser(String query) {
		
		return userRepository.findByQuery(query);
		
	}

	@Override
	public UserInfo updateUserDetail(UserInfo updatedUser, UserInfo existingUser) {
		// TODO Auto-generated method stub
		//we'll implement this as per the need
		return null;
	}
	
	//-- BY SHIVENDU 

}
