import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Employer extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;

    public Employer(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.textField5.setText("");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!data.superUser) {
                    JOptionPane.showMessageDialog(new JFrame(), "Только руководство может добавлять сотрудников.");
                    Exit();
                    return;
                }
                if (textField2.getText().isEmpty() || textField3.getText().isEmpty() || textField4.getText().isEmpty() || textField6.getText().isEmpty() || textField7.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Основные поля должны быть заполненными.");
                    return;
                }
                for (int i = 0; i < data.getEmployer().length; ++i) {
                    if (textField6.getText().equals(Objects.toString(data.getEmployer()[i][5], ""))) {
                        JOptionPane.showMessageDialog(new JFrame(), "Логин должен быть уникальным.");
                        return;
                    }
                }
                if (!textField5.getText().isEmpty()) {
                    try {
                        Integer.parseInt(textField5.getText());
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен стаж.");
                        return;
                    }
                }
                data.addEmployer(textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText(), textField6.getText(), textField7.getText());
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
