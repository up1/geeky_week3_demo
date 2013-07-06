package geeky.week3.hello.driver;

import geeky.week3.hello.config.HelloWorldConfiguration;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Thawatchai Jongsuwanpaisan
 * Date: 7/5/13
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldSpring {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        AmqpTemplate template = context.getBean(AmqpTemplate.class);

        template.convertAndSend("myqueue", "foo");

        String foo = (String) template.receiveAndConvert("myqueue");
        System.out.println(foo);
    }
}
