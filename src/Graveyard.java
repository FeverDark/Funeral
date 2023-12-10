import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graveyard extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;

    public Graveyard(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        comboBox1.addItem(new ComboItem("", "NULL"));
        for (int i = 0; i < data.ordering.length; ++i) {
            comboBox1.addItem(new ComboItem(data.ordering[i][0].toString(), data.ordering[i][0].toString()));
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField2.getText().isEmpty() || textField3.getText().isEmpty() || textField4.getText().isEmpty() || textField5.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Основные поля должны быть заполненными.");
                    return;
                }
                try {
                    Integer.parseInt(textField4.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введена цена.");
                    return;
                }
                try {
                    Float.parseFloat(textField5.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введена площадь.");
                    return;
                }
                data.addGraveyard(textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText(), ((ComboItem) comboBox1.getSelectedItem()).getValue());
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
