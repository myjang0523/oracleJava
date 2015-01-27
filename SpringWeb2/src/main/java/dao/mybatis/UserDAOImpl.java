package dao.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import pbean.UserInfo;
import dao.UserDAO;

@Repository
public class UserDAOImpl 
	extends SqlSessionDaoSupport 
	implements UserDAO {

	public UserInfo login(UserInfo userInfo) {
		return getSqlSession().selectOne("login", userInfo);
	}

	public List<UserInfo> searchByName(String name) {
		return getSqlSession().selectList("searchByName", name);
	}

	public void addUser(UserInfo userInfo) {
		getSqlSession().insert("addUserInfo", userInfo);
	}

	public List<UserInfo> searchByIdAjax(String userId) {
		return getSqlSession().selectList("searchById", userId);
	}

}
