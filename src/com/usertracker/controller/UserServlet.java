package com.usertracker.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usertracker.dao.UserDAO;
import com.usertracker.model.User;

@WebServlet("/userservlet")
public class UserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// read the "command" parameter
		String theCommand = req.getParameter("command");
		
		// if the command is missing, then default to listing users
		if(theCommand == null) {
			theCommand = "LIST";
		}
		
		switch (theCommand) {
			case "LIST":
				listUsers(req, resp);
				break;
			case "ADD":
				addUser(req, resp);
				break;
			case "LOAD":
				loadUser(req, resp);
				break;
			case "UPDATE":
				updateUser(req, resp);
				break;
			case "DELETE":
				deleteUser(req, resp);
				break;
			default:
				listUsers(req, resp);
				break;
		}
		
		// route to the appropriate method
	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
		// read student id from form data
		int id = Integer.parseInt(req.getParameter("userId"));
		
		// delete student from database
		new UserDAO().delete(id);
		
		// send back them to "list-users" page
		listUsers(req, resp);
	}

	private void updateUser(HttpServletRequest req, HttpServletResponse resp) {
		// read user info from form data
		int id = Integer.parseInt(req.getParameter("userId"));
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		
		// create a new user object
		User user = new User(id, firstName, lastName, email);
		
		// perform update on database
		new UserDAO().update(user);
		
		// send it back to do "list-user" page
		listUsers(req, resp);
	}

	private void loadUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// read userId from form data
		String strId = req.getParameter("userId");
		
		// get user from database (from UserDAO)
		User user = new UserDAO().getById(Integer.parseInt(strId));
		
		// place student in the request attribute
		req.setAttribute("THE_USER", user);
		
		// send to update-student-from jsp page
		RequestDispatcher dispatcher = req.getRequestDispatcher("update-user-form.jsp");
		dispatcher.forward(req, resp);
	}

	private void addUser(HttpServletRequest req, HttpServletResponse resp) {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		
		User user = new User(firstName, lastName, email);
		
		new UserDAO().insert(user);
		
		listUsers(req, resp);
	}

	private void listUsers(HttpServletRequest req, HttpServletResponse resp) {
		// get users from UserDAO
		List<User> userList = new UserDAO().getAll();
		
		// add users to the request 
		req.setAttribute("userList", userList);
		
		// send to JSP page (view)
		RequestDispatcher dispatcher = req.getRequestDispatcher("list-users.jsp");
		
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
