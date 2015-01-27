import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import pbean.Board;


public class MyBatisTest {

	@Test
	public void testList() throws IOException {
		String resource = "mybatis/Configuration.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory sqlMapper =
				new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlMapper.openSession();
		List<Board> list = session.selectList("getBoardList");
		
		for(Board board : list) {
			System.out.println(board.getNum() + "  " + board.getTitle());
		}
		
		session.close();
	}

}
