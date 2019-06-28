package kr.or.ddit.user.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


public class UserVO {
	@Size(min=2)
	private String name;
	
	@Size(min=4)
	private String userId;
	
	private String alias;
	private String pass;
	private String addr1;
	private String addr2;
	private String zipcd;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date birth;
	
	private String path;
	private String fileName;
	private String birthStr;
	
	public UserVO() {
	}

	public UserVO(String name, String userId, String alias, String pass,
			String addr1, String addr2, String zipcd, Date birth/*String path, String fileName*/) {
		this(name, userId, alias);
		this.name = name;
		this.userId = userId;
		this.alias = alias;
		this.pass = pass;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipcd = zipcd;
		this.birth = birth;
//		this.path = path;
//		this.fileName = fileName;

	}
	
	public String getBirthStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	      
	     if(birth == null){
	        return "";
	     }
	     return sdf.format(birth);  //date타입 String 으로 변환  (sdf.parse() - String 형태를 date타입으로 변환)
	}

	public void setBirthStr(String birthStr) {
		this.birthStr = birthStr;
	}

	public UserVO(String name, String UserId, String alias){
		this.name=name;
		this.userId = UserId;
		this.alias=alias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getZipcd() {
		return zipcd;
	}

	public void setZipcd(String zipcd) {
		this.zipcd = zipcd;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "UserVO [name=" + name + ", userId=" + userId + ", alias="
				+ alias + ", pass=" + pass + ", addr1=" + addr1 + ", addr2="
				+ addr2 + ", zipcd=" + zipcd + ", birth=" + birth + ", path="
				+ path + ", fileName=" + fileName + "]";
	}

	

}
