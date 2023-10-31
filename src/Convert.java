import com.google.gson.Gson;


public class Convert {
    public Convert() {
      
    }
   static String jsonString = "{\"Temperature\":\"45\",\"Humidite\":\"50\"}";

    public static Capteur Conversion(String Json) {
        

        // Create a Gson instance
        Gson gson = new Gson();

        // Convert the JSON string to a Person object
        Capteur capteur = gson.fromJson(Json, Capteur.class);

        // Access the fields of the Person object
        //System.out.println("Temperature: " + capteur.Temperature );
        //System.out.println("Humidité: " + capteur.Humidite );
        
        return capteur;
    }
}
