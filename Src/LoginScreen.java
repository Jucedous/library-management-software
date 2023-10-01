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

public class LoginScreen extends JFrame implements ActionListener{
    private JTextField txtUserName = new JTextField(20);
    private JTextField txtPassword = new JTextField(20);
    private JButton btnLogin = new JButton("Login");
    public JButton getBtnLogin() {
        return btnLogin;
    }
    private User user = new User();

    public LoginScreen() {
        this.setSize(300, 150);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.getContentPane().add(new JLabel ("Library Management System"));

        JPanel main = new JPanel(new SpringLayout());
        main.add(new JLabel("Username:"));
        main.add(txtUserName);
        main.add(new JLabel("Password:"));
        main.add(txtPassword);

        SpringUtilities.makeCompactGrid(main,
                2, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        this.getContentPane().add(main);
        this.getContentPane().add(btnLogin);
        this.getBtnLogin().addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String username = txtUserName.getText().trim();
            String password = txtPassword.getText().trim();
            // System.out.print(username);
            user = Application.getInstance().getDataAdapter().loadUser(username, password);
            if (user == null) {
                JOptionPane.showMessageDialog(null, "This user does not exist!");
            }
            else if (username.equals("admin") && password.equals("password"))
            {
                this.dispose();
                Application.getInstance().getAdminView().setVisible(true);
            }
            else {
                Application.getInstance().setCurrentUser(user);
                Application.getInstance().getPersonal_Information_View().setUserInfo(user.getUsername());
                this.setVisible(false);
                Application.getInstance().getMainScreen().setVisible(true);
            }
            /*if(username.equals("admin") && password.equals("password"))
            {
                // System.out.print("1");
                this.dispose();
                Application.getInstance().getAdminView().setVisible(true);
            }
            else
            {
                // System.out.print("2");
                this.dispose();
                Application.getInstance().getMainScreen().setVisible(true);
            }*/
        }
    }
}