import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JPanel mainPanel;
    private JTextField loginField;
    private JTextField passwordField;
    private JButton enterButton;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel labelText;

    public LoginForm(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        SwingUtilities.getRootPane(enterButton).setDefaultButton(enterButton);
        this.pack();
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean[] log = data.login(loginField.getText(), passwordField.getText());
                if (log[0]) {
                    data.isLogged = log[0];
                    data.superUser = log[1];
                    control.setState(1);
                }
            }
        });
    }
}
