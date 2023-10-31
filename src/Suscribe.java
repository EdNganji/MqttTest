import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Suscribe implements MqttCallback {

MqttClient client;

public Suscribe() {
}

public static void main(String[] args) {
    new Suscribe().SuscribeMessage();
}

public void SuscribeMessage() {
    try {
        String uri = "tcp://test.mosquitto.org:1883";
        //String uri = "tcp://192.168.1.216:1883";
        String clientID = UUID.randomUUID().toString();
        MemoryPersistence persistence = new MemoryPersistence();
        System.out.println("*** uri = "+uri);
        System.out.println("*** UUID = "+clientID);
        client = new MqttClient(uri, clientID, persistence);

        client.connect();
        client.setCallback(this);
        client.subscribe("foo2");
    } catch (MqttException e) {
        e.printStackTrace();
    }
}

@Override
public void connectionLost(Throwable cause) {
    cause.printStackTrace();
}

@Override
public void messageArrived(String topic, MqttMessage message) throws Exception {
   // System.out.println("["+topic+"] "+message); 
    String jsonPayload = new String(message.getPayload());
    
    Capteur capteur = Convert.Conversion(jsonPayload);  
    // Access the fields of the Person object
    System.out.println("Temperature: " + capteur.Temperature );
    System.out.println("Humidité: " + capteur.Humidite );
    Sender sender = new Sender();
    sender.insertvalues(capteur);
    
}



@Override
public void deliveryComplete(IMqttDeliveryToken token) {
    System.out.println("Delivery complete...");
}

}