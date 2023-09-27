import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Application {
    private static Application instance;
    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }
    private Connection connection;

    public Connection getDBConnection() {
        return connection;
    }
    private DataAdapter dataAdapter;
    
    private User currentUser = null;

    public User getCurrentUser() { return currentUser; }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    public LoginScreen loginScreen = new LoginScreen();

    public LoginScreen getLoginScreen() {
        return loginScreen;
    }
    private Application() {
        // create SQLite database connection here!
        try {
            Class.forName("org.sqlite.JDBC");

            String url = "jdbc:sqlite:data.db";

            connection = DriverManager.getConnection(url);
            dataAdapter = new DataAdapter(connection);

        }
        catch (ClassNotFoundException ex) {
            System.out.println("SQLite is not installed. System exits with error!");
            ex.printStackTrace();
            System.exit(1);
        }

        catch (SQLException ex) {
            System.out.println("SQLite database is not ready. System exits with error!" + ex.getMessage());

            System.exit(2);
        }

        // productController = new ProductController(productView);

        // orderController = new OrderController(orderView);

    }


    public static void main(String[] args) {
        Application.getInstance().getLoginScreen().setVisible(true);
    }
}
