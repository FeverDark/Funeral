import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class Ordering extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private DatePicker datePicker;

    public Ordering(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        textField1.setText("");
        for (int i = 0; i < data.client.length; ++i) {
            comboBox1.addItem(new ComboItem(data.client[i][1].toString(), data.client[i][0].toString()));
        }
        for (int i = 0; i < data.employer.length; ++i) {
            comboBox2.addItem(new ComboItem(data.employer[i][1].toString(), data.employer[i][0].toString()));
        }
        datePicker.setDateToToday();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Основные поля должны быть заполненными.");
                    return;
                }
                if (datePicker.getDate().isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(new JFrame(), "Дата не должна превышать текущую.");
                    return;
                }
                try {
                    Integer.parseInt(textField2.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введена цена.");
                    return;
                }
                data.addOrdering(((ComboItem) comboBox1.getSelectedItem()).getValue(), ((ComboItem) comboBox2.getSelectedItem()).getValue(), datePicker.toString(), textField2.getText(), textField1.getText());
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
