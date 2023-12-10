import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Objects;

public class CorpseEdit extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private DatePicker datePicker1;
    private DatePicker datePicker2;

    public CorpseEdit(String title, DB data, Controller control, int src) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        textField1.setText(data.corpse[src][0].toString());
        textField2.setText(data.corpse[src][1].toString());
        textField3.setText(Objects.toString(data.corpse[src][4], ""));
        for (int i = 0; i < data.ordering.length; ++i) {
            comboBox1.addItem(new ComboItem(data.ordering[i][0].toString(), data.ordering[i][0].toString()));
        }
        comboBox1.setSelectedIndex(Integer.parseInt(data.corpse[src][5].toString()) - 1);
        datePicker1.setDate(LocalDate.parse(data.corpse[src][2].toString()));
        datePicker2.setDate(LocalDate.parse(data.corpse[src][3].toString()));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals(data.corpse[src][0].toString()) && textField2.getText().equals(data.corpse[src][1].toString()) &&
                        textField3.getText().equals(Objects.toString(data.corpse[src][4], "")) && ((ComboItem) comboBox1.getSelectedItem()).getValue().equals(data.corpse[src][5].toString()) &&
                        datePicker1.toString().equals(data.corpse[src][2].toString()) && datePicker2.toString().equals(data.corpse[src][3].toString())) {
                    Exit();
                } else {
                    if (textField1.getText().isEmpty() || textField2.getText().isEmpty() || datePicker1.getDate() == null || datePicker2.getDate() == null) {
                        JOptionPane.showMessageDialog(new JFrame(), "Основные поля должны быть заполненными.");
                        textField1.setText(data.corpse[src][0].toString());
                        textField2.setText(data.corpse[src][1].toString());
                        textField3.setText(Objects.toString(data.corpse[src][4], ""));
                        comboBox1.setSelectedIndex(Integer.parseInt(data.corpse[src][5].toString()) - 1);
                        datePicker1.setDate(LocalDate.parse(data.corpse[src][2].toString()));
                        datePicker2.setDate(LocalDate.parse(data.corpse[src][3].toString()));
                        return;
                    }
                    for (int i = 0; i < data.corpse.length; ++i) {
                        if (!textField1.getText().equals(data.corpse[src][0].toString())) {
                            JOptionPane.showMessageDialog(new JFrame(), "ID изменять нельзя.");
                            textField1.setText(data.corpse[src][0].toString());
                            return;
                        }
                    }
                    if (datePicker1.getDate().isAfter(LocalDate.now())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Дата не должна превышать текущую.");
                        datePicker1.setDate(LocalDate.parse(data.corpse[src][2].toString()));
                        return;
                    }
                    if (datePicker2.getDate().isAfter(LocalDate.now())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Дата не должна превышать текущую.");
                        datePicker2.setDate(LocalDate.parse(data.corpse[src][3].toString()));
                        return;
                    }
                    try {
                        Integer.parseInt(textField1.getText());
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен ID.");
                        textField1.setText(data.corpse[src][0].toString());
                        return;
                    }
                    data.updateCorpse(textField1.getText(), textField2.getText(), datePicker1.toString(), datePicker2.toString(), textField3.getText(), ((ComboItem) comboBox1.getSelectedItem()).getValue());
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
