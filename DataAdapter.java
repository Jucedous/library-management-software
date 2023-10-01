import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableModel;

public class DataAdapter {
    private Connection connection;
    public DataAdapter(Connection connection) {
        this.connection = connection;
    }

    public User loadUser(String username, String password) {
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE UserName = ? AND Password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("Password"));
                resultSet.close();
                statement.close();

                return user;
            }

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
        return null;
    }

    public boolean submitBookOp(LendList ld) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(ld.getBorrowDate() != null)
        {
            Date borrowDate;
            try {
                borrowDate = new java.sql.Date(dateFormat.parse(ld.getBorrowDate()).getTime());
            }
            catch (ParseException p) {
                System.out.println("Date parsing error!");
                p.printStackTrace();
                return false;
            }   
            // System.out.println(borrowDate);
            Date expectedReturDate = sqlDatePlusDays(borrowDate, 90);
            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO LendList VALUES (?, ?, ?, ?, ?)");
                statement.setString(2, ld.getBookName());
                statement.setString(1, ld.getStudentID());
                statement.setDate(3, borrowDate);
                statement.setDate(4, null);
                statement.setDate(5, expectedReturDate);

                statement.execute();    // commit to the database;
                statement.close();

                PreparedStatement statement2 = connection.prepareStatement("UPDATE Book SET Availability = 'Unavailable' WHERE BookName = ?");
                statement2.setString(1, ld.getBookName());

                statement2.execute();    // commit to the database;
                statement2.close();
                return true; // save successfully!
            } catch (SQLException e) {
                System.out.println("Database access error!");
                e.printStackTrace();
                return false;
            }
            
        }
        if(ld.getReturnDate() != null)
        {
            Date returnDate;
            try {
                returnDate = new java.sql.Date(dateFormat.parse(ld.getReturnDate()).getTime());
            }
            catch (ParseException p) {
                System.out.println("Date parsing error!");
                p.printStackTrace();
                return false;
            }
            // System.out.println(returnDate);
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE LendList SET ReturnDate = ? WHERE StudentID = ?");
                statement.setDate(1, returnDate);
                statement.setString(2, ld.getStudentID());

                statement.execute();    // commit to the database;
                statement.close();

                PreparedStatement statement2 = connection.prepareStatement("UPDATE Book SET Availability = 'Available' WHERE BookName = ?");
                statement2.setString(1, ld.getBookName());

                statement2.execute();    // commit to the database;
                statement2.close();
                return true; // save successfully!
            } catch (SQLException e) {
                System.out.println("Database access error!");
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private Date sqlDatePlusDays(Date date, int days) {
            return Date.valueOf(date.toLocalDate().plusDays(days));
    }

    public boolean checkBookName(String bookName, boolean bDate, boolean rDate) {
        try {
                String query = "SELECT * FROM Book WHERE BookName = '" + bookName + "'";
                if(!bDate) query += " AND Availability = 'Available'";
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
                String query = "SELECT * FROM Student WHERE StudentID = " + studentID;

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

    public DefaultTableModel returnBookList() {
        try {                
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
        try {
                
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
                return true; // book deleted successfully!
        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false;
        }
    }
}
