import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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
        textField1.setText(Objects.toString(data.client[src][0], ""));
        textField2.setText(Objects.toString(data.client[src][1], ""));
        textField3.setText(Objects.toString(data.client[src][2], ""));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals(Objects.toString(data.client[src][0], "")) && textField2.getText().equals(Objects.toString(data.client[src][1], "")) && textField3.getText().equals(Objects.toString(data.client[src][2], ""))) {
                    Exit();
                } else {
                    if (textField1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(new JFrame(), "Поля должны быть заполненными.");
                        textField1.setText(Objects.toString(data.client[src][0], ""));
                        textField2.setText(Objects.toString(data.client[src][1], ""));
                        textField3.setText(Objects.toString(data.client[src][2], ""));
                        return;
                    }
                    for (int i = 0; i < data.client.length; ++i) {
                        if (!textField1.getText().equals(data.client[src][0].toString())) {
                            JOptionPane.showMessageDialog(new JFrame(), "ID изменять нельзя.");
                            textField1.setText(Objects.toString(data.client[src][0], ""));
                            return;
                        }
                    }
                    try {
                        Integer.parseInt(textField1.getText());
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен ID.");
                        textField1.setText(Objects.toString(data.client[src][0], ""));
                        return;
                    }
                    data.updateClient(textField1.getText(), textField2.getText(), textField3.getText());
                    Exit();
                    control.setUpdate(true);
                }
            }
        });
    }

    private void Exit() {
        this.setVisible(false);
        this.dispose();
    }
}
