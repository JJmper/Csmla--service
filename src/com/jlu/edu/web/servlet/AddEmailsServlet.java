package com.jlu.edu.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jlu.edu.domain.Emails;
import com.jlu.edu.service.UserService;

public class AddEmailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String teachernumber = request.getParameter("number");
		String teachername = request.getParameter("name");
		String emailname = request.getParameter("emailname");
		String emailpwd = request.getParameter("emailpwd");
		UserService userService = new UserService();
		Emails emails = new Emails();
		emails.setTeachernumber(teachernumber);
		emails.setTeachername(teachername);
		emails.setEmailname(emailname);
		emails.setEmailpwd(emailpwd);
		System.out.println(emails.toString());
		int res=userService.addEmails(emails);
		response.getWriter().print(res+"");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
