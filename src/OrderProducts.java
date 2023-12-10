import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderProducts extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public OrderProducts(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        textField1.setText("");
        for (int i = 0; i < data.ordering.length; ++i) {
            comboBox2.addItem(new ComboItem(data.ordering[i][0].toString(), data.ordering[i][0].toString()));
        }
        for (int i = 0; i < data.product.length; ++i) {
            comboBox1.addItem(new ComboItem(data.product[i][1].toString(), data.product[i][0].toString()));
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField1.getText().equals("0")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Количество не должно быть нулевым или пустым.");
                    return;
                }
                for (int i = 0; i != data.orderProducts.length; ++i) {
                    if (data.orderProducts[i][0].toString().equals(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                        if (data.orderProducts[i][1].toString().equals(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            JOptionPane.showMessageDialog(new JFrame(), "Товар уже есть в заказе.");
                            return;
                        }
                    }
                }
                try {
                    Integer.parseInt(textField1.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введено количество.");
                    return;
                }
                data.addOrderProducts(((ComboItem) comboBox2.getSelectedItem()).getValue(), ((ComboItem) comboBox1.getSelectedItem()).getValue(), textField1.getText());
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
