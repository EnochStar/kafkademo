package constant;

/**
 * @author EnochStar
 * @title: KafkaConstants
 * @projectName kafakademo
 * @description: kafka常用配置变量
 * @date 2021/2/27 23:00
 */
public class KafkaConstants {
    public static final String BROKER_LIST = "localhost:9092";
    public static final String CLIENT_ID = "client1";
    public static String GROUP_ID_CONFIG="consumerGroup1";
    private KafkaConstants() {

    }
}
