import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class OrderProductsEdit extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public OrderProductsEdit(String title, DB data, Controller control, int src) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        textField1.setText(Objects.toString(data.getOrderProducts()[src][2], ""));
        for (int i = 0; i < data.getOrdering().length; ++i) {
            comboBox2.addItem(new ComboItem(data.getOrdering()[i][0].toString(), data.getOrdering()[i][0].toString()));
        }
        comboBox2.setSelectedIndex(Integer.parseInt(data.getOrderProducts()[src][0].toString()) - 1);
        for (int i = 0; i < data.getProduct().length; ++i) {
            comboBox1.addItem(new ComboItem(data.getProduct()[i][1].toString(), data.getProduct()[i][0].toString()));
        }
        comboBox1.setSelectedIndex(Integer.parseInt(data.getOrderProducts()[src][1].toString()) - 1);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField1.getText().equals("0")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Количество не должно быть нулевым или пустым.");
                    textField1.setText(Objects.toString(data.getOrderProducts()[src][2], ""));
                    return;
                }
                for (int i = 0; i != data.getOrderProducts().length; ++i) {
                    if (data.getOrderProducts()[i][0].toString().equals(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                        if (data.getOrderProducts()[i][1].toString().equals(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            JOptionPane.showMessageDialog(new JFrame(), "Товар уже есть в заказе.");
                            comboBox2.setSelectedIndex(Integer.parseInt(data.getOrderProducts()[src][0].toString()) - 1);
                            comboBox1.setSelectedIndex(Integer.parseInt(data.getOrderProducts()[src][1].toString()) - 1);
                            return;
                        }
                    }
                }
                //data.addOrderProducts(((ComboItem) comboBox1.getSelectedItem()).getValue(), ((ComboItem) comboBox2.getSelectedItem()).getValue(), textField1.getText());
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
