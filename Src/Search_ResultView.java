import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Search_ResultView extends JFrame implements ActionListener{
    private JButton btnReturn = new JButton("Return");
    private JLabel bookinfo = new JLabel();

    public JButton getbtnReturn() {
        return btnReturn;
    }

    public Search_ResultView() {
        btnReturn.setPreferredSize(new Dimension(100, 40));
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);

        JLabel title = new JLabel("Search result");
        title.setFont(new Font("Sans Serif", Font.BOLD, 24));
        JPanel panelTitle = new JPanel();
        panelTitle.add(title);

        bookinfo.setFont(new Font("Verdana",1,15));
        JPanel userinformation = new JPanel();
        // JTextArea result = new JTextArea();
        // result.setText("Book Name:" + bookinfo + "Availability: Yex/No");
        userinformation.add(bookinfo);

        JPanel panelButton = new JPanel();
        panelButton.add(btnReturn);
        
        this.getContentPane().add(panelTitle);
        this.getContentPane().add(userinformation);
        this.getContentPane().add(panelButton);
        this.getbtnReturn().addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnReturn) {
            this.setVisible(false);
            Application.getInstance().getSearchView().setVisible(true);
        }
    }
    public void setbookinfo(String x, String y) {
        bookinfo.setText("Book Name: " + x + "   Availability: " + y);
    }
}
