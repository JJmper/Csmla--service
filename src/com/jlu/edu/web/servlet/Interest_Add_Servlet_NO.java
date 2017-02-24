package com.jlu.edu.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jlu.edu.dao.UserDaoImpl;
import com.jlu.edu.domain.Interest_data;
import com.jlu.edu.domain.UserMsg;
import com.jlu.edu.service.UserService;

public class Interest_Add_Servlet_NO extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String res = null;
		String Interestnumber = request.getParameter("Interestnumber");
		String Interesttime = request.getParameter("Interesttime");
		String Interestcontent = request.getParameter("Interestcontent");
		Interest_data data = new Interest_data();
		data.setInterestnumber(Interestnumber);
		data.setInteresttime(Long.valueOf(Interesttime));
		data.setInterestcontent(Interestcontent);
		data.setInterestimage("NO");
		UserMsg user = new UserDaoImpl().UserData(data.getInterestnumber());
		data.setInterestname(user.getUsername());
		data.setInterestportrait(user.getUserportrait());
		data.setInterestsex(user.getUsersex());
		res = new UserService().addInterestData(data);
		out.print(res);
		out.flush();
		out.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
