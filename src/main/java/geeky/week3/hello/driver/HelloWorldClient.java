package geeky.week3.hello.driver;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: Thawatchai Jongsuwanpaisan
 * Date: 7/5/13
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldClient {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ConnectionFactory connectionFactory = new CachingConnectionFactory("10.10.10.2");

        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
        admin.declareQueue(new Queue("myqueue"));

        AmqpTemplate template = new RabbitTemplate(connectionFactory);
        template.convertAndSend("myqueue", "again Hello World!");
    }
}
