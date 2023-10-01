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


    private MainScreen mainScreen = new MainScreen();

    public MainScreen getMainScreen() {
        return mainScreen;
    }


    private SearchView Search = new SearchView();

    public SearchView getSearchView() {
        return Search;
    }

    private StatusView Status = new StatusView();

    public StatusView getStatusView() {
        return Status;
    }

    private Personal_Information_View Personal_Information = new Personal_Information_View();

    public Personal_Information_View getPersonal_Information_View() {
        return Personal_Information;
    }

    private Search_ResultView Search_Result = new Search_ResultView();

    public Search_ResultView getSearch_ResultView() {
        return Search_Result;
    }

    public DataAdapter getDataAdapter() {
        return dataAdapter;
    }

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
