import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class StatusView extends JFrame implements ActionListener{

    private JButton btnReturn = new JButton("Return");
    public JButton getbtnReturn() {
        return btnReturn;
    }
    public StatusView() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(540, 700);
    
        btnReturn.setPreferredSize(new Dimension(120, 50));
    
        JLabel title = new JLabel("My Borrowing Status");
        title.setFont(new Font("Sans Serif", Font.BOLD, 24));
        JPanel panelTitle = new JPanel();
        panelTitle.add(title);
        this.getContentPane().add(panelTitle);

        DefaultTableModel model = new DefaultTableModel(
                    new Object[][]{
                            {"Book 1", "Dec, 01"},
                            {"Book 2", "Dec, 07"},
                    },
                    new Object[]{"Book Name", "Return Date"}
        );
        JTable table = new JTable(model);

        // JTextArea status = new JTextArea();
        // status.setText("Bookname:" + "return date:");
        // JScrollPane window = new JScrollPane(status);
        JScrollPane window = new JScrollPane(table);
        window.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        window.setPreferredSize(new Dimension(400, 500));
        this.getContentPane().add(window);

        JPanel panelButton = new JPanel();
        panelButton.add(btnReturn);
    
        this.getContentPane().add(panelButton);

        this.getbtnReturn().addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnReturn) {
            this.setVisible(false);
            Application.getInstance().getMainScreen().setVisible(true);
        }
    }
}
