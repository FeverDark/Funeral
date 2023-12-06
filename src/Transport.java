import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transport extends JFrame {
    private JPanel mainPanel;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button1;

    public Transport(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        textField3.setText("");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!data.superUser) {
                    JOptionPane.showMessageDialog(new JFrame(), "Только руководство может добавлять транспорт.");
                    Exit();
                    return;
                }
                if (textField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Основные поля должны быть заполненными.");
                    return;
                }
                try {
                    if (!textField3.getText().equals("")) Integer.parseInt(textField3.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введено количество мест.");
                    return;
                }
                data.addTransport(textField2.getText(), textField3.getText());
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
