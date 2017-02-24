package com.jlu.edu.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jlu.edu.service.UserService;

public class modifypasswordServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UserService userService=new UserService();
		String res=userService.modifypasswordUserDao(request.getParameter("Usernumber"), request.getParameter("UserOldpassword"),request.getParameter("UserNewpassword"));
		response.getWriter().print(res);
	}

}
