package geeky.week3.spring.redis.repository;

import geeky.week3.spring.redis.config.TestRedisConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestManual {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(TestRedisConfig.class);
		MyDemoRepository myDemo = context.getBean("myDemoRepository", MyDemoRepository.class);
		
		myDemo.helloString("test_key", "value");
	}
}
