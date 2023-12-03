import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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

        textField1.setText(data.getCorpse()[src][0].toString());
        textField2.setText(data.getCorpse()[src][1].toString());
        textField3.setText(data.getCorpse()[src][4].toString());
        for (int i = 0; i < data.getOrdering().length; ++i) {
            comboBox1.addItem(new ComboItem(data.getOrdering()[i][0].toString(), data.getOrdering()[i][0].toString()));
        }
        comboBox1.setSelectedIndex(Integer.parseInt(data.getCorpse()[src][5].toString()) - 1);
        datePicker1.setDate(LocalDate.parse(data.getCorpse()[src][2].toString()));
        datePicker2.setDate(LocalDate.parse(data.getCorpse()[src][3].toString()));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField2.getText().isEmpty() || datePicker1.getDate() == null || datePicker2.getDate() == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Основные поля должны быть заполненными.");
                    textField1.setText(data.getCorpse()[src][0].toString());
                    textField2.setText(data.getCorpse()[src][1].toString());
                    textField3.setText(data.getCorpse()[src][4].toString());
                    comboBox1.setSelectedIndex(Integer.parseInt(data.getCorpse()[src][5].toString()) - 1);
                    datePicker1.setDate(LocalDate.parse(data.getCorpse()[src][2].toString()));
                    datePicker2.setDate(LocalDate.parse(data.getCorpse()[src][3].toString()));
                    return;
                }
                for (int i = 0; i < data.getCorpse().length; ++i) {
                    if (!textField1.getText().equals(data.getCorpse()[src][0].toString())) {
                        JOptionPane.showMessageDialog(new JFrame(), "ID изменять нельзя.");
                        textField1.setText(data.getCorpse()[src][0].toString());
                        return;
                    }
                }
                if (datePicker1.getDate().isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(new JFrame(), "Дата не должна превышать текущую.");
                    datePicker1.setDate(LocalDate.parse(data.getCorpse()[src][2].toString()));
                    return;
                }
                if (datePicker2.getDate().isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(new JFrame(), "Дата не должна превышать текущую.");
                    datePicker2.setDate(LocalDate.parse(data.getCorpse()[src][3].toString()));
                    return;
                }
                try {
                    Integer.parseInt(textField1.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен ID.");
                    textField1.setText(data.getCorpse()[src][0].toString());
                    return;
                }
                //data.addCorpse(textField1.getText(), textField2.getText(), datePicker1.toString(), datePicker2.toString(), textField3.getText(), ((ComboItem) comboBox1.getSelectedItem()).getValue());
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
