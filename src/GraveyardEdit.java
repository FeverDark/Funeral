import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GraveyardEdit extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;

    public GraveyardEdit(String title, DB data, Controller control, int src) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        textField1.setText(Objects.toString(data.graveyard[src][0], ""));
        textField2.setText(Objects.toString(data.graveyard[src][1], ""));
        textField3.setText(Objects.toString(data.graveyard[src][2], ""));
        textField4.setText(Objects.toString(data.graveyard[src][3], ""));
        textField5.setText(Objects.toString(data.graveyard[src][4], ""));
        comboBox1.addItem(new ComboItem("", "NULL"));
        for (int i = 0; i < data.ordering.length; ++i) {
            comboBox1.addItem(new ComboItem(data.ordering[i][0].toString(), data.ordering[i][0].toString()));
        }
        if (data.graveyard[src][5] != null)
            comboBox1.setSelectedIndex(Integer.parseInt(data.graveyard[src][5].toString()));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals(Objects.toString(data.graveyard[src][0], "")) && textField2.getText().equals(Objects.toString(data.graveyard[src][1], "")) &&
                        textField3.getText().equals(Objects.toString(data.graveyard[src][2], "")) && textField4.getText().equals(Objects.toString(data.graveyard[src][3], "")) &&
                        textField5.getText().equals(Objects.toString(data.graveyard[src][4], "")) &&
                        ((ComboItem) comboBox1.getSelectedItem()).getValue().equals(Objects.toString(data.graveyard[src][5], "NULL"))) {
                    Exit();
                } else {
                    if (textField1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty() || textField4.getText().isEmpty() || textField5.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(new JFrame(), "Основные поля должны быть заполненными.");
                        textField1.setText(Objects.toString(data.graveyard[src][0], ""));
                        textField2.setText(Objects.toString(data.graveyard[src][1], ""));
                        textField3.setText(Objects.toString(data.graveyard[src][2], ""));
                        textField4.setText(Objects.toString(data.graveyard[src][3], ""));
                        textField5.setText(Objects.toString(data.graveyard[src][4], ""));
                        if (data.graveyard[src][5] != null)
                            comboBox1.setSelectedIndex(Integer.parseInt(data.graveyard[src][5].toString()));
                        return;
                    }
                    for (int i = 0; i < data.graveyard.length; ++i) {
                        if (!textField1.getText().equals(data.graveyard[src][0].toString())) {
                            JOptionPane.showMessageDialog(new JFrame(), "ID изменять нельзя.");
                            textField1.setText(Objects.toString(data.graveyard[src][0], ""));
                            return;
                        }
                    }
                    try {
                        Integer.parseInt(textField1.getText());
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен ID.");
                        textField1.setText(Objects.toString(data.graveyard[src][0], ""));
                        return;
                    }
                    try {
                        Integer.parseInt(textField4.getText());
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(new JFrame(), "Неправильно введена цена.");
                        textField4.setText(Objects.toString(data.graveyard[src][3], ""));
                        return;
                    }
                    try {
                        Float.parseFloat(textField5.getText());
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(new JFrame(), "Неправильно введена площадь.");
                        textField5.setText(Objects.toString(data.graveyard[src][4], ""));
                        return;
                    }
                    data.updateGraveyard(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText(), ((ComboItem) comboBox1.getSelectedItem()).getValue());
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
