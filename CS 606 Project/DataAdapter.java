import java.sql.Connection;
public class DataAdapter {
    private Connection connection;
    public DataAdapter(Connection connection) {
        this.connection = connection;
    }
}
