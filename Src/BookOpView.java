import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class BookOpView extends JFrame {
    private JTextField txtBookName = new JTextField(20);
    private JTextField txtStudentID = new JTextField(20);
    private JTextField txtBorrowDate = new JTextField(20);
    private JTextField txtReturnDate = new JTextField(20);

    private JButton btnReturn = new JButton("Return");
    private JButton btnSubmit = new JButton("Submit");

    private LendList lendList = new LendList();

    public JButton getBtnReturn() {
        return btnReturn;
    }

    public JButton getBtnSubmit() {
        return btnSubmit;
    }

    public JTextField getTxtBookName() {
        return txtBookName;
    }

    public JTextField getTxtStudentID() {
        return txtStudentID;
    }

    public JTextField getTxtBorrowDate() {
        return txtBorrowDate;
    }

    public JTextField getTxtReturnDate() {
        return txtReturnDate;
    }

    public BookOpView() {
        this.setSize(400, 300);
        this.getContentPane().add(new JLabel ("Borrow/Return a Book"));
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        
        JPanel main = new JPanel(new SpringLayout());
        main.add(new JLabel("Book Name"));
        main.add(txtBookName);
        main.add(new JLabel("Student ID"));
        main.add(txtStudentID);
        main.add(new JLabel("Borrow Date"));
        main.add(txtBorrowDate);
        main.add(new JLabel("Return Date"));
        main.add(txtReturnDate);
        
        SpringUtilities.makeCompactGrid(main,
                4, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        JPanel panelButton = new JPanel();

        this.getContentPane().add(main);
        panelButton.add(btnReturn);
        panelButton.add(btnSubmit);
        this.getContentPane().add(panelButton);

        btnReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().getBookOpView().dispose();
                Application.getInstance().getAdminView().setVisible(true);
            }
        });
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkInfo();
            }
        });
    }

    private void checkInfo() {
        String bName = txtBookName.getText().trim();
        String sID = txtStudentID.getText().trim();
        
        if(!Application.getInstance().getDataAdapter().checkBookName(bName, txtBorrowDate.getText().isEmpty(), txtReturnDate.getText().isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "Invalid Book Name or Book is unavailable!");
            return;
        }

        if(!Application.getInstance().getDataAdapter().checkStudentID(sID))
        {
            JOptionPane.showMessageDialog(null, "Invalid Student ID!");
            return;
        }
        String bDate = txtBorrowDate.getText().trim();
        String rDate = txtReturnDate.getText().trim();

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD", Locale.ENGLISH);
        
        lendList.setBookName(bName);
        lendList.setStudentID(txtStudentID.getText().trim());
        if(!txtBorrowDate.getText().isEmpty() && !txtReturnDate.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Conflicting Operation!");
            return;
        }
        if(!txtBorrowDate.getText().isEmpty())
        {
            lendList.setBorrowDate(bDate);
            lendList.setReturnDate(null);
        }
        if(!txtReturnDate.getText().isEmpty())
        {
            lendList.setReturnDate(rDate);
            lendList.setBorrowDate(null);
        }
        
    

        Application.getInstance().getDataAdapter().submitBookOp(lendList);
        JOptionPane.showMessageDialog(null, "Book borrowed/returned successfully!");
        return;
    }


}
