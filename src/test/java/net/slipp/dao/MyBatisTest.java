package net.slipp.dao;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.slipp.domain.users.User;

public class MyBatisTest {
	private static final Logger log = LoggerFactory.getLogger(MyBatisTest.class);
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setup() throws Exception {
		String resource = "mybatis-config-test.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void gettingStarted() {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			User user = session.selectOne("UserMapper.findById", "prettykara");
			log.debug("User : {}", user);
		}

	}

	@Test
	public void insert() throws Exception {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			User user = new User("prettykara2", "2222", "prettykara2", "prettykara2@gmail.com");
			int cnt = session.insert("UserMapper.create", user);
			log.debug("cnt : {}", cnt);
			
			User actual = session.selectOne("UserMapper.findById", user.getUserId());
			log.debug("User : {}", actual);
			assertEquals(user, actual);
		}
	}

}
