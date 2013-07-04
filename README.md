Geeky Academe Week 3
================
Develop Spring Framework application with Spring Data :: Redis

	- Spring Framework 3.2.3
	- Spring Data
	- Spring Test
	- jUnit 4.10


## Manage dependency libraries in pom.xml ( Maven project )  

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