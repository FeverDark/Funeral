import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderServicesAddForm extends JFrame {
    private JComboBox comboBox2;
    private JButton addButton;
    private JPanel mainPanel;
    private JLabel label11;
    private int price;

    public OrderServicesAddForm(String title, DB data, Controller control, OrderServicesAdd item, DefaultTableModel model, ArrayList<Item> order) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        for (int i = 0; i < data.service.length; ++i) {
            comboBox2.addItem(new ComboItem(data.service[i][1].toString(), data.service[i][0].toString()));
        }
        if (comboBox2.getSelectedIndex() != -1) {
            for (int i = 0; i < data.service.length; ++i) {
                if (Integer.parseInt(data.service[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                    price = Integer.parseInt(data.service[i][2].toString());
                    label11.setText("Цена: " + price);
                    break;
                }
            }
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i != order.size(); ++i) {
                    if (order.get(i).getType() == 5) {
                        OrderServicesAdd temp = (OrderServicesAdd) order.get(i);
                        if (Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getValue()) == temp.getId()) {
                            JOptionPane.showMessageDialog(new JFrame(), "Услуга уже есть в заказе.");
                            return;
                        }
                    }
                }
                item.setId(Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getValue()));
                item.setName(((ComboItem) comboBox2.getSelectedItem()).getKey());
                item.setPrice(price);
                /*for(int i = 0; i < data.service.length; ++i){
                    if(Integer.parseInt(data.service[i][0].toString()) == item.getId()) {
                        item.setPrice(Integer.parseInt(data.service[i][2].toString()));
                        break;
                    }
                }*/
                model.addRow(new Object[]{"Услуга", item.getName(), "", "",  item.getPrice()});
                order.add(item);
                Exit();
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox2.getSelectedIndex() != -1) {
                    for (int i = 0; i < data.service.length; ++i) {
                        if (Integer.parseInt(data.service[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                            price = Integer.parseInt(data.service[i][2].toString());
                            label11.setText("Цена: " + price);
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
