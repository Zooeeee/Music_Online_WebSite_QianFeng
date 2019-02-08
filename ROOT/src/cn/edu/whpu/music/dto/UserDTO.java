package cn.edu.whpu.music.dto;
/**
 * 存放一个用户信息
 * DTO类用于传输JDBC操作的数据；
 * DTO类创建规范：
 * 	1. DTO类中属性个数和类型要与对应操作的数据表中的字段保持一致
 *  2. 提供无参构造器
 *  3. 有参构造
 * 	4. get和set
 * @author SevenEyes
 *
 */
public class UserDTO {
	private int userId;//用户编号
	private String userName;//用户姓名
	private String userPwdString;//用户密码
	private String nick;//用户昵称
	private String sex;//用户性别
	private String pic;//用户头像地址url
	private String desc;//用户个性签名
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(int userId, String userName, String userPwdString, String nick, String sex, String pic,
			String desc) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwdString = userPwdString;
		this.nick = nick;
		this.sex = sex;
		this.pic = pic;
		this.desc = desc;
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwdString() {
		return userPwdString;
	}

	public void setUserPwdString(String userPwdString) {
		this.userPwdString = userPwdString;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", userPwdString=" + userPwdString + ", nick="
				+ nick + ", sex=" + sex + ", pic=" + pic + ", desc=" + desc + "]";
	}
	
}
