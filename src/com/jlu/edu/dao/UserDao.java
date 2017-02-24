package com.jlu.edu.dao;

import java.util.List;

import com.jlu.edu.domain.Course;
import com.jlu.edu.domain.Emails;
import com.jlu.edu.domain.Interest_comment;
import com.jlu.edu.domain.Interest_data;
import com.jlu.edu.domain.Statistics;
import com.jlu.edu.domain.Statistics_record;
import com.jlu.edu.domain.User;
import com.jlu.edu.domain.UserMsg;

public interface UserDao {
	// 添加用户 用户注册
	public int addUserDao(User user);

	// 登陆用户 返回账户密码信息
	public String loginUserDao(String number);

	// 返回账号信息
	public UserMsg UserData(String number);

	// 是否已经被注册
	public User findBynumberUserDao(String number);

	// 修改密码
	public int modifypasswordUserDao(String number, String oldpassword,
			String newpassword);

	// 忘记密码
	public String forgetpasswordUserDao(String number, String email);

	// 更新Token
	public int updateTokenUserDao(String number, String token);

	public String ReceTokenUserDao(String number);

	// 兴趣圈
	// 增添信息
	public int addInterestData(Interest_data data);// 添加信息成功返回1，失败返回0

	public int addInterest_comment(Interest_comment comment);// 添加评论信息
	// 查询信息

	public List<Interest_data> receInterestData(int start);// 返回指定数目的信息

	public List<Interest_comment> receInterestcomment(int id);// 返回指定信息下的评论

	public int receInterestcomment(Interest_comment comment);

	// 删除信息

	// 课程表
	public int addSyllabus(Course course);

	public int deleteSyllabus(Course course);

	public List<Course> querySyllabus(String usernumber);

	// 生词本
	public int addWordBook(String word, String usernumber);

	public int deleteWordBook(String word, String usernumber);

	public List<String> queryWordBook(String number);

	// 教师发送验证码
	public int addTeacherSendAuth(Statistics statistics);

	// 学生发送验证码
	public int addStudentSendAuth(Statistics statistics);

	// 更新或添加上课情况
	public int addStatistics(Statistics_record statistics_record);

	public int updateStatistics(Statistics_record statistics_record);

	// 筛选符合要求的学生的number
	public List<String> queryRecord(Statistics statistics);
   //教师获取课程表信息
	public List<String> querySimpleSyllabus(String usernumber);
	// 返回单条信息的count
	public int querySimpleStatistics(String usernumber, String name,
			String student);

	// 教师统计学生上课情况
	public List<Statistics_record> queryStatistics(String usernumber,
			String syllabusname);

	// 意见反馈
	public int addFeedback(String text);
    public int addEmails(Emails emails);
    public Emails queryEmails(String teachernumber,String teachername);
    public List<String> queryTeacher(String studentnumber);
    public int updateEmails(Emails emails);
    public int deleteEmails(String  teachernumber);
    public int updateAccount(int account,String teachernumber);
    public int doubleEmails(String teachernumber,String emailname);
    
}
