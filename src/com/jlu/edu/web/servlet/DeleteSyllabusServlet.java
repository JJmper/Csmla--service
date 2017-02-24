package com.jlu.edu.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jlu.edu.domain.Course;
import com.jlu.edu.service.UserService;

public class DeleteSyllabusServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		Course course=new Course();
		course.setUsernumber(request.getParameter("usernumber"));
		course.setName(request.getParameter("name"));
		course.setTeacher(request.getParameter("teacher"));
		course.setPlace(request.getParameter("place"));
		course.setWeek(Integer.valueOf(request.getParameter("week")));
		course.setStart(Integer.valueOf(request.getParameter("start")));
		course.setStep(Integer.valueOf(request.getParameter("step")));
		out.print(new UserService().deletesyllabusData(course));
		out.flush();
		out.close();
	}

}
