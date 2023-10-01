import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class DataAdapter {
    private Connection connection;
    public DataAdapter(Connection connection) {
        this.connection = connection;
    }

    public boolean submitBookOp(LendList ld) {
        try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO LendList VALUES (?, ?, ?, ?, ?)");
                statement.setString(1, ld.getBookName());
                statement.setString(2, ld.getStudentID());
                statement.setString(3, ld.getBorrowDate());
                statement.setString(4, ld.getReturnDate());

                statement.execute();    // commit to the database;
                statement.close();
                return true; // save successfully!
        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkBookName(String bookName) {
        try {
                String query = "SELECT * FROM Books WHERE BookName = " + bookName;

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(!resultSet.isBeforeFirst())
                {
                    return false;
                }
                else return true;

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkStudentID(String studentID) {
        try {
                String query = "SELECT * FROM Students WHERE StudentID = " + studentID;

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(!resultSet.isBeforeFirst())
                {
                    return false;
                }
                else return true;
                
        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false;
        }
    }

    public DefaultTableModel returnLendList() {
        // System.out.println("1");
        try {
                // String query = "SELECT * FROM LendList";
                
                DefaultTableModel model = new DefaultTableModel();
                PreparedStatement statement = connection.prepareStatement("SELECT LendList.StudentID, Student.StudentName, LendList.BookName, LendList.BorrowDate, LendList.ReturnDate FROM LendList INNER JOIN Student ON LendList.StudentID = Student.StudentID");
                ResultSet resultSet = statement.executeQuery();
                
                if(!resultSet.isBeforeFirst())
                {
                    
                    return null;
                }
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnsNumber = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= columnsNumber; i++) {
                    model.addColumn(resultSetMetaData.getColumnName(i));
                }
                while (resultSet.next()) {
                    Object[] row = new Object[columnsNumber];
                    for (int i = 1; i <= columnsNumber; i++) {
                        row[i - 1] = resultSet.getObject(i);
                        System.out.println(row[3]);
                    }
                model.addRow(row);
                }
                return model;

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return null;
        }
    }

    public DefaultTableModel returnBookList() {
        // System.out.println("1");
        try {
                // String query = "SELECT * FROM LendList";
                
                DefaultTableModel model = new DefaultTableModel();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Book");
                ResultSet resultSet = statement.executeQuery();
                
                if(!resultSet.isBeforeFirst())
                {
                    
                    return null;
                }
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnsNumber = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= columnsNumber; i++) {
                    model.addColumn(resultSetMetaData.getColumnName(i));
                }
                while (resultSet.next()) {
                    Object[] row = new Object[columnsNumber];
                    for (int i = 1; i <= columnsNumber; i++) {
                        row[i - 1] = resultSet.getObject(i);
                        // System.out.println(row[3]);
                    }
                model.addRow(row);
                }
                return model;

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return null;
        }
    }

    public DefaultTableModel returnShelfList() {
        // System.out.println("1");
        try {
                // String query = "SELECT * FROM LendList";
                
                DefaultTableModel model = new DefaultTableModel();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Shelf WHERE ShelfAvailability = 'Available'");
                ResultSet resultSet = statement.executeQuery();
                
                if(!resultSet.isBeforeFirst())
                {
                    
                    return null;
                }
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnsNumber = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= columnsNumber; i++) {
                    model.addColumn(resultSetMetaData.getColumnName(i));
                }
                while (resultSet.next()) {
                    Object[] row = new Object[columnsNumber];
                    for (int i = 1; i <= columnsNumber; i++) {
                        row[i - 1] = resultSet.getObject(i);
                    }
                model.addRow(row);
                }
                return model;

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return null;
        }
    }

    public boolean addBook(Book book) {
        try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Book VALUES (?, ?)");
                statement.setString(1, book.getBookName());
                statement.setString(2, book.getAvailability());

                statement.execute();    // commit to the database;
                statement.close();
                return true; // book added successfully!
        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBook(Book book) {
        try {
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Book WHERE BookName = ?");
                statement.setString(1, book.getBookName());

                statement.execute();    // commit to the database;
                statement.close();
                return true; // book added successfully!
        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false;
        }
    }
}
