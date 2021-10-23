import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Value;

public class ReceiverApp {

    private final static String TASK_QUEUE_NAME = "task_queue";
    @Value("${data.bus.host}")
    private static String host;

    public void read() throws Exception{

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);

        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

        channel.basicQos(1);

        final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Message=" + message);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        boolean autoAck = false;
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, consumerTag -> { });
    }

    public static void main(String[] args) throws Exception {
        new ReceiverApp().read();
    }
}
