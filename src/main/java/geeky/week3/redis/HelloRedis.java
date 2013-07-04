package geeky.week3.redis;

import java.util.List;

import redis.clients.jedis.Jedis;

public class HelloRedis {

	public static void main(String[] args) {
		helloList1();
		helloList2();
	}
	
	public static void helloBasic() {
		Jedis jedis = new Jedis("localhost");
		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		System.out.println( value );
	}
	
	public static void helloList1() {
		String key = "my_list";
		Jedis jedis = new Jedis("localhost");
		jedis.lpush(key, "a");
		jedis.lpush(key, "b");
		jedis.lpush(key, "c");
		
		List<String> resultList = jedis.lrange(key, 0, -1);
		for (String value : resultList) {
			System.out.println( value );
		}
	}
	
	public static void helloList2() {
		String key = "my_list";
		Jedis jedis = new Jedis("localhost");
		jedis.rpush(key, "a");
		jedis.rpush(key, "b");
		jedis.rpush(key, "c");
		
		List<String> resultList = jedis.lrange(key, 0, -1);
		for (String value : resultList) {
			System.out.println( value );
		}
	}
	
	public static void helloSet() {
		//TODO
	}
	
	public static void helloSortedSet() {
		//TODO
	}

}
