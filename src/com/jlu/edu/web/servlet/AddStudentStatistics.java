package com.jlu.edu.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jlu.edu.domain.Statistics;
import com.jlu.edu.service.UserService;

public class AddStudentStatistics extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		Statistics statistics=new Statistics();
		statistics.setUsernumber(request.getParameter("usernumber"));
		statistics.setSyllabusname(request.getParameter("syllabusname"));
		statistics.setSyllabusteacher(request.getParameter("syllabusteacher"));
		statistics.setAuth(Integer.valueOf(request.getParameter("auth")));
		statistics.setLongitude(Double.valueOf(request.getParameter("longitude")));
		statistics.setLatitude(Double.valueOf(request.getParameter("latitude")));
		statistics.setTime(new Date().getTime());
		out.print(new UserService().addStudentSendAuth(statistics));
		out.flush();
		out.close();
		
		
		
	}

}
