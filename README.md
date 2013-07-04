Geeky Academe Week 3
================
Develop Spring Framework application with Spring Data :: Redis + XML Configuration

	- Spring Framework 3.2.3
	- Spring Data
	- Spring Test
	- jUnit 4.10
	
Redis configuration in redis-config.xml
-------------------------

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="
   http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
   http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

	<context:component-scan base-package="geeky.week3.spring.redis" />
	<context:property-placeholder location="classpath:/redis.properties" />

	<!-- Jedis ConnectionFactory -->
	<bean id="jedisConnectionFactory"
		class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory"
		p:host-name="${redis.host-name}" p:port="${redis.port}" p:use-pool="true" />

	<!-- redis template definition -->
	<bean id="redisTemplate" class="org.springframework.data.redis.core.RedisTemplate"
		p:connection-factory-ref="jedisConnectionFactory" />

</beans>
```

Manage dependency libraries in pom.xml ( Maven project )
-------------------------

Spring framework repository

```

<repository>
   <id>spring-release</id>
   <name>Spring Maven RELEASE Repository</name>
   <url>http://maven.springframework.org/release</url>
</repository>
```	

Spring Data Redis

```
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-redis</artifactId>
    <version>1.0.5.RELEASE</version>
</dependency>
```



