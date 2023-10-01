import java.awt.Dimension;
import java.awt.Font;
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

public class SearchView extends JFrame implements ActionListener{
    private JTextField txtSearch_Name = new JTextField(5);
    private JButton btnReturn = new JButton("Return");
    private JButton btnSearch = new JButton("Search");
    public JButton getbtnReturn() {
        return btnReturn;
    }
    public JButton getbtnSearch() {
        return btnSearch;
    }
    public SearchView() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(540, 300);
    
        btnReturn.setPreferredSize(new Dimension(120, 50));
        btnSearch.setPreferredSize(new Dimension(120, 50));
    
    
        JLabel title = new JLabel("Search Book");
        title.setFont(new Font("Sans Serif", Font.BOLD, 24));
        JPanel panelTitle = new JPanel();
        panelTitle.add(title);
        this.getContentPane().add(panelTitle);

        JPanel main = new JPanel(new SpringLayout());
        main.add(new JLabel("Book Name:"));
        main.add(txtSearch_Name);

        SpringUtilities.makeCompactGrid(main,
                1, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        this.getContentPane().add(main);
    
        JPanel panelButton = new JPanel();
        panelButton.add(btnReturn);
        panelButton.add(btnSearch);
    
        this.getContentPane().add(panelButton);
        this.getbtnReturn().addActionListener(this);
        this.getbtnSearch().addActionListener(this);
        // btnSearch.addActionListener(new ActionListener() { // when controller is simple, we can declare it on the fly
        //     public void actionPerformed(ActionEvent e) {
        //         Application.getInstance().getSearch_ResultView().setVisible(true);
        //     }
        // });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnReturn) {
            this.setVisible(false);
            Application.getInstance().getMainScreen().setVisible(true);
        } else if (e.getSource() == this.btnSearch) {
            String Search_Name = txtSearch_Name.getText().trim();
            if(!Application.getInstance().getDataAdapter().checkBookName(Search_Name)) {
                JOptionPane.showMessageDialog(null, "Invalid Book Name");
                return;
            }
            Book book = Application.getInstance().getDataAdapter().loadBook(Search_Name);
            Application.getInstance().setCurrentBook(book);
            Application.getInstance().getSearch_ResultView().setbookinfo(book.getBookName(), book.getAvailability());
            this.setVisible(false);
            Application.getInstance().getSearch_ResultView().setVisible(true);
        }
    }
}
