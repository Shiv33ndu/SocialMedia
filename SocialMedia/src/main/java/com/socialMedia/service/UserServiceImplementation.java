package com.socialMedia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialMedia.config.JwtProvider;
import com.socialMedia.constants.ErrorCodeConstants;
import com.socialMedia.exceptionHandling.UserException;
import com.socialMedia.modelEntity.UserInfo;
import com.socialMedia.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserInfo RegisterUser(UserInfo user) throws UserException {
						
		if(userRepository.findByEmail(user.getEmail()) != null) //means we found an existing user by the same email
			throw new UserException(ErrorCodeConstants.SAME_EMAIL_EXIST);
		
		UserInfo createUser = new UserInfo();
		
		createUser.setEmail(user.getEmail());
		createUser.setFirstName(user.getFirstName());
		createUser.setLastName(user.getLastName());
		createUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(createUser);
		
	}

	@Override
	public UserInfo findUserById(Integer userId) throws UserException {
				
		Optional<UserInfo> user = userRepository.findById(userId);
		
		if(user.isPresent()) {
			return user.get();
		}
		else
			throw new UserException(ErrorCodeConstants.USER_NOT_FOUND + userId);
	}

	@Override
	public UserInfo findUserByEmail(String email) throws UserException {
		
		if(userRepository.findByEmail(email) == null)
			throw new UserException(ErrorCodeConstants.NO_EMAIL_FOUND);
		
		return userRepository.findByEmail(email);
		
	}

	@Override
	public UserInfo followUser(Integer loggedInUser, Integer followedUserId) throws Exception {
		
		UserInfo followingUser = findUserById(loggedInUser); 		//user A who is going to follow To B
		UserInfo followedUser = findUserById(followedUserId);	//user B who is being followed By A
		
		followedUser.getFollowers().add(followingUser.getId()); //added user A in follower list of user B
		followingUser.getFollowing().add(followedUser.getId()); //added user B in following list of user A	
				
		//update the DB for both user
		RegisterUser(followedUser);
		RegisterUser(followingUser);
		
		return followingUser; //returning user A's updated info with increased following list number
	}

	@Override
	public List<UserInfo> searchUser(String query) {
		
		return userRepository.findByQuery(query);
		
	}

	@Override
	public UserInfo updateUserDetail(UserInfo updatedUser, Integer userId) throws Exception {
		
		// only a logged-in person can make changes to his own account 
		// so for that, on controller side we will take JWT and Updated info of the User
		
		// now we will generally get an Id of the user which already exists, means we doing updates 
		
		// so we will just find the User by Id now and append the new updates received from FrontEnd/Controller
		
		if(userRepository.findById(userId).isEmpty())
			throw new Exception(ErrorCodeConstants.USER_NOT_FOUND + userId);
		
		UserInfo currentUser = userRepository.findById(userId).get();
		
		if(updatedUser.getEmail() != null)
			currentUser.setEmail(updatedUser.getEmail());
		
		if(updatedUser.getFirstName() != null)
			currentUser.setFirstName(updatedUser.getFirstName());
		
		if(updatedUser.getLastName() != null)
			currentUser.setLastName(updatedUser.getFirstName());
		
		if(updatedUser.getPassword() != null)
			currentUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));		
		
		userRepository.save(currentUser);		
		
		return currentUser;
	}

	@Override
	public UserInfo findUserByJwt(String jwt) throws UserException {

		//1. now we have Token here, we have to Extract Email out of it 
		
		String email =  JwtProvider.getEmailFromJwtToken(jwt);
		
		//2. now we extract UserInfo using Email
		
		UserInfo user = findUserByEmail(email);
		
		return user;
	}

	@Override
	public List<UserInfo> findAllUser() {
		return userRepository.findAll();
	}
	
	//-- BY SHIVENDU 

}
