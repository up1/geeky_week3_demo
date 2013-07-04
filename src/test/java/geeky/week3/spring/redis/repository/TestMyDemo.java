package geeky.week3.spring.redis.repository;

import static org.junit.Assert.fail;
import geeky.week3.spring.redis.config.TestRedisConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestRedisConfig.class })
public class TestMyDemo {

	@Autowired
	MyDemoRepository myDemoRepository;

	@Test
	public void testString() {
		myDemoRepository.helloString("foo", "bar");
	}

	@Test
	public void testList() {
		myDemoRepository.helloList("my_list", new String[] { "a", "b", "c" });
	}

	@Test
	public void testSet() {
		fail("TODO");
	}

	@Test
	public void testSortedSet() {
		fail("TODO");
	}

}
