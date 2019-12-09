package com.zyf.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zyf.bean.User;
import com.zyf.utils.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:applicationContext-redis.xml")
public class WeekTest {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	@Test
	public void test1() {
		
		List<Object> list = new ArrayList<>();
		
		for (int i = 0; i < 50000; i++) {
			User user = new User();
			
			//设置uid
			user.setUid(i+1);
			
			//设置中文姓名
			String uname = StringUtils.getRandomChinese(3);
			user.setUname(uname);
			
			//设置性别
			Random random = new Random();
			String sex = random.nextBoolean()?"男":"女";
			user.setSex(sex);
			
			//设置手机号
			String phone = "13"+StringUtils.getRandomNumber(9);
			user.setPhone(phone);
			
			//设置邮箱号
			int random2 = (int)(Math.random()*20);
			int len = random2<3?random2+3:random2;
			String str = StringUtils.getRandomStr(len);
			String email = StringUtils.getRandomEmailSuffex();
			user.setEmail(str+email);
			
			//设置生日
			String birthday = StringUtils.randomBirthday();
			user.setBirthday(birthday);
			
			list.add(user);
		}
		
		/**
		 * 	JDK的序列化
		 *	耗时227毫秒
		 */
	/*	System.out.println("JDK的序列化");
		long start = System.currentTimeMillis();
		BoundListOperations<String,Object> boundListOps = redisTemplate.boundListOps("jdk");
		boundListOps.leftPush(list);
		long end = System.currentTimeMillis();
		System.out.println("耗时"+(end-start)+"毫秒");*/
		
		
		/**
		 * 	JSON的序列化
		 *	耗时313毫秒
		 */
	/*	System.out.println("JSON的序列化");
		long start1 = System.currentTimeMillis();
		BoundListOperations<String,Object> boundListOps1 = redisTemplate.boundListOps("json");
		boundListOps1.leftPush(list);
		long end1 = System.currentTimeMillis();
		System.out.println("耗时"+(end1-start1)+"毫秒");*/
		
		
		/**
		 * 	Hash的序列化
		 * 	耗时214毫秒
		 */
		System.out.println("Hash的序列化");
		long start2 = System.currentTimeMillis();
		BoundHashOperations<String,Object,Object> boundHashOps = redisTemplate.boundHashOps("hash");
		boundHashOps.put("hash", list);
		long end2 = System.currentTimeMillis();
		System.out.println("耗时"+(end2-start2)+"毫秒");
		
	}
	
}
