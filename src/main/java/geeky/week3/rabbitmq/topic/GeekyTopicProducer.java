package geeky.week3.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class GeekyTopicProducer {
    private static final String EXCHANGE_NAME = "topic_geeky";
    private static final String RABBITMQ_HOST = "10.10.10.2";

    public static void main(String[] argv) {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(RABBITMQ_HOST);

            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            /*String routingKey = getRouting(argv);
            String message = getMessage(argv);*/
            String[] routingKeys = {"geeky.jvm.wolrd", "geeky.java.lang", "geeky.groovy.grails", "geeky.java.spring"};
            String[] messages = {"geeky jvm", "geeky java", "geeky groovy", "geeky java spring"};
            for (int i = 0;i< routingKeys.length; i++ ){
                channel.basicPublish(EXCHANGE_NAME, routingKeys[i], null, messages[i].getBytes());
                System.out.println(" [x] Sent '" + routingKeys[i] + "':'" + messages[i] + "'");
            }



        }
        catch  (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (Exception ignore) {}
            }
        }
    }

    private static String getRouting(String[] strings){
        if (strings.length < 1)
            return "anonymous.info";
        return strings[0];
    }

    private static String getMessage(String[] strings){
        if (strings.length < 2)
            return "Hello World!";
        return joinStrings(strings, " ", 1);
    }

    private static String joinStrings(String[] strings, String delimiter, int startIndex) {
        int length = strings.length;
        if (length == 0 ) return "";
        if (length < startIndex ) return "";
        StringBuilder words = new StringBuilder(strings[startIndex]);
        for (int i = startIndex + 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
