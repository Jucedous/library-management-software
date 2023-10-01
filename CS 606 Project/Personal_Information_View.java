import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Personal_Information_View extends JFrame implements ActionListener{
    private JTextField txtName = new JTextField(8);
    private JTextField txtStudent_ID = new JTextField(8);
    private JTextField txtEmail = new JTextField(8);
    private JButton btnReturn = new JButton("Return");
    // private JButton btnLoad = new JButton("Load");
    // private JButton btnSave = new JButton("Save");

    public JButton getbtnReturn() {
        return btnReturn;
    }
    // public JButton getbtnSave() {
    //     return btnSave;
    // }
    // public JButton getbtnLoad() {
    //     return btnLoad;
    // }

    public Personal_Information_View() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 360);
    
        btnReturn.setPreferredSize(new Dimension(120, 50));
        // btnSave.setPreferredSize(new Dimension(120, 50));
        // btnLoad.setPreferredSize(new Dimension(120, 50));
    
        JLabel title = new JLabel("Personal Information");
        title.setFont(new Font("Sans Serif", Font.BOLD, 24));
        JPanel panelTitle = new JPanel();
        panelTitle.add(title);
        this.getContentPane().add(panelTitle);

        JPanel main = new JPanel(new SpringLayout());
        main.add(new JLabel("Name:"));
        main.add(txtName);
        main.add(new JLabel("Student ID:"));
        main.add(txtStudent_ID);
        main.add(new JLabel("Email:"));
        main.add(txtEmail);

        SpringUtilities.makeCompactGrid(main,
                3, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        this.getContentPane().add(main);
        // JScrollPane scrPane = new JScrollPane(main);
        // this.getContentPane().add(scrPane);

        JPanel panelButton = new JPanel();
        panelButton.add(btnReturn);
        // panelButton.add(btnLoad);
        // panelButton.add(btnSave);
    
        this.getContentPane().add(panelButton);

        this.getbtnReturn().addActionListener(this);
        // this.getbtnSave().addActionListener(this);
        // this.getbtnLoad().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnReturn) {
            this.setVisible(false);
            Application.getInstance().getMainScreen().setVisible(true);
        }// } else if (e.getSource() == this.btnLoad) {
        //     JOptionPane.showMessageDialog(null, "not implement yet");
        // } else if (e.getSource() == this.btnSave) {
        //     JOptionPane.showMessageDialog(null, "not implement yet");
        // }
    }
}
