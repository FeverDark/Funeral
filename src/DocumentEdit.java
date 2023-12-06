import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class DocumentEdit extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JTextField textField2;

    public DocumentEdit(String title, DB data, Controller control, int src) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        textField1.setText(Objects.toString(data.getDocument()[src][0], ""));
        textField2.setText(Objects.toString(data.getDocument()[src][4], ""));
        for (int i = 0; i < data.getEmployer().length; ++i) {
            comboBox1.addItem(new ComboItem(data.getEmployer()[i][1].toString(), data.getEmployer()[i][0].toString()));
        }
        comboBox2.addItem(new ComboItem("", "NULL"));
        for (int i = 0; i < data.getContractor().length; ++i) {
            comboBox2.addItem(new ComboItem(data.getContractor()[i][0].toString(), data.getContractor()[i][0].toString()));
        }
        comboBox3.addItem(new ComboItem("", "NULL"));
        for (int i = 0; i < data.getOrdering().length; ++i) {
            comboBox3.addItem(new ComboItem(data.getOrdering()[i][0].toString(), data.getOrdering()[i][0].toString()));
        }
        if (data.getDocument()[src][1] != null)
            comboBox1.setSelectedIndex(Integer.parseInt(data.getDocument()[src][1].toString()) - 1);
        if (data.getDocument()[src][2] != null) {
            for (int i = 0; i < data.getContractor().length; ++i) {
                if (data.getDocument()[src][2].toString().equals(data.getContractor()[i][0].toString())) {
                    comboBox2.setSelectedIndex(i + 1);
                    break;
                }
            }
        }
        if (data.getDocument()[src][3] != null)
            comboBox3.setSelectedIndex(Integer.parseInt(data.getDocument()[src][3].toString()));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals(Objects.toString(data.getDocument()[src][0], "")) && textField2.getText().equals(Objects.toString(data.getDocument()[src][4], "")) &&
                        ((ComboItem) comboBox1.getSelectedItem()).getValue().toString().equals(Objects.toString(data.getDocument()[src][1], "NULL")) &&
                        ((ComboItem) comboBox2.getSelectedItem()).getValue().toString().equals(Objects.toString(data.getDocument()[src][2], "NULL")) &&
                        ((ComboItem) comboBox3.getSelectedItem()).getValue().toString().equals(Objects.toString(data.getDocument()[src][3], "NULL"))) {
                    Exit();
                } else {
                    if (textField1.getText().isEmpty() || textField2.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(new JFrame(), "Поля должны быть заполненными.");
                        textField1.setText(Objects.toString(data.getDocument()[src][0], ""));
                        textField2.setText(Objects.toString(data.getDocument()[src][4], ""));
                        if (data.getDocument()[src][1] != null)
                            comboBox1.setSelectedIndex(Integer.parseInt(data.getDocument()[src][1].toString()) - 1);
                        if (data.getDocument()[src][2] != null) {
                            for (int i = 0; i < data.getContractor().length; ++i) {
                                if (data.getDocument()[src][2].toString().equals(data.getContractor()[i][0].toString())) {
                                    comboBox2.setSelectedIndex(i + 1);
                                    break;
                                }
                            }
                        }
                        if (data.getDocument()[src][3] != null)
                            comboBox3.setSelectedIndex(Integer.parseInt(data.getDocument()[src][3].toString()));
                        return;
                    }
                    for (int i = 0; i < data.getDocument().length; ++i) {
                        if (!textField1.getText().equals(data.getDocument()[src][0].toString())) {
                            JOptionPane.showMessageDialog(new JFrame(), "ID изменять нельзя.");
                            textField1.setText(Objects.toString(data.getDocument()[src][0], ""));
                            return;
                        }
                    }
                    try {
                        Integer.parseInt(textField1.getText());
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен ID.");
                        textField1.setText(Objects.toString(data.getDocument()[src][0], ""));
                        return;
                    }
                    data.updateDocument(textField1.getText(), ((ComboItem) comboBox1.getSelectedItem()).getValue(), ((ComboItem) comboBox2.getSelectedItem()).getValue(), ((ComboItem) comboBox3.getSelectedItem()).getValue(), textField2.getText());
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
