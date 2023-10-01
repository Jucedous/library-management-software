public class LendList {
    private String bookName;
    private String studentID;
    private String borrowDate;
    private String returnDate;
    private String expectedReturnDate;

    public String getBookName() {
        return bookName;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getExpectedReturnDate() {
        return expectedReturnDate;
    }
    
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setStudentID(String stuID) {
        this.studentID = stuID;
    }

    public void setBorrowDate(String bDate) {
        this.borrowDate = bDate;
    }

    public void setReturnDate(String rDate) {
        this.returnDate = rDate;
    }

    public void setExpectedReturnDate(String erDate) {
        this.expectedReturnDate = erDate;
    }
}
