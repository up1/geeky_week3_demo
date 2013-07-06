package geeky.week3.asynchronous.driver;

import geeky.week3.asynchronous.config.ProducerConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        new AnnotationConfigApplicationContext(ProducerConfiguration.class);
    }
}
