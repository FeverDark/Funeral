import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransportEdit extends JFrame {
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button1;

    public TransportEdit(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        textField3.setText("");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Поля должны быть заполненными.");
                    return;
                }
                for (int i = 0; i < data.getTransport().length; ++i) {
                    if (textField1.getText().equals(data.getTransport()[i][0].toString())) {
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
                try {
                    if (!textField3.getText().equals("")) Integer.parseInt(textField3.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введено количество мест.");
                    return;
                }
                data.addTransport(textField1.getText(), textField2.getText(), textField3.getText());
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
