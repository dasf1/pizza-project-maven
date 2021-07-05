package pizza_project;



import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Connect {

    public static Connection connect() {

        try {

            
            Class.forName("com.mysql.jdbc.Driver");
           
            
      Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/bills_pro"
              , "dasf11", "root");

            return con;
            
            

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }

    }
   
}
