package com.bkip.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDao {
	
	
	private SqlSession sqlSession;
	
	public PersonDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public Person selectPersonById(int id) {
		return this.sqlSession.selectOne("selectbyId", id);
	}

}