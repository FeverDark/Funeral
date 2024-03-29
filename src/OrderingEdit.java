import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Objects;

public class OrderingEdit extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField3;
    private DatePicker datePicker;

    public OrderingEdit(String title, DB data, Controller control, int src) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        textField3.setText(Objects.toString(data.ordering[src][0], ""));
        textField2.setText(Objects.toString(data.ordering[src][4], ""));
        textField1.setText(Objects.toString(data.ordering[src][5], ""));
        for (int i = 0; i < data.client.length; ++i) {
            comboBox1.addItem(new ComboItem(data.client[i][1].toString(), data.client[i][0].toString()));
        }
        comboBox1.setSelectedIndex(Integer.parseInt(data.ordering[src][1].toString()) - 1);
        for (int i = 0; i < data.employer.length; ++i) {
            comboBox2.addItem(new ComboItem(data.employer[i][1].toString(), data.employer[i][0].toString()));
        }
        comboBox2.setSelectedIndex(Integer.parseInt(data.ordering[src][2].toString()) - 1);
        datePicker.setDate(LocalDate.parse(data.ordering[src][3].toString()));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField3.getText().equals(data.ordering[src][0].toString()) && textField2.getText().equals(data.ordering[src][4].toString()) &&
                        textField1.getText().equals(Objects.toString(data.ordering[src][5], "")) &&
                        ((ComboItem) comboBox1.getSelectedItem()).getValue().equals(data.ordering[src][1].toString()) &&
                        ((ComboItem) comboBox2.getSelectedItem()).getValue().equals(data.ordering[src][2].toString()) &&
                        datePicker.toString().equals(data.ordering[src][3].toString())) {
                    Exit();
                } else {
                    if (textField3.getText().isEmpty() || textField2.getText().isEmpty() || datePicker.getDate() == null) {
                        JOptionPane.showMessageDialog(new JFrame(), "Основные поля должны быть заполненными.");
                        textField3.setText(Objects.toString(data.ordering[src][0], ""));
                        textField2.setText(Objects.toString(data.ordering[src][4], ""));
                        textField1.setText(Objects.toString(data.ordering[src][5], ""));
                        comboBox1.setSelectedIndex(Integer.parseInt(data.ordering[src][1].toString()) - 1);
                        comboBox2.setSelectedIndex(Integer.parseInt(data.ordering[src][2].toString()) - 1);
                        datePicker.setDate(LocalDate.parse(data.ordering[src][3].toString()));
                        return;
                    }
                    for (int i = 0; i < data.ordering.length; ++i) {
                        if (!textField3.getText().equals(data.ordering[src][0].toString())) {
                            JOptionPane.showMessageDialog(new JFrame(), "Номер заказа изменять нельзя.");
                            textField3.setText(Objects.toString(data.ordering[src][0], ""));
                            return;
                        }
                    }
                    if (datePicker.getDate().isAfter(LocalDate.now())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Дата не должна превышать текущую.");
                        datePicker.setDate(LocalDate.parse(data.ordering[src][3].toString()));
                        return;
                    }
                    try {
                        Integer.parseInt(textField3.getText());
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен номер заказа.");
                        textField3.setText(Objects.toString(data.ordering[src][0], ""));
                        return;
                    }
                    try {
                        Integer.parseInt(textField2.getText());
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(new JFrame(), "Неправильно введена цена.");
                        textField2.setText(Objects.toString(data.ordering[src][4], ""));
                        return;
                    }
                    data.updateOrdering(textField3.getText(), ((ComboItem) comboBox1.getSelectedItem()).getValue(), ((ComboItem) comboBox2.getSelectedItem()).getValue(), datePicker.toString(), textField2.getText(), textField1.getText());
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
