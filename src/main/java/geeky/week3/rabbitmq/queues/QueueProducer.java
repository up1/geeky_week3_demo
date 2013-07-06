package geeky.week3.rabbitmq.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;


public class QueueProducer {
    private static final String TASK_QUEUE_NAME = "task_queue";
    private static final String RABBITMQ_HOST = "10.10.10.2";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        boolean durable = true;

        channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);

        String[] messages = {"first.", "second..", "third...", "forth....", "fifth....."};
        for(String message: messages) {
            channel.basicPublish( "", TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }



        channel.close();
        connection.close();
    }

    private static String getMessage(String[] strings){
        if (strings.length < 1)
            return "Hello World!";
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
