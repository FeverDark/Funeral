import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class Corpse extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private DatePicker datePicker1;
    private DatePicker datePicker2;

    public Corpse(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        textField3.setText("");
        for (int i = 0; i < data.ordering.length; ++i) {
            comboBox1.addItem(new ComboItem(data.ordering[i][0].toString(), data.ordering[i][0].toString()));
        }
        datePicker1.setDateToToday();
        datePicker2.setDateToToday();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField2.getText().isEmpty() || datePicker1.getDate() == null || datePicker2.getDate() == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Основные поля должны быть заполненными.");
                    return;
                }
                if (datePicker1.getDate().isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(new JFrame(), "Дата не должна превышать текущую.");
                    return;
                }
                if (datePicker2.getDate().isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(new JFrame(), "Дата не должна превышать текущую.");
                    return;
                }
                data.addCorpse(textField2.getText(), datePicker1.toString(), datePicker2.toString(), textField3.getText(), ((ComboItem) comboBox1.getSelectedItem()).getValue());
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
