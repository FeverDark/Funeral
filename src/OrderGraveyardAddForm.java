import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderGraveyardAddForm extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField2;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private String[] graveyards = new String[]{};
    private int price = -1;
    private float area = -1;

    public OrderGraveyardAddForm(String title, DB data, Controller control, OrderGraveyardAdd item, DefaultTableModel model, ArrayList<Item> order) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        for (int i = 0; i < data.getGraveyard().length; ++i) {
            if (!Arrays.asList(graveyards).contains(data.getGraveyard()[i][1].toString()) && (Integer.parseInt(data.getGraveyard()[i][5].toString()) == 0)) {
                comboBox1.addItem(new ComboItem(data.getGraveyard()[i][1].toString(), data.getGraveyard()[i][1].toString()));
                graveyards = Arrays.copyOf(graveyards, graveyards.length + 1);
                graveyards[graveyards.length - 1] = data.getGraveyard()[i][1].toString();
            }
        }
        if (comboBox1.getSelectedIndex() != -1) {
            comboBox2.removeAllItems();
            for (int i = 0; i < data.getGraveyard().length; ++i) {
                if (data.getGraveyard()[i][1].toString().equals(((ComboItem) comboBox1.getSelectedItem()).getValue()) && (Integer.parseInt(data.getGraveyard()[i][5].toString()) == 0)) {
                    comboBox2.addItem(new ComboItem(data.getGraveyard()[i][2].toString(), data.getGraveyard()[i][0].toString()));
                }
            }
        }
        if (comboBox2.getSelectedIndex() != -1) {
            for (int i = 0; i < data.getGraveyard().length; ++i) {
                if (Integer.parseInt(data.getGraveyard()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                    price = Integer.parseInt(data.getGraveyard()[i][3].toString());
                    area = Float.parseFloat(data.getGraveyard()[i][4].toString());
                    textField1.setText(String.valueOf(price));
                    textField2.setText(String.valueOf(area));
                }
            }
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i != order.size(); ++i) {
                    if (order.get(i).getType() == 2) {
                        OrderGraveyardAdd temp = (OrderGraveyardAdd) order.get(i);
                        if (Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getValue()) == temp.getId()) {
                            JOptionPane.showMessageDialog(new JFrame(), "Место на кладбище уже есть в заказе.");
                            return;
                        }
                    }
                }
                item.setId(Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getValue()));
                item.setName(((ComboItem) comboBox1.getSelectedItem()).getValue());
                item.setNumber(Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getKey()));
                item.setArea(area);
                item.setPrice(price);
                model.addRow(new Object[]{"Место на кладбище", item.getName(), item.getNumber(), item.getArea(), item.getPrice()});
                order.add(item);
                Exit();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedIndex() != -1) {
                    comboBox2.removeAllItems();
                    for (int i = 0; i < data.getGraveyard().length; ++i) {
                        if (data.getGraveyard()[i][1].toString().equals(((ComboItem) comboBox1.getSelectedItem()).getValue()) && (Integer.parseInt(data.getGraveyard()[i][5].toString()) == 0)) {
                            comboBox2.addItem(new ComboItem(data.getGraveyard()[i][2].toString(), data.getGraveyard()[i][0].toString()));
                        }
                    }
                }
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox2.getSelectedIndex() != -1) {
                    for (int i = 0; i < data.getGraveyard().length; ++i) {
                        if (Integer.parseInt(data.getGraveyard()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                            price = Integer.parseInt(data.getGraveyard()[i][3].toString());
                            area = Float.parseFloat(data.getGraveyard()[i][4].toString());
                            textField1.setText(String.valueOf(price));
                            textField2.setText(String.valueOf(area));
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
