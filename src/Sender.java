
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
/*import java.sql.ResultSet;*/
import java.sql.Statement;

/**
 *
 * @author postgresqltutorial.com
 */
public class Sender{

    private final String url = "jdbc:postgresql://localhost/meteo";
    private final String user = "postgres";
    private final String password = "admin";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    

    public long insertvalues(Capteur capteur) {
        String SQL = "INSERT INTO bathurst_capteurs(\"Temperature\" , \"Humidite\") "
                + "VALUES(?,?)";

        long id = 0;

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setDouble(1, capteur.Temperature);
            pstmt.setInt(2, capteur.Humidite);

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    
}





