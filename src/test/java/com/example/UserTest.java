package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.service.UserService;
import com.example.vo.UserVo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
class UserTest {

	private GenericXmlApplicationContext ctx;
	
	@Autowired
	private UserService userService;
	
	@Disabled @Test
	public void test() {
		assertNotNull(this.ctx);
		//assertNotNull(this.userService);
		//assertNotNull((DataSource)this.ctx.getBean("dataSource"));
	}
	
	@Disabled @Test
	public void test1() {
		UserVo user = this.userService.selectUser("jimin");
		assertEquals("한지민", user.getName());
	}
	
	@Disabled @Test
	public void test2() {
		UserVo user = new UserVo("chulsu","김철수","남","부산");
		int rowcount = this.userService.insertUser(user);
		assertEquals(1, rowcount);
	}
	
	@Disabled @Test
	public void test3() {
		int rowcount = this.userService.deleteUser("jimin");		
	}
	@Disabled @Test
	public void test4() {
		UserVo user = new UserVo("chulsu", "박철수", "남", "광주");
		int rowcount = this.userService.updateUser(user);
		assertEquals(1, rowcount);
	}
	
	@Test
	public void test5() {
		List<UserVo> list = this.userService.selectAllUsers();
		for(UserVo user : list) {
			System.out.println(user);
		}
	}
	@AfterEach
	public void destroy() {
		this.ctx.close();
	}

}
