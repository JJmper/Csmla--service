package com.jlu.edu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.jlu.edu.dao.UserDao;
import com.jlu.edu.dao.UserDaoImpl;
import com.jlu.edu.domain.Course;
import com.jlu.edu.domain.Emails;
import com.jlu.edu.domain.Interest_comment;
import com.jlu.edu.domain.Interest_data;
import com.jlu.edu.domain.Statistics;
import com.jlu.edu.domain.Statistics_record;
import com.jlu.edu.domain.User;
import com.jlu.edu.domain.UserMsg;
import com.jlu.edu.email.DesUtils;
import com.jlu.edu.email.SendMail;
import com.jlu.edu.util.JsonTools;
import com.jlu.edu.util.TokenUtils;

public class UserService {

	private UserDao userDao = new UserDaoImpl();

	/**
	 * 注册功能
	 * 
	 * @param form
	 */
	public int regist(User nuser) {
		/*
		 * 
		 */
		/*
		 * 1. 校验学号
		 */
		User user = userDao.findBynumberUserDao(nuser.getUsernumber());
		if (user != null) {
			return -1;
		}
		/*
		 * 2. 插入User对象
		 */
		int res = userDao.addUserDao(nuser);
		return res;
	}

	/**
	 * 登陆
	 * 
	 * @param number
	 * @param password
	 * @return
	 */
	public String login(String number, String password) {
		/*
		 * 1. 使用number进行校验
		 */
		User user = userDao.findBynumberUserDao(number);
		/*
		 * 判断user是否为null，若为null，说明用户名错误，所以抛出异常
		 */
		if (user == null) {
			return "-1"; // 用户名不存在
		}
		/*
		 * 判断form和user密码是否相同 若不同，说明密码错误，所以抛出异常
		 */
		if (!password.equals(user.getUserpassword())) {
			return "0"; // 用户密码错误
		}

		int res = userDao.updateTokenUserDao(number,
				TokenUtils.randomString(32));
		/*
		 * 根据res的数值判断是否更新成功Token
		 */
		String rece = null;
		if (res == 1) {
			rece = userDao.ReceTokenUserDao(number);
		}
		return rece;// 成功匹配

	}

	public String modifypasswordUserDao(String number, String oldpassword,
			String newpassword) {
		int res = userDao.modifypasswordUserDao(number, oldpassword,
				newpassword);
		if (res == 1) {
			return "1";
		} else {
			return "0";
		}

	}

	/**
	 * 忘记密码
	 * 
	 * @param number
	 * @param email
	 * @return
	 */
	public String forgetpassword(String number, String email) {
		DesUtils des = null;
		try {
			des = new DesUtils("csmla");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String password = userDao.forgetpasswordUserDao(number, email);
		if (password == null)
			return "0";

		boolean flag = false;
		try {
			flag = SendMail.send_163(email, des.decrypt(password));
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (flag == true) {
			return "1";
		} else {
			return "-1";
		}

	}

	// 添加兴趣圈信息
	public String addInterestData(Interest_data data) {
		int res = userDao.addInterestData(data);
		if (res == 1) {
			return "1";
		} else {
			return "0";
		}

	}

	// 添加评论信息
	public String addInterestCommentData(Interest_comment comment) {
		UserMsg active = userDao.UserData(comment.getInterest_comment_active());
		UserMsg passive = userDao.UserData(comment.getInterest_comment_passive());
		comment.setInterest_active_name(active.getUsername());
		comment.setInterest_passive_name(passive.getUsername());
		int res = userDao.addInterest_comment(comment);
		if (res == 0) {
			return "-1";
		} else {
			comment.setInterest_commentid(userDao.receInterestcomment(comment));
			return JsonTools.createJsonString("comment", comment);
		}
	}

	// 获取兴趣圈信息
	public String receInterestData(String start) {
		List<Interest_data> list = userDao.receInterestData(Integer.valueOf(start));
		if (list != null) {
			return JsonTools.createJsonString("interest", list);
		} else {
			return null;
		}
	}

	// 获取评论信息
	public String receInterestData(int id) {
		List<Interest_comment> list = userDao.receInterestcomment(id);
		if (list != null) {
			return JsonTools.createJsonString("interest_comment", list);
		} else {
			return null;
		}
	}

	// 获取用户信息
	public String receUserData(String number) {
		UserMsg um = userDao.UserData(number);
		if (um != null) {
			return JsonTools.createJsonString("User_data", um);
		} else {
			return null;
		}
	}

	// 课程表
	public String addsyllabusData(Course course) {
		int res = userDao.addSyllabus(course);
		System.out.println("--" + res);
		if (res == 1) {
			return "1";
		} else {
			return "-1";
		}
	}

	public String deletesyllabusData(Course course) {
		int res = userDao.deleteSyllabus(course);
		System.out.println(course.toString());
		System.out.println(res);
		if (res == 1) {
			return "1";
		} else {
			return "-1";
		}
	}

	public String querysyllabusData(String usernumber) {
		List<Course> list = userDao.querySyllabus(usernumber);

		if (list != null) {
			return JsonTools.createJsonString("syllabus", list);
		} else {
			return null;
		}
	}

	// 生词本
	public String addWordbookData(String word, String usernumber) {
		int res = userDao.addWordBook(word, usernumber);

		if (res == 1) {
			return "1";
		} else {
			return "-1";
		}
	}

	public String deleteWordbookData(String word, String usernumber) {
		int res = userDao.deleteWordBook(word, usernumber);

		if (res == 1) {
			return "1";
		} else {
			return "-1";
		}
	}

	public String queryWordbookData(String usernumber) {
		List<String> list = userDao.queryWordBook(usernumber);
		if (list != null) {
			return JsonTools.createJsonString("wordbook", list);
		} else {
			return null;
		}
	}

	// 教师添加课堂信息
	public String addTeacherSendAuth(Statistics statistics) {
		int res = userDao.addTeacherSendAuth(statistics);
		if (res == 1) {
			timer(statistics);
			return "1";
		} else {
			return "-1";
		}
	}

	// 学生添加课堂信息
	public String addStudentSendAuth(Statistics statistics) {
		int res = userDao.addStudentSendAuth(statistics);
		if (res == 1) {
			return "1";
		} else {
			return "-1";
		}
	}

	// 添加单科科目纪录到数据库
	private void timer(final Statistics statistics) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				// 根据验证码和时间统计用户 相同验证码 学生时间>教师时间筛选出来 经纬度和海拔距离
				List<String> list = userDao.queryRecord(statistics);
				for (String name : list) {
					// 然后根据筛选将签到学生和教师“+1”处理
					Statistics_record statistics_record = new Statistics_record();
					statistics_record.setUsernumber(statistics.getUsernumber());
					statistics_record.setName(statistics.getSyllabusname());
					statistics_record.setTeacher(statistics
							.getSyllabusteacher());
					statistics_record.setStudent(name);
					int sum = userDao.querySimpleStatistics(
							statistics.getUsernumber(),
							statistics.getSyllabusname(), name);
					if (sum == -1) {
						statistics_record.setCount(1);
						userDao.addStatistics(statistics_record);
					} else {
						statistics_record.setCount(sum + 1);
						userDao.updateStatistics(statistics_record);
					}

				}

			}
		}, 1 * 60 * 1000);// 设定指定的时间time,此处为2分钟
	}

	public String queryStatistics(String usernumber, String syllabusname) {
		List<Statistics_record> list = userDao.queryStatistics(usernumber,
				syllabusname);
		if (list != null) {
			return JsonTools.createJsonString("statistics", list);
		} else {
			return null;
		}
	}

	public int addFeedback(String text) {
		return userDao.addFeedback(text);
	}

	public String querySimpleSyllabus(String usernumber) {
		List<String> list = userDao.querySimpleSyllabus(usernumber);
		if (list != null) {
			return JsonTools.createJsonString("syllabus", list);
		} else {
			return null;
		}
	}

	public int addEmails(Emails emails) {
		Emails subemail = userDao.queryEmails(emails.getTeachernumber(),emails.getTeachername());
		if (subemail == null) {
			return userDao.addEmails(emails);
		} else {
			return 2;
		}

	}

	public String queryEmails(String studentnumber) {
		List<String> teachers = userDao.queryTeacher(studentnumber);
		if (teachers.size() == 0) {
			return null;
		}
		List<Emails> list = new ArrayList<Emails>();
		for (String s : teachers) {
			Emails emails = userDao.queryEmails(s,s);//第1个参数随意
			if (emails != null) {
				list.add(emails);
			}

		}
		if (list.size() != 0)
			return JsonTools.createJsonString("email", list);
		return null;

	}

	public String isEmails(String teachernumber) {
		Emails emails = userDao.queryEmails(teachernumber,teachernumber);
		if (emails != null) {
			return JsonTools.createJsonString("email", emails);
		} else {
			return "0";
		}
	}

	public int updateEmails(Emails emails) {
		int res = 0;
		int temp = userDao.doubleEmails(emails.getTeachernumber(),emails.getEmailname());
		if (temp == 0) {
			res = userDao.updateEmails(emails);
		} else {
			res = 2;
		}

		return res;
	}

	public int deleteEmails(String teachernumber) {
		return userDao.deleteEmails(teachernumber);
	}

	public int upDateAcount(String teachernumber) {
		Emails emails = userDao.queryEmails(teachernumber,teachernumber);
		if(emails!=null){
			int acount = emails.getAccount() + 1;
			int res = userDao.updateAccount(acount, teachernumber);
			return res;
		}else{
			return 0;
		}
		
	}

}
