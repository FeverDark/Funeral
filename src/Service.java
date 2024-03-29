import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Service extends JFrame {
    private JPanel mainPanel;
    private JTextField textField2;
    private JTextField textField3;
    private JButton addButton;

    public Service(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField2.getText().isEmpty() || textField3.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Поля должны быть заполненными.");
                    return;
                }
                try {
                    Integer.parseInt(textField3.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введена цена.");
                    return;
                }
                data.addService(textField2.getText(), textField3.getText());
                Exit();
                control.setUpdate(true);
            }
        });
    }

    private void Exit() {
        this.setVisible(false);
        this.dispose();
    }
}
