import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderProductsAddForm extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JLabel label12;
    private JLabel label11;
    private int stock;
    private int price;

    public OrderProductsAddForm(String title, DB data, Controller control, OrderProductsAdd item, DefaultTableModel model, ArrayList<Item> order) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        for (int i = 0; i < data.getProduct().length; ++i) {
            comboBox1.addItem(new ComboItem(data.getProduct()[i][1].toString(), data.getProduct()[i][0].toString()));
        }
        if (comboBox1.getSelectedIndex() != -1) {
            for (int i = 0; i < data.getProduct().length; ++i) {
                if (Integer.parseInt(data.getProduct()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    price = Integer.parseInt(data.getProduct()[i][3].toString());
                    stock = Integer.parseInt(data.getProduct()[i][4].toString());
                    label11.setText("Цена: " + price);
                    label12.setText("Осталось на складе: " + stock);
                    break;
                }
            }
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField1.getText().equals("0")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Количество не должно быть нулевым или пустым.");
                    return;
                }
                for (int i = 0; i != order.size(); ++i) {
                    if (order.get(i).getType() == 4) {
                        OrderProductsAdd temp = (OrderProductsAdd) order.get(i);
                        if (Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue()) == temp.getId()) {
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
                if (Integer.parseInt(textField1.getText()) > stock) {
                    JOptionPane.showMessageDialog(new JFrame(), "На складе нет столько товара.");
                    return;
                }
                item.setId(Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue()));
                item.setName(((ComboItem) comboBox1.getSelectedItem()).getKey());
                item.setAmount(Integer.parseInt(textField1.getText()));
                item.setPrice(price);
                for (int i = 0; i < data.getProduct().length; ++i) {
                    if (Integer.parseInt(data.getProduct()[i][0].toString()) == item.getId()) {
                        //item.setPrice(Integer.parseInt(data.getProduct()[i][3].toString()));
                        for (int j = 0; j < data.getProductsCategory().length; ++j) {
                            if (Integer.parseInt(data.getProduct()[i][2].toString()) == Integer.parseInt(data.getProductsCategory()[j][0].toString())) {
                                item.setCategory(data.getProductsCategory()[j][1].toString());
                                break;
                            }
                        }
                        break;
                    }
                }
                model.addRow(new Object[]{"Товар", item.getName(), item.getCategory(), item.getAmount(), item.getPrice()});
                order.add(item);
                Exit();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedIndex() != -1) {
                    for (int i = 0; i < data.getProduct().length; ++i) {
                        if (Integer.parseInt(data.getProduct()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            price = Integer.parseInt(data.getProduct()[i][3].toString());
                            stock = Integer.parseInt(data.getProduct()[i][4].toString());
                            label11.setText("Цена: " + price);
                            label12.setText("Осталось на складе: " + stock);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void Exit() {
        this.setVisible(false);
        this.dispose();
    }
}
