package geeky.week3.rabbitmq.rpc;



import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.BasicProperties;

import java.util.UUID;

public class RPCProducer {
    private static final String RABBITMQ_HOST = "10.10.10.2";
    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";
    private String replyQueueName;
    private QueueingConsumer consumer;

    public RPCProducer() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);
        connection = factory.newConnection();
        channel = connection.createChannel();

        replyQueueName = channel.queueDeclare().getQueue();
        consumer = new QueueingConsumer(channel);
        channel.basicConsume(replyQueueName, true, consumer);
    }

    public String call(String message) throws Exception {
        String response = null;
        String corrId = UUID.randomUUID().toString();

        BasicProperties props = new BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", requestQueueName, props, message.getBytes());

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response = new String(delivery.getBody(),"UTF-8");
                break;
            }
        }

        return response;
    }

    public void close() throws Exception {
        connection.close();
    }

    public static void main(String[] argv) {
        RPCProducer rpcProducer = null;
        String response = null;
        try {
            rpcProducer = new RPCProducer();

            System.out.println(" [x] Requesting fib(30)");
            response = rpcProducer.call("30");
            System.out.println(" [.] Got '" + response + "'");
        }
        catch  (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rpcProducer!= null) {
                try {
                    rpcProducer.close();
                }
                catch (Exception ignore) {}
            }
        }
    }
}
