import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderTransportAddForm extends JFrame {
    private JComboBox comboBox2;
    private JButton addButton;
    private JPanel mainPanel;
    private JLabel label11;
    private int capacity;

    public OrderTransportAddForm(String title, DB data, Controller control, OrderTransportAdd item, DefaultTableModel model, ArrayList<Item> order) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        for (int i = 0; i < data.getTransport().length; ++i) {
            comboBox2.addItem(new ComboItem(data.getTransport()[i][1].toString(), data.getTransport()[i][0].toString()));
        }
        if (comboBox2.getSelectedIndex() != -1) {
            for (int i = 0; i < data.getTransport().length; ++i) {
                if (Integer.parseInt(data.getTransport()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                    capacity = Integer.parseInt(data.getTransport()[i][2].toString());
                    label11.setText("Вместимость: " + capacity);
                    break;
                }
            }
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i != order.size(); ++i) {
                    if (order.get(i).getType() == 6) {
                        OrderTransportAdd temp = (OrderTransportAdd) order.get(i);
                        if (Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getValue()) == temp.getId()) {
                            JOptionPane.showMessageDialog(new JFrame(), "Транспорт уже есть в заказе.");
                            return;
                        }
                    }
                }
                item.setId(Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getValue()));
                item.setModel(((ComboItem) comboBox2.getSelectedItem()).getKey());
                item.setCapacity(capacity);
                /*for(int i = 0; i < data.getTransport().length; ++i){
                    if(Integer.parseInt(data.getTransport()[i][0].toString()) == item.getId()) {
                        item.setCapacity(Integer.parseInt(data.getTransport()[i][2].toString()));
                        break;
                    }
                }*/
                model.addRow(new Object[]{"Транспорт", item.getModel(), item.getCapacity()});
                order.add(item);
                Exit();
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox2.getSelectedIndex() != -1) {
                    for (int i = 0; i < data.getTransport().length; ++i) {
                        if (Integer.parseInt(data.getTransport()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                            capacity = Integer.parseInt(data.getTransport()[i][2].toString());
                            label11.setText("Вместимость: " + capacity);
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
