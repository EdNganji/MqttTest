import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.text.DecimalFormat;
import java.util.Random;
import com.google.gson.Gson;

public class PahoTest001Producer implements MqttCallback {

MqttClient client;

public PahoTest001Producer() {
}

public static Double rand() {
        Random random = new Random();
        
        // Generate a random double between 26 and 28
        double randomValue = 26 + random.nextDouble() * 2;
        
        // Round the value to two decimal places
        double roundedValue = Math.round(randomValue * 100.0) / 100.0;
        
        // Format the rounded value to have exactly two decimal places
        DecimalFormat df = new DecimalFormat("0.00");
        String formattedValue = df.format(roundedValue);
        
        System.out.println("Random decimal value: " + formattedValue);
        return randomValue;
    }


        public static Integer randhum() {
            Random random = new Random();
            
            int minValue = 20;
            int maxValue = 30;
            
            // Generate a random integer between 20 and 30 (inclusive)
            int randomValue = random.nextInt((maxValue - minValue) + 1) + minValue;
            
            return randomValue;
        }
    

   
  public  static String CreateJson() {
            // Créez une instance de la classe Person avec des variables Java
            Double Temperature = rand();
            int Humidite = randhum();
            Capteur capteur = new Capteur(Temperature, Humidite);
    
            // Utilisez Gson pour convertir l'objet en une chaîne JSON
            Gson gson = new Gson();
            String jsonString = gson.toJson(capteur);
    
            System.out.println("JSON string: " + jsonString);
            return jsonString;
      }
    

public static void main(String[] args) {
    while (true) {
        new PahoTest001Producer().doDemo(CreateJson());

        try {
            // Mettez la boucle en pause pendant 1000 millisecondes (1 seconde)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
     
   // new PahoTest001Producer().doDemo("{\"Temperature\":\"50\",\"Humidite\":\"50\"}");
    
}

public void doDemo(String valeur) {
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

        MqttMessage message = new MqttMessage();
        message.setPayload(valeur.getBytes());
        System.out.println("*** msgId = "+message.getId());
        client.publish("foo2", message);

        client.disconnect();
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
    System.out.println("["+topic+"] "+message);   
}

@Override
public void deliveryComplete(IMqttDeliveryToken token) {
    System.out.println("Delivery complete...");
}

}



