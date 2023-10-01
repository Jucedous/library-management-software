import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView extends JFrame implements ActionListener{
    
    private JButton btnStuOp = new JButton("Borrow/Return a Book");
    private JButton btnLendList = new JButton("Lend List");
    private JButton btnBookOp = new JButton("Add/Delete a Book");
    private JButton btnBookList = new JButton("Book List");
    private JButton btnShelfList = new JButton("Shelf List");
    private JButton btnLogOut = new JButton("Log Out");
    private static DefaultTableModel model = null;

    public AdminView() {
        this.setSize(1000, 300);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        btnStuOp.setPreferredSize(new Dimension(180, 50));
        btnLendList.setPreferredSize(new Dimension(180, 50));
        btnBookOp.setPreferredSize(new Dimension(180, 50));
        btnBookList.setPreferredSize(new Dimension(180, 50));
        btnShelfList.setPreferredSize(new Dimension(180, 50));
        btnLogOut.setPreferredSize(new Dimension(100, 50));

        JLabel title = new JLabel ("Welcome, Library Admin!");
        JPanel panelTitle = new JPanel();
        panelTitle.add(title);
        this.getContentPane().add(panelTitle);

        JPanel panelButton = new JPanel();
        panelButton.add(btnStuOp);
        panelButton.add(btnLendList);
        panelButton.add(btnBookOp);
        panelButton.add(btnBookList);
        panelButton.add(btnShelfList);
        panelButton.add(btnLogOut);

        this.getContentPane().add(panelButton);

        btnStuOp.addActionListener(new ActionListener() { // when controller is simple, we can declare it on the fly
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().getBookOpView().setVisible(true);            
            }
        });
        btnLendList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().getAdminView().dispose();
                Application.getInstance().getLendListView().setVisible(true);
                // System.out.println(model);
                LendListView.receiveData(model);     
            }
        });
        btnBookOp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().getAdminView().dispose();
                Application.getInstance().getBookModView().setVisible(true);            
            }
        });
        btnBookList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().getAdminView().dispose();
                Application.getInstance().getBookListView().setVisible(true);            
            }
        });
        btnShelfList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().getAdminView().dispose();
                Application.getInstance().getShelfListView().setVisible(true);            
            }
        });
        btnLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().getAdminView().dispose();
                Application.getInstance().getLoginScreen().setVisible(true);            
            }
        });

    }
    public void actionPerformed(ActionEvent e) {
        return;
    }
}
