package geeky.week3.spring.redis.repository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestManual {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("test-redis-config.xml");
		MyDemoRepository myDemo = context.getBean("myDemoRepository", MyDemoRepository.class);
		
		myDemo.helloString("test_key", "value");
	}
}
