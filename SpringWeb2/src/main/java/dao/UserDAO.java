package dao;

import java.util.List;

import pbean.UserInfo;

public interface UserDAO {
	//로그인
	public UserInfo login(UserInfo userInfo);
	//이름으로 사용자 검색
	public List<UserInfo> 
		searchByName(String name);
	//사용자 추가
	public void addUser(UserInfo userInfo);
	//아이디 중복체크
	public List<UserInfo> 
	searchByIdAjax(String userId);
	
}
