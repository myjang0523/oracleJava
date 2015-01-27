package utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//MyBatisManager.getSqlSession()....
public class MyBatisManager {
	private static SqlSessionFactory sqlMapper;
	static {
		String resource = "mybatis/Configuration.xml";
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
			sqlMapper =
					new SqlSessionFactoryBuilder().build(reader);
			System.out.println("Mybatis Manager success");
		} catch (IOException e) {
			System.out.println("Mybatis Manager error");
		}
		
	}
	
	public static SqlSessionFactory getSqlSession() {
		System.out.println("SqlSession return");
		return sqlMapper;
	}
}




