package geeky.week3.asynchronous.driver;

import geeky.week3.asynchronous.config.ConsumerConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AsyncConsumer {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
    }
}
