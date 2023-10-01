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

    private Book currentBook = null;

    public Book getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(Book book) {
        this.currentBook = book;
    }
    
    private User currentUser = null;

    public User getCurrentUser() { return currentUser; }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    
    public LoginScreen loginScreen = new LoginScreen();
    public AdminView adminView = new AdminView();
    public BookOpView bookOpView = new BookOpView();
    public LendListView lendListView = new LendListView();
    public BookModView bookModView = new BookModView();
    public BookListView bookListView = new BookListView();
    public ShelfListView shelfListView = new ShelfListView();
    public MainScreen mainScreen = new MainScreen();
    public SearchView Search = new SearchView();
    public StatusView Status = new StatusView();
    public Personal_Information_View Personal_Information = new Personal_Information_View();
    public Search_ResultView Search_Result = new Search_ResultView();

    public SearchView getSearchView() {
        return Search;
    }

    public StatusView getStatusView() {
        return Status;
    }

    public Personal_Information_View getPersonal_Information_View() {
        return Personal_Information;
    }

    public Search_ResultView getSearch_ResultView() {
        return Search_Result;
    }

    public MainScreen getMainScreen() {
        return mainScreen;
    }

    public LoginScreen getLoginScreen() {
        return loginScreen;
    }

    public AdminView getAdminView() {
        return adminView;
    }

    public BookOpView getBookOpView() {
        return bookOpView;
    }

    public BookModView getBookModView() {
        return bookModView;
    }

    public LendListView getLendListView() {
        return lendListView;
    }

    public BookListView getBookListView() {
        return bookListView;
    }

    public ShelfListView getShelfListView() {
        return shelfListView;
    }

    public DataAdapter getDataAdapter() {
        return dataAdapter;
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
        // Application.getInstance().getAdminView().setVisible(true);
    }
}
