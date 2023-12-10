import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderPlaceAddForm extends JFrame {
    private JPanel mainPanel;
    private JComboBox comboBox2;
    private JButton addButton;

    public OrderPlaceAddForm(String title, DB data, Controller control, OrderPlaceAdd item, DefaultTableModel model, ArrayList<Item> order) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        for (int i = 0; i < data.place.length; ++i) {
            comboBox2.addItem(new ComboItem(data.place[i][1].toString(), data.place[i][0].toString()));
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i != order.size(); ++i) {
                    if (order.get(i).getType() == 3) {
                        OrderPlaceAdd temp = (OrderPlaceAdd) order.get(i);
                        if (Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getValue()) == temp.getId()) {
                            JOptionPane.showMessageDialog(new JFrame(), "Место уже есть в заказе.");
                            return;
                        }
                    }
                }
                item.setId(Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getValue()));
                item.setName(((ComboItem) comboBox2.getSelectedItem()).getKey());
                for (int i = 0; i < data.place.length; ++i) {
                    if (Integer.parseInt(data.place[i][0].toString()) == item.getId()) {
                        item.setAdress(data.place[i][2].toString());
                        break;
                    }
                }
                model.addRow(new Object[]{"Место церемонии", item.getName(), item.getAdress() == null ? "" : item.getAdress()});
                order.add(item);
                Exit();
            }
        });
    }

    private void Exit() {
        this.setVisible(false);
        this.dispose();
    }
}
