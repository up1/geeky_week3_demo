package geeky.week3.synchronous.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

public class SynchronousConfig {

    protected final String helloWorldQueueName = "hello.world.queue";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("10.10.10.2");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        //The routing key is set to the name of the queue by the broker for the default exchange.
        template.setRoutingKey(this.helloWorldQueueName);
        //Where we will synchronously receive messages from
        template.setQueue(this.helloWorldQueueName);
        return template;
    }

    @Bean
    public Queue helloWorldQueue() {
        return new Queue(this.helloWorldQueueName);
    }
}
