package geeky.week3.synchronous.driver;

import geeky.week3.synchronous.config.SynchronousConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldConsumer {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SynchronousConfig.class);
        AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
        System.out.println("Received: " + amqpTemplate.receiveAndConvert());
    }
}
