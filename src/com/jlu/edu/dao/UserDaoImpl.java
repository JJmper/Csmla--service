package com.jlu.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.jlu.edu.dao.util.JdbcUtils;
import com.jlu.edu.domain.Course;
import com.jlu.edu.domain.Emails;
import com.jlu.edu.domain.Interest_comment;
import com.jlu.edu.domain.Interest_data;
import com.jlu.edu.domain.Statistics;
import com.jlu.edu.domain.Statistics_record;
import com.jlu.edu.domain.User;
import com.jlu.edu.domain.UserMsg;

public class UserDaoImpl implements UserDao {
	// 添加用户 用户注册
	@Override
	public int addUserDao(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into user (Usernumber,Username,Userpassword,Usersex,Userportrait,Userclassify,Userschool,Useremail,UserToken) values(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsernumber());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getUserpassword());
			pstmt.setString(4, user.getUsersex());
			pstmt.setString(5, user.getUserportrait());
			pstmt.setString(6, user.getUserclassify());
			pstmt.setString(7, user.getUserschool());
			pstmt.setString(8, user.getUseremail());
			pstmt.setString(9, user.getUserToken());
			int res = pstmt.executeUpdate();
			System.out.println("--断开连接----");
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				System.out.println("--断开连接----");
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	// 登陆
	@Override
	public String loginUserDao(String number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select Userpassword from user where Usernumber=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();
			if (rs == null) {
				return null;
			}
			if (rs.next()) {
				String Userpassword = rs.getString("Userpassword");
				return Userpassword;
			} else {
				return null;
			}

		} catch (SQLException e) {

			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	// 返回账号信息
	@Override
	public UserMsg UserData(String number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserMsg um = new UserMsg();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from user where Usernumber=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				um.setUsername(new String(rs.getString("Username").getBytes(
						"utf-8"), "utf-8"));
				um.setUsernumber(rs.getString("Usernumber"));
				um.setUserportrait(rs.getString("Userportrait"));
				um.setUsersex(rs.getString("Usersex"));
				um.setUserclassify(rs.getString("Userclassify"));
				um.setUserschool(new String(rs.getString("Userschool")
						.getBytes("utf-8"), "utf-8"));

			}
			return um;
		} catch (Exception e) {

			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	// 修改密码
	@Override
	public int modifypasswordUserDao(String number, String oldpassword,
			String newpassword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update user set Userpassword=? where Usernumber=? && Userpassword=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newpassword);
			pstmt.setString(2, number);
			pstmt.setString(3, oldpassword);
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {

			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	// 忘记密码
	@Override
	public String forgetpasswordUserDao(String number, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select Userpassword from user  where Usernumber=? && Useremail=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if (rs == null) {
				return null;
			}
			if (rs.next()) {
				String Userpassword = rs.getString("Userpassword");
				return Userpassword;
			} else {
				return null;
			}
		} catch (SQLException e) {

			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	// 判断用户名存在与否
	@Override
	public User findBynumberUserDao(String number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/*
			 * 一、得到连接
			 */
			conn = JdbcUtils.getConnection();
			/*
			 * 二、准备sql模板，得到pstmt
			 */
			String sql = "select * from user where usernumber=?";
			pstmt = conn.prepareStatement(sql);
			/*
			 * 三、为pstmt中的问号赋值
			 */
			pstmt.setString(1, number);

			/*
			 * 四、执行之
			 */
			rs = pstmt.executeQuery();

			/*
			 * 五、把rs转换成User类型，返回！
			 */
			if (rs == null) {
				return null;
			}
			if (rs.next()) {
				// 转换成User对象，并返回
				// ORM --> 对象关系映射！ hibernate!
				User user = new User();
				user.setUsernumber(rs.getString("Usernumber"));
				user.setUsername(new String(rs.getString("Username").getBytes(
						"utf-8"), "utf-8"));
				user.setUserpassword(rs.getString("Userpassword"));
				user.setUserportrait(rs.getString("Userportrait"));
				user.setUsersex(rs.getString("Usersex"));
				user.setUserschool(new String(rs.getString("Userschool")
						.getBytes("utf-8"), "utf-8"));
				user.setUseremail(rs.getString("Useremail"));
				user.setUserclassify(rs.getString("Userclassify"));
				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	// 更新token
	@Override
	public int updateTokenUserDao(String number, String token) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update user set UserToken=? where Usernumber=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, token);
			pstmt.setString(2, number);
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {

			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	// 返回Token
	@Override
	public String ReceTokenUserDao(String number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from user where Usernumber=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();
			if (rs == null) {
				return null;
			}
			if (rs.next()) {
				return rs.getString("UserToken");
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	// 添加兴趣圈信息
	@Override
	public int addInterestData(Interest_data data) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into interest (Interestnumber,Interestportrait,Interestname,Interestsex,Interestcontent,Interestimage,Interesttime) values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getInterestnumber());
			pstmt.setString(2, data.getInterestportrait());
			pstmt.setString(3, data.getInterestname());
			pstmt.setString(4, data.getInterestsex());
			pstmt.setString(5, data.getInterestcontent());
			pstmt.setString(6, data.getInterestimage());
			pstmt.setTimestamp(7, new Timestamp(data.getInteresttime()));
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	// 添加评论信息
	@Override
	public int addInterest_comment(Interest_comment comment) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into interest_comment (Interestid,Interest_comment_active,Interest_comment_passive,Interest_comment_content,Interest_active_name,Interest_passive_name) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getInterestid());
			pstmt.setString(2, comment.getInterest_comment_active());
			pstmt.setString(3, comment.getInterest_comment_passive());
			pstmt.setString(4, comment.getInterest_comment_content());
			pstmt.setString(5, comment.getInterest_active_name());
			pstmt.setString(6, comment.getInterest_passive_name());
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	// 返回兴趣圈信息
	@Override
	public List<Interest_data> receInterestData(int start) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Interest_data> list = new ArrayList<Interest_data>();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from interest order by Interesttime desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, 5);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Interest_data data = new Interest_data();
				data.setInterestid(rs.getInt("Interestid"));
				data.setInterestnumber(rs.getString("Interestnumber"));
				data.setInterestportrait(rs.getString("Interestportrait"));
				data.setInterestsex(rs.getString("Interestsex"));
				data.setInterestname(new String(rs.getString("Interestname")
						.getBytes("utf-8"), "utf-8"));
				data.setInterestcontent(new String(rs.getString(
						"Interestcontent").getBytes("utf-8"), "utf-8"));
				data.setInterestimage(rs.getString("Interestimage"));
				data.setInteresttime(rs.getTimestamp("Interesttime").getTime());
				UserMsg user=UserData(data.getInterestnumber());
				data.setInterestname(user.getUsername());
				data.setInterestportrait(user.getUserportrait());
				data.setInterestsex(user.getUsersex());
				data.setList(receInterestcomment(data.getInterestid()));
				System.out.println(data.toString());
				list.add(data);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	// 返回评论信息
	@Override
	public List<Interest_comment> receInterestcomment(int id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Interest_comment> list = new ArrayList<Interest_comment>();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from interest_comment where Interestid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Interest_comment comment = new Interest_comment();
				comment.setInterest_commentid(rs.getInt("Interest_commentid"));
				comment.setInterestid(rs.getInt("Interestid"));
				comment.setInterest_comment_active(rs
						.getString("Interest_comment_active"));
				comment.setInterest_comment_passive(rs
						.getString("Interest_comment_passive"));
				comment.setInterest_comment_content(new String(rs.getString(
						"Interest_comment_content").getBytes("utf-8"), "utf-8"));
				comment.setInterest_active_name(new String(rs.getString(
						"Interest_active_name").getBytes("utf-8"), "utf-8"));
				comment.setInterest_passive_name(new String(rs.getString(
						"Interest_passive_name").getBytes("utf-8"), "utf-8"));
				list.add(comment);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	// 返回评论信息
	@Override
	public int receInterestcomment(Interest_comment comment) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int res = 0;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from interest_comment where Interestid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getInterestid());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int temp = rs.getInt("Interest_commentid");
				if (temp > res) {
					res = temp;
				}
			}
			return res;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	// 添加课程
	@Override
	public int addSyllabus(Course course) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into syllabus (Syllabususernumber,Syllabusname,Syllabusteacher,Syllabusplace,Syllabusweek,Syllabusstart,Syllabusstep) values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, course.getUsernumber());
			pstmt.setString(2, course.getName());
			pstmt.setString(3, course.getTeacher());
			pstmt.setString(4, course.getPlace());
			pstmt.setInt(5, course.getWeek());
			pstmt.setInt(6, course.getStart());
			pstmt.setInt(7, course.getStep());
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public int deleteSyllabus(Course course) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "delete from syllabus where Syllabususernumber=?&&Syllabusname=?&&Syllabusteacher=?&&Syllabusplace=?&&Syllabusweek=?&&Syllabusstart=?&&Syllabusstep=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, course.getUsernumber());
			pstmt.setString(2, course.getName());
			pstmt.setString(3, course.getTeacher());
			pstmt.setString(4, course.getPlace());
			pstmt.setInt(5, course.getWeek());
			pstmt.setInt(6, course.getStart());
			pstmt.setInt(7, course.getStep());
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public List<Course> querySyllabus(String usernumber) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from syllabus where Syllabususernumber=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usernumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Course course = new Course();

				course.setName(new String(rs.getString("Syllabusname")
						.getBytes("utf-8"), "utf-8"));
				course.setTeacher(new String(rs.getString("Syllabusteacher")
						.getBytes("utf-8"), "utf-8"));
				course.setPlace(new String(rs.getString("Syllabusplace")
						.getBytes("utf-8"), "utf-8"));
				course.setWeek(rs.getInt("Syllabusweek"));
				course.setStart(rs.getInt("Syllabusstart"));
				course.setStep(rs.getInt("Syllabusstep"));
				list.add(course);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	@Override
	public int addWordBook(String word, String usernumber) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into wordbook (Word,Usernumber) values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, word);
			pstmt.setString(2, usernumber);
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public int deleteWordBook(String word, String usernumber) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "delete from wordbook where Word=?&&Usernumber=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, word);
			pstmt.setString(2, usernumber);
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public List<String> queryWordBook(String number) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from wordbook where Usernumber=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("Word"));
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	@Override
	public int addTeacherSendAuth(Statistics statistics) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into syllabus_teacher (Syllabususernumber,Syllabusname,Syllabusteacher,Syllabusauth,Syllabuslongitude,Syllabuslatitude,Syllabustime) values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, statistics.getUsernumber());
			pstmt.setString(2, statistics.getSyllabusname());
			pstmt.setString(3, statistics.getSyllabusteacher());
			pstmt.setInt(4, statistics.getAuth());
			pstmt.setDouble(5, statistics.getLongitude());
			pstmt.setDouble(6, statistics.getLatitude());
			pstmt.setLong(7, statistics.getTime());
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public int addStudentSendAuth(Statistics statistics) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into syllabus_student (Syllabususernumber,Syllabusname,Syllabusteacher,Syllabusauth,Syllabuslongitude,Syllabuslatitude,Syllabustime) values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, statistics.getUsernumber());
			pstmt.setString(2, statistics.getSyllabusname());
			pstmt.setString(3, statistics.getSyllabusteacher());
			pstmt.setInt(4, statistics.getAuth());
			pstmt.setDouble(5, statistics.getLongitude());
			pstmt.setDouble(6, statistics.getLatitude());
			pstmt.setLong(7, statistics.getTime());
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public List<Statistics_record> queryStatistics(String usernumber,
			String syllabusname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Statistics_record> list = new ArrayList<Statistics_record>();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from syllabus_statistics where Syllabususernumber=?&&Syllabusname=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usernumber);
			pstmt.setString(2, syllabusname);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Statistics_record statistics_record = new Statistics_record();
				statistics_record.setUsernumber(new String(rs.getString(
						"Syllabususernumber").getBytes("utf-8"), "utf-8"));
				statistics_record.setName(new String(rs.getString(
						"Syllabusname").getBytes("utf-8"), "utf-8"));
				statistics_record.setTeacher(new String(rs.getString(
						"Syllabusteacher").getBytes("utf-8"), "utf-8"));
				statistics_record.setStudent(new String(rs.getString(
						"Syllabusstudent").getBytes("utf-8"), "utf-8"));
				statistics_record.setCount(rs.getInt("Syllabusstatistics"));

				list.add(statistics_record);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	@Override
	public int addStatistics(Statistics_record statistics_record) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into syllabus_statistics (Syllabususernumber,Syllabusname,Syllabusteacher,Syllabusstudent,Syllabusstatistics) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, statistics_record.getUsernumber());
			pstmt.setString(2, statistics_record.getName());
			pstmt.setString(3, statistics_record.getTeacher());
			pstmt.setString(4, statistics_record.getStudent());
			pstmt.setInt(5, statistics_record.getCount());
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public int updateStatistics(Statistics_record statistics_record) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update  syllabus_statistics set Syllabusstatistics=? where Syllabususernumber=?&&Syllabusname=?&&Syllabusteacher=?&&Syllabusstudent=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, statistics_record.getCount());
			pstmt.setString(2, statistics_record.getUsernumber());
			pstmt.setString(3, statistics_record.getName());
			pstmt.setString(4, statistics_record.getTeacher());
			pstmt.setString(5, statistics_record.getStudent());
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public int querySimpleStatistics(String usernumber, String name,
			String student) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from syllabus_statistics where Syllabususernumber=?&&Syllabusname=?&&Syllabusstudent=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usernumber);
			pstmt.setString(2, name);
			pstmt.setString(3, student);
			rs = pstmt.executeQuery();
			int res = -1;
			while (rs.next()) {
				res = rs.getInt("Syllabusstatistics");
			}

			return res;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public List<String> queryRecord(Statistics statistics) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		double lon_t = statistics.getLongitude();
		double lat_t = statistics.getLatitude();
		double r = 6370000;
		List<String> list = new ArrayList<String>();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from  syllabus_student where Syllabusname=?&&Syllabusteacher=?&&Syllabusauth=?&&Syllabustime>?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, statistics.getSyllabusname());
			pstmt.setString(2, statistics.getSyllabusteacher());
			pstmt.setInt(3, statistics.getAuth());
			pstmt.setLong(4, statistics.getTime());
			rs = pstmt.executeQuery();
			// x精度 y纬度
			while (rs.next()) {
				double lon_s = rs.getDouble("Syllabuslongitude");
				double lat_s = rs.getDouble("Syllabuslatitude");
				double distance = r
						* Math.acos(Math.cos(lat_s) * Math.cos(lat_t)
								* Math.cos(lon_s - lon_t) + Math.sin(lat_s)
								* Math.sin(lat_t));

				if (distance < 10) {
					list.add(rs.getString("Syllabususernumber"));
				}

			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public int addFeedback(String text) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into feedback (feedback) values(?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, text);

			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public List<String> querySimpleSyllabus(String usernumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select distinct Syllabusname from syllabus where Syllabususernumber=?";
			// String sql = "select * from syllabus where Syllabususernumber=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usernumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = new String(rs.getString("Syllabusname").getBytes(
						"utf-8"), "utf-8");
				list.add(name);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	@Override
	public int addEmails(Emails emails) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			System.out.println("------->"+emails.toString());
			String sql = "insert into emails (teachernumber,teachername,emailname,emailpwd,emailaccount) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emails.getTeachernumber());
			pstmt.setString(2, emails.getTeachername());
			pstmt.setString(3, emails.getEmailname());
			pstmt.setString(4, emails.getEmailpwd());
			pstmt.setInt(5, 0);
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public Emails queryEmails(String teachernumber, String teachername) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from emails where teachernumber=? or teachername=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teachernumber);
			pstmt.setString(2, teachername);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Emails emails = new Emails();
				String tnumber = new String(rs.getString("teachernumber")
						.getBytes("utf-8"), "utf-8");
				String tname = new String(rs.getString("teachername").getBytes(
						"utf-8"), "utf-8");
				String ename = new String(rs.getString("emailname").getBytes(
						"utf-8"), "utf-8");
				String epwd = new String(rs.getString("emailpwd").getBytes(
						"utf-8"), "utf-8");
				int eaccount = rs.getInt("emailaccount");
				emails.setTeachernumber(tnumber);
				emails.setTeachername(tname);
				emails.setEmailname(ename);
				emails.setEmailpwd(epwd);
				emails.setAccount(eaccount);
				return emails;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return null;

	}

	@Override
	public List<String> queryTeacher(String Syllabususernumber) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select distinct Syllabusteacher from syllabus where Syllabususernumber=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Syllabususernumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = new String(rs.getString("Syllabusteacher")
						.getBytes("utf-8"), "utf-8");
				list.add(name);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public int updateEmails(Emails emails) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update emails set teachernumber=?,teachername=?,emailname=?,emailpwd=?,emailaccount=? where teachernumber=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emails.getTeachernumber());
			pstmt.setString(2, emails.getTeachername());
			pstmt.setString(3, emails.getEmailname());
			pstmt.setString(4, emails.getEmailpwd());
			pstmt.setInt(5, 0);
			pstmt.setString(6, emails.getTeachername());
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {

			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	@Override
	public int deleteEmails(String teachernumber) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "delete from emails where teachernumber=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teachernumber);
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public int updateAccount(int account, String teachernumber) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update emails set emailaccount=? where teachernumber=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account);
			pstmt.setString(2, teachernumber);
			int res = pstmt.executeUpdate();
			return res;
		} catch (SQLException e) {

			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}

	@Override
	public int doubleEmails(String teachernumber, String emailname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from emails where teachernumber=? and emailname=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teachernumber);
			pstmt.setString(2, emailname);
			rs = pstmt.executeQuery();
			int temp = 0;
			while (rs.next()) {
				temp++;
			}
			if (temp > 0) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

	}
}
