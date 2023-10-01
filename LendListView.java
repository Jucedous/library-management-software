import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LendListView extends JFrame {
    private JButton btnReturn = new JButton("Return");
    private JButton btnView = new JButton("View Lend List");
    private JTable lendListTable = new JTable();
    private static DefaultTableModel model = new DefaultTableModel();

    public LendListView() {

        this.setTitle("Lend List");
        this.setSize(400, 500);
        // this.getContentPane().add(new JLabel ("Payment Page"));
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        // int columnsNumber = -1;
        // System.out.println("2");
        // model = Application.getInstance().getDataAdapter().returnLendList();
        /*if(model == null)
        {
            JOptionPane.showMessageDialog(null, "Lend list is empty!");
            return;
        }*/
        lendListTable.setModel(model);

        // JPanel panelTable = new JPanel();
        // panelTable.add();
        this.getContentPane().add(new JScrollPane(lendListTable));
        this.getContentPane().add(btnReturn);
        this.getContentPane().add(btnView);

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Application.getInstance().getLendListView().dispose();
            Application.getInstance().getAdminView().setVisible(true);            
        }
        });

        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            model = Application.getInstance().getDataAdapter().returnLendList();
            lendListTable.setModel(model);
        }
        });
    }
    
    public static void receiveData(DefaultTableModel m){
        model = m;
    }
}
