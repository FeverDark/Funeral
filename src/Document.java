import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Document extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JTextField textField2;

    public Document(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Поля должны быть заполненными.");
                    return;
                }
                for (int i = 0; i < data.getDocument().length; ++i) {
                    if (textField1.getText().equals(data.getDocument()[i][0].toString())) {
                        JOptionPane.showMessageDialog(new JFrame(), "ID должен быть уникальным.");
                        return;
                    }
                }
                try {
                    Integer.parseInt(textField1.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен ID.");
                    return;
                }
                data.addDocument(textField1.getText(), ((ComboItem) comboBox1.getSelectedItem()).getValue(), ((ComboItem) comboBox2.getSelectedItem()).getValue(), ((ComboItem) comboBox3.getSelectedItem()).getValue(), textField2.getText());
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
