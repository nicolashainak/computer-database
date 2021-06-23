package com.excilys.cdb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.excilys.cdb.binding.dto.UserDetailsImpl;

import com.excilys.cdb.binding.mapper.MapperUser;
import com.excilys.cdb.model.User;
import com.excilys.cdb.persistence.UserRepository;

public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private MapperUser mapperUser;
	
	@Autowired
    private UserRepository userRepository;
    
	private static Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);
  
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = mapperUser.toUser(userRepository.findByUsernameLike("%"+username+"%"));
	        return new UserDetailsImpl(user);
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException("User "+e.getMessage()+" not found.");
		}
    }



}
