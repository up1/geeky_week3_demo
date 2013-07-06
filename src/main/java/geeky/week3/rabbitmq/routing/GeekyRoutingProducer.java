package geeky.week3.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class GeekyRoutingProducer {
    private static final String EXCHANGE_NAME = "direct_geeky";
    private static final String RABBITMQ_HOST = "10.10.10.2";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String[] severity = {"geeky.jvm", "geeky.java", "geeky.groovy", "geeky.java"};
        String[] messages = {"geeky jvm", "geeky java", "geeky groovy", "geeky java spring"};

        for (int i = 0;i< severity.length; i++ ){
            channel.basicPublish(EXCHANGE_NAME, severity[i], null, messages[i].getBytes());
            System.out.println(" [x] Sent '" + severity[i] + "':'" + messages[i] + "'");
        }

        channel.close();
        connection.close();
    }

    private static String getSeverity(String[] strings){
        if (strings.length < 1)
            return "info";
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
