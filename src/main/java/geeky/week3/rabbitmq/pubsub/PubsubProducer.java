package geeky.week3.rabbitmq.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class PubsubProducer {
    private static final String EXCHANGE_NAME = "logs";
    private static final String RABBITMQ_HOST = "10.10.10.2";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String[] messages = {"first.", "second..", "third...", "forth....", "fifth....."};
        for(String message: messages) {
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }

        channel.close();
        connection.close();
    }

    private static String getMessage(String[] strings){
        if (strings.length < 1)
            return "info: Hello World!";
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
