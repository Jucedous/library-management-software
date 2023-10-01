import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookModView extends JFrame implements ActionListener {
    private JButton btnReturn = new JButton("Return");
    private JButton btnAdd = new JButton("Add");
    private JButton btnDelete = new JButton("Delete");
    private JTextField txtBookName = new JTextField(20);
    private Book book = new Book();

    public BookModView() {
        this.setSize(300, 150);
        this.getContentPane().add(new JLabel("Add/Delete a Book"));
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        
        JPanel main = new JPanel(new SpringLayout());
        main.add(new JLabel("Book Name"));
        main.add(txtBookName);

        SpringUtilities.makeCompactGrid(main,
                1, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        
        this.getContentPane().add(main);
        JPanel panelButton = new JPanel();
        panelButton.add(btnAdd);
        panelButton.add(btnDelete);
        panelButton.add(btnReturn);
        // this.getContentPane().add(btnAdd);
        // this.getContentPane().add(btnDelete);
        this.getContentPane().add(panelButton);

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Application.getInstance().getLendListView().dispose();
            Application.getInstance().getAdminView().setVisible(true);            
        }
        });
        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = false;
        book.setBookName(txtBookName.getText().trim());
        book.setAvailability("Available");
        if (e.getSource() == btnAdd)
        {
            
            flag = Application.getInstance().getDataAdapter().addBook(book);
            if(flag) {
                JOptionPane.showMessageDialog(null, "Book successfully added!");
                return;
            }
        }
        else if (e.getSource() == btnDelete)
        {
            flag = Application.getInstance().getDataAdapter().deleteBook(book);
            if(flag) {
                JOptionPane.showMessageDialog(null, "Book successfully removed!");
                return;
            }
        }
    }
    
}
