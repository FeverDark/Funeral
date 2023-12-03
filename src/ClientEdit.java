import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientEdit extends JFrame {
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton addButton;

    public ClientEdit(String title, DB data, Controller control, int src) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        textField1.setText(data.getClient()[src][0].toString());
        textField2.setText(data.getClient()[src][1].toString());
        textField3.setText(data.getClient()[src][2].toString());
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Поля должны быть заполненными.");
                    return;
                }
                for (int i = 0; i < data.getClient().length; ++i) {
                    if (textField1.getText().equals(data.getClient()[i][0].toString())) {
                        JOptionPane.showMessageDialog(new JFrame(), "ID должен быть уникальным.");
                        return;
                    }
                }
                try {
                    Integer.parseInt(textField1.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен ID.");
                    return;
                }
                data.addClient(textField1.getText(), textField2.getText(), textField3.getText());
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
