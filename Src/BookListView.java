import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BookListView extends JFrame {
    private JButton btnReturn = new JButton("Return");
    private JButton btnView = new JButton("View Book List");
    private JTable bookListTable = new JTable();
    private static DefaultTableModel model = new DefaultTableModel();

    public BookListView() {
        this.setTitle("Book List");
        this.setSize(400, 500);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        bookListTable.setModel(model);

        this.getContentPane().add(new JScrollPane(bookListTable));
        JPanel panelButton = new JPanel();
        panelButton.add(btnReturn);
        panelButton.add(btnView);
        this.getContentPane().add(panelButton);

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Application.getInstance().getBookListView().dispose();
            Application.getInstance().getAdminView().setVisible(true);
        }
        });

        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            model = Application.getInstance().getDataAdapter().returnBookList();
            bookListTable.setModel(model);
        }
        });
    }
}
