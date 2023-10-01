import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShelfListView extends JFrame {
    private JButton btnReturn = new JButton("Return");
    private JButton btnView = new JButton("View Shelf List");
    private JTable shelfListTable = new JTable();
    private static DefaultTableModel model = new DefaultTableModel();

    public ShelfListView() {
        this.setTitle("Shelf List");
        this.setSize(400, 300);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.getContentPane().add(new JScrollPane(shelfListTable));
        JPanel panelButton = new JPanel();
        panelButton.add(btnReturn);
        panelButton.add(btnView);
        this.getContentPane().add(panelButton);

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Application.getInstance().getShelfListView().dispose();
            Application.getInstance().getAdminView().setVisible(true);            
        }
        });

        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            model = Application.getInstance().getDataAdapter().returnShelfList();
            shelfListTable.setModel(model);
        }
        });
    }

}
