import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductEdit extends JFrame {
    private JButton addButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JPanel mainPanel;

    public ProductEdit(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.textField3.setText("");
        for (int i = 0; i < data.getProductsCategory().length; ++i) {
            comboBox1.addItem(new ComboItem(data.getProductsCategory()[i][1].toString(), data.getProductsCategory()[i][0].toString()));
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField2.getText().isEmpty() || textField4.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Поля должны быть заполненными.");
                    return;
                }
                for (int i = 0; i < data.getProduct().length; ++i) {
                    if (textField1.getText().equals(data.getProduct()[i][0].toString())) {
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
                try {
                    Integer.parseInt(textField4.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введена цена.");
                    return;
                }
                try {
                    if (!textField3.getText().equals("")) Integer.parseInt(textField3.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введено количество товаров.");
                    return;
                }
                data.addProduct(textField1.getText(), textField2.getText(), ((ComboItem) comboBox1.getSelectedItem()).getValue(), textField4.getText(), textField3.getText());
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