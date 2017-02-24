package com.jlu.edu.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jlu.edu.domain.User;
import com.jlu.edu.service.UserService;
import com.jlu.edu.util.Base64toImage;
import com.jlu.edu.util.InputStream2String;
import com.jlu.edu.util.TokenUtils;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();
		String portrait_path;
		System.out.println("建立连接-----");
		int res = 0;
		// 创建文件项目工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 设置文件上传路径
		String upload = this.getServletContext().getRealPath("/image/");
		// 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
		String temp = System.getProperty("java.io.tmpdir");
		// 设置缓冲区大小为 5M
		factory.setSizeThreshold(1024 * 1024 * 5);
		// 设置临时文件夹为temp
		factory.setRepository(new File(temp));
		// 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

		// 解析结果放在List中
		try {

			@SuppressWarnings("unchecked")
			List<FileItem> list = servletFileUpload.parseRequest(request);
			User user = new User();
			user.setUserToken(TokenUtils.randomString(32));
			for (FileItem item : list) {
				String name = item.getFieldName();

				if (name.contains("Usernumber")) {
					user.setUsernumber(item.getString("utf-8"));
				} else if (name.contains("Username")) {

					user.setUsername(item.getString("utf-8"));

				} else if (name.contains("Userpassword")) {
					user.setUserpassword(item.getString("utf-8"));

				} else if (name.contains("Usersex")) {
					user.setUsersex(item.getString("utf-8"));

				} else if (name.contains("Userclassify")) {
					user.setUserclassify(item.getString("utf-8"));

				} else if (name.contains("Userschool")) {

					user.setUserschool(item.getString("utf-8"));

				} else if (name.contains("Useremail")) {
					System.out.println(item.getSize());
					user.setUseremail(item.getString("utf-8"));

				} else if (name.contains("Userportrait")) {

					portrait_path = System.currentTimeMillis() + ".jpg";
				
					user.setUserportrait(Base64toImage.GenerateImage(upload,
							portrait_path,
							InputStream2String.GetString(item.getInputStream())));
				}
			}
			res = new UserService().regist(user);
			out.print(res);
		} catch (FileUploadException e) {
			e.printStackTrace();
			System.out.println("有错误产生");
			out.print(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.flush();
		out.close();
		System.out.print("建立连接结束----->" + res);
	}

}
