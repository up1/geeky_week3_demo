package geeky.week3.hello.driver;


import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Hello world!
 *
 */
public class HelloWorld
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ConnectionFactory connectionFactory = new CachingConnectionFactory("10.10.10.2");

        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
        admin.declareQueue(new Queue("myqueue"));

        AmqpTemplate template = new RabbitTemplate(connectionFactory);
        template.convertAndSend("myqueue", "Hello World!!");

        String foo = (String) template.receiveAndConvert("myqueue");
        System.out.println(foo);
    }
}
