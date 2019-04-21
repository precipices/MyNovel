package com.wk.mynovel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.wk.dao.UsingMapper;

@RunWith(SpringRunner.class)
// @SpringBootTest
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsingMapperTest {
	@Autowired
	public UsingMapper usingMapper;

	@Test
	@Rollback(false)
	public void insertUsing() {
		usingMapper.insertUsing(2);
//		System.out.println();
	}

	@Test
	public void queryUsingByUserId() {
		System.out.println(usingMapper.queryUsingByUserId(2));
	}
}
