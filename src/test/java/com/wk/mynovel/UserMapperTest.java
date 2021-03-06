package com.wk.mynovel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.wk.dao.UserMapper;
@RunWith(SpringRunner.class)
//@SpringBootTest
@MybatisTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {
	@Autowired
	public UserMapper userMapper;

	@Test
	@Rollback(false)
	public void testCheckUser() {
		System.out.println(userMapper.checkUser("wukai", "123456"));
	}
	@Test
	public void queryUserByUserName() {
		System.out.println(userMapper.queryUserByUserName("wukai"));
	}
	@Test
	public void queryUserWithBook() {
		System.out.println(userMapper.queryUserWithBookById(1));
	}
}
