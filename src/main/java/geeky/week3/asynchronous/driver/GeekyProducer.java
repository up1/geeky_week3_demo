package geeky.week3.asynchronous.driver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class GeekyProducer {
    private static final String TASK_QUEUE_NAME = "hello.world.queue";
    private static final String RABBITMQ_HOST = "10.10.10.2";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        boolean durable = true;

        channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);

        //String message = getMessage(argv);

        String[] messages = {"Hello Geeky 1.", "Hello Geeky 2.", "Hello Geeky 3.", "Hello Geeky 4.", "Hello Geeky 5."};
        for(String message: messages) {
            channel.basicPublish( "", TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }

        channel.close();
        connection.close();
    }
}
