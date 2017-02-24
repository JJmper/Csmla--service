package com.jlu.edu.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jlu.edu.domain.Interest_comment;
import com.jlu.edu.service.UserService;

public class Interest_Add_Comment_Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("建立连接-----");
		String res = null;
		UserService userService=new UserService();
		Interest_comment data = new Interest_comment();
        
		data.setInterestid(Integer.valueOf(request.getParameter("Interestid")));
		data.setInterest_comment_active(request
				.getParameter("Interest_comment_active"));
		data.setInterest_comment_passive(request
				.getParameter("Interest_comment_passive"));
		data.setInterest_comment_content(request
				.getParameter("Interest_comment_content"));
		res = userService.addInterestCommentData(data);
		out.print(res);
		out.flush();
		out.close();
		System.out.println("建立连接结束----->" + res);
	}

}
