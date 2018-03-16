package com.usertracker.test;

import java.util.List;

import com.usertracker.dao.UserDAO;
import com.usertracker.model.User;

public class TestUserDAO {
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		
		List<User> userList = userDAO.getAll();
		
		for (User user : userList) {
			System.out.println(user);
		}
		
		System.out.println("-------------------");
		User selectedUser = userDAO.getById(4);
		System.out.println(selectedUser);
		System.out.println("-------------------");
		
		// update test
		selectedUser.setLastName("Batos");
		selectedUser.setEmail("affandos80@gmail.com");
		userDAO.update(selectedUser);
		
		
	}
}
