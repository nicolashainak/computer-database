package com.excilys.cdb.binding.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_table")
public class DtoUser {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String username;
	private String password;
	private String role;

	private DtoUser(DtoUserBuilder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.password = builder.password;
		this.role = builder.role;
	}

	public DtoUser() {
		
	}
	public static class DtoUserBuilder{
		private String id;
		private String username;
		private String password;
		private String role;

		public DtoUserBuilder(String id, String username) {
			this.id = id;
			this.username = username;
		}
		public DtoUserBuilder(String username) {
			this.username = username;
		}
		public DtoUserBuilder id(String id) {
			this.id = id;
			return this;
		}
		public DtoUserBuilder password(String password) {
			this.password = password;
			return this;
		}
		public DtoUserBuilder role(String role) {
			this.role = role;
			return this;
		}

		public DtoUser build() {
			return new DtoUser(this);
		}
	}


	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}
	@Override
	public String toString() {
		String res = "User n°" + id +
				" : {" + username +
				", password: " + password +
				", role: " + role + "}";
		return res;
	}

}

