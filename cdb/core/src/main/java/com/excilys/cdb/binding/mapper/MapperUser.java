package com.excilys.cdb.binding.mapper;

import org.springframework.stereotype.Component;

import com.excilys.cdb.binding.dto.DtoUser;
import com.excilys.cdb.binding.dto.DtoUser.DtoUserBuilder;
import com.excilys.cdb.model.User;
import com.excilys.cdb.model.User.UserBuilder;

@Component
public class MapperUser {

	public User toUser(DtoUser dtoUser) {
		int id = Integer.parseInt(dtoUser.getId());
		String username = dtoUser.getUsername();
		UserBuilder builder = new User.UserBuilder(id, username);
		if (! "".equals(dtoUser.getPassword())) {
			builder.password(dtoUser.getPassword());
		}
		if (! "".equals(dtoUser.getRole())) {
			builder.password(dtoUser.getRole());
		}
		return builder.build();
	}

	public DtoUser toUserDTO(User user) {
		String id = Integer.toString(user.getId());
		String username = user.getUsername();
		DtoUserBuilder builder = new DtoUser.DtoUserBuilder(id, username);
		if (! "".equals(user.getPassword())) {
			builder.password(user.getPassword());
		}
		if (! "".equals(user.getRole())) {
			builder.password(user.getRole());
		}
		return builder.build();
	}

}