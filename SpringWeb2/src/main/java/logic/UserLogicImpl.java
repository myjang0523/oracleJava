package logic;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pbean.UserInfo;
import vbean.VUserInfo;
import dao.UserDAO;

@Service
public class UserLogicImpl 
	implements UserLogic{
	@Autowired
	private UserDAO userDAO;

	public UserInfo login(VUserInfo userInfo) {
		UserInfo info = new UserInfo(userInfo);
		return this.userDAO.login(info);
	}

	public List<UserInfo> searchByName(String name) {
		if (name == null || name.equals("")) {
			return Collections.emptyList();
		} else {
			return this.userDAO.searchByName(name);
		}
	}

	public void addUser(VUserInfo userInfo) {
		UserInfo info = new UserInfo(userInfo);
		this.userDAO.addUser(info);
		
	}

	public List<UserInfo> searchByIdAjax(String userid) {
		return this.userDAO.searchByIdAjax(userid);
	}
	
	

}
