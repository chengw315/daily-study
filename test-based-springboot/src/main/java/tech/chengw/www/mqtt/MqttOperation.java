package tech.chengw.www.mqtt;

import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * description 代码连接MQTT服务器，测试是否服务器是否可用
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/14
 **/
public class MqttOperation {

    public static void main(String[] args) {
        //不重复的clientId
        String clientId = RandomStringUtils.randomAlphabetic(5) + System.currentTimeMillis();
        try {
            //仅查看连接结果，使用非异步客户端+最简单配置即可
            MqttClient mqttClient = new MqttClient("tcp://MyIp:1883", clientId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("admin");
            options.setPassword("chengw".toCharArray());
            mqttClient.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        System.out.println("over");
    }
}
