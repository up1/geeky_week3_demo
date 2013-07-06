package geeky.week3.rabbitmq.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class HelloWorldProducer {

    private final static String QUEUE_NAME = "hello";
    private static final String RABBITMQ_HOST = "10.10.10.2";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        boolean durable = false;

        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        String[] strs = {"first.", "second..", "third...", "forth....", "fifth....."};
        for(String message: strs) {
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}
