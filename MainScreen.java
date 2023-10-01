import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainScreen extends JFrame implements ActionListener{
    private JButton btnSearch = new JButton("Search a Book");
    private JButton btnBorrow_Status = new JButton("My Borrowing Status");
    private JButton btnPersonal_Information = new JButton("Personal Information");
    private JButton btnLog_out = new JButton("Log out");
    public JButton getBtnSearch() {
        return btnSearch;
    }
    public JButton getPersonal_Information() {
        return btnPersonal_Information;
    }
    public JButton getbtnBorrow_Status() {
        return btnBorrow_Status;
    }
    public JButton getbtnLog_out() {
        return btnLog_out;
    }
    public MainScreen() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(540, 300);

        btnSearch.setPreferredSize(new Dimension(120, 50));
        btnBorrow_Status.setPreferredSize(new Dimension(120, 50));
        btnPersonal_Information.setPreferredSize(new Dimension(120, 50));
        btnLog_out.setPreferredSize(new Dimension(100, 30));

        JLabel title = new JLabel("User View");
        title.setFont(new Font("Sans Serif", Font.BOLD, 24));
        JPanel panelTitle = new JPanel();
        panelTitle.add(title);
        this.getContentPane().add(panelTitle);

        JPanel panelButton = new JPanel();
        panelButton.add(btnSearch);
        panelButton.add(btnBorrow_Status);
        panelButton.add(btnPersonal_Information);

        JPanel low_panelButton = new JPanel();
        low_panelButton.add(btnLog_out);

        this.getContentPane().add(panelButton);
        this.getContentPane().add(low_panelButton);
        this.getBtnSearch().addActionListener(this);
        this.getPersonal_Information().addActionListener(this);
        this.getbtnBorrow_Status().addActionListener(this);
        this.getbtnLog_out().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnSearch) {
            this.setVisible(false);
            Application.getInstance().getSearchView().setVisible(true);
        } else if (e.getSource() == this.btnBorrow_Status) {
            this.setVisible(false);
            Application.getInstance().getStatusView().setVisible(true);
        } else if (e.getSource() == this.btnPersonal_Information) {
            this.setVisible(false);
            Application.getInstance().getPersonal_Information_View().setVisible(true);
        } else if (e.getSource() == this.btnLog_out) {
            this.setVisible(false);
            Application.getInstance().getLoginScreen().setVisible(true);
        }
    }
}
