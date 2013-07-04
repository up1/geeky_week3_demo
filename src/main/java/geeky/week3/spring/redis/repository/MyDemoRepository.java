package geeky.week3.spring.redis.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository()
public class MyDemoRepository {

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	public void helloString(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
		// Ugly code
		System.out.println(redisTemplate.opsForValue().get(key));
	}

	public void helloList(String key, String[] values) {
		for (String value : values) {
			redisTemplate.opsForList().leftPush(key, value);
		}
		// Ugly code
		List<String> resultList = redisTemplate.opsForList().range(key, 0, -1);
		for (String value : resultList) {
			System.out.println(value);
		}
	}

	public void helloSet(String key, String value) {
		// TODO
	}

	public void helloSortedSet(String key, String value) {
		// TODO
	}

}
