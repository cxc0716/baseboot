package com.cxc.dao;

import com.cxc.domain.HiUser;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * author:chenxinchao
 * date:2016-09-21 15:57
 * desc:com.cxc.dao
 */
@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;

	public HiUser queryUserById(int id) {
		return this.sqlSession.selectOne("queryUserById", id);
	}

}
