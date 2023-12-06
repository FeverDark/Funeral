import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TransportEdit extends JFrame {
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button1;

    public TransportEdit(String title, DB data, Controller control, int src) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        textField1.setText(Objects.toString(data.getTransport()[src][0], ""));
        textField2.setText(Objects.toString(data.getTransport()[src][1], ""));
        textField3.setText(Objects.toString(data.getTransport()[src][2], ""));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!data.superUser) {
                    JOptionPane.showMessageDialog(new JFrame(), "Только руководство может изменять транспорт.");
                    Exit();
                    return;
                }
                if (textField1.getText().equals(Objects.toString(data.getTransport()[src][0], "")) &&
                        textField2.getText().equals(Objects.toString(data.getTransport()[src][1], "")) &&
                        textField3.getText().equals(Objects.toString(data.getTransport()[src][2], ""))) {
                    Exit();
                } else {
                    if (textField1.getText().isEmpty() || textField2.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(new JFrame(), "Основные поля должны быть заполненными.");
                        textField1.setText(Objects.toString(data.getTransport()[src][0], ""));
                        textField2.setText(Objects.toString(data.getTransport()[src][1], ""));
                        return;
                    }
                    for (int i = 0; i < data.getTransport().length; ++i) {
                        if (!textField1.getText().equals(data.getTransport()[src][0].toString())) {
                            JOptionPane.showMessageDialog(new JFrame(), "ID изменять нельзя.");
                            textField1.setText(Objects.toString(data.getTransport()[src][0], ""));
                            return;
                        }
                    }
                    try {
                        Integer.parseInt(textField1.getText());
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен ID.");
                        textField1.setText(Objects.toString(data.getTransport()[src][0], ""));
                        return;
                    }
                    try {
                        if (!textField3.getText().equals("")) Integer.parseInt(textField3.getText());
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(new JFrame(), "Неправильно введено количество мест.");
                        textField3.setText(Objects.toString(data.getTransport()[src][2], ""));
                        return;
                    }
                    data.updateTransport(textField1.getText(), textField2.getText(), textField3.getText());
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
