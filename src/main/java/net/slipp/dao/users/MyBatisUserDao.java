package net.slipp.dao.users;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.slipp.domain.users.User;

public class MyBatisUserDao implements UserDao {
	private static final Logger log = LoggerFactory.getLogger(MyBatisUserDao.class);

	private SqlSession  sqlSession;
	
//	private DataSource dataSource;
	
/*	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("slipp.sql"));
		DatabasePopulatorUtils.execute(populator, dataSource);
		log.debug("database successfully initialized!");
	}*/
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

/*	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}*/
	
	@Override
	public User findById(String userId) {
		return sqlSession.selectOne("UserMapper.findById", userId);
	}

	@Override
	public void create(User user) {
		sqlSession.insert("UserMapper.create", user);
	}

	@Override
	public void update(User user) {
		sqlSession.insert("UserMapper.update", user);
	}

}
