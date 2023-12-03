import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class EmployerEdit extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;

    public EmployerEdit(String title, DB data, Controller control, int src) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        textField1.setText(Objects.toString(data.getEmployer()[src][0], ""));
        textField2.setText(Objects.toString(data.getEmployer()[src][1], ""));
        textField3.setText(Objects.toString(data.getEmployer()[src][2], ""));
        textField4.setText(Objects.toString(data.getEmployer()[src][3], ""));
        textField5.setText(Objects.toString(data.getEmployer()[src][4], ""));
        textField6.setText(Objects.toString(data.getEmployer()[src][5], ""));
        textField7.setText(Objects.toString(data.getEmployer()[src][6], ""));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty() || textField4.getText().isEmpty() || textField6.getText().isEmpty() || textField7.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Основные поля должны быть заполненными.");
                    textField1.setText(Objects.toString(data.getEmployer()[src][0], ""));
                    textField2.setText(Objects.toString(data.getEmployer()[src][1], ""));
                    textField3.setText(Objects.toString(data.getEmployer()[src][2], ""));
                    textField4.setText(Objects.toString(data.getEmployer()[src][3], ""));
                    textField5.setText(Objects.toString(data.getEmployer()[src][4], ""));
                    textField6.setText(Objects.toString(data.getEmployer()[src][5], ""));
                    textField7.setText(Objects.toString(data.getEmployer()[src][6], ""));
                    return;
                }
                for (int i = 0; i < data.getEmployer().length; ++i) {
                    if (!textField1.getText().equals(data.getEmployer()[src][0].toString())) {
                        JOptionPane.showMessageDialog(new JFrame(), "ID изменять нельзя.");
                        textField1.setText(Objects.toString(data.getEmployer()[src][0], ""));
                        return;
                    }
                }
                try {
                    Integer.parseInt(textField1.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен ID.");
                    textField1.setText(Objects.toString(data.getEmployer()[src][0], ""));
                    return;
                }
                try {
                    Integer.parseInt(textField5.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен стаж.");
                    textField5.setText(Objects.toString(data.getEmployer()[src][4], ""));
                    return;
                }
                //data.addEmployer(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText(), textField6.getText(), textField7.getText());
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
