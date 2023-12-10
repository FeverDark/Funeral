import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderPlaceEdit extends JFrame {
    private JPanel mainPanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton addButton;

    public OrderPlaceEdit(String title, DB data, Controller control, int src) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        for (int i = 0; i < data.ordering.length; ++i) {
            comboBox1.addItem(new ComboItem(data.ordering[i][0].toString(), data.ordering[i][0].toString()));
        }
        comboBox1.setSelectedIndex(Integer.parseInt(data.orderPlace[src][0].toString()) - 1);
        for (int i = 0; i < data.place.length; ++i) {
            comboBox2.addItem(new ComboItem(data.place[i][1].toString(), data.place[i][0].toString()));
        }
        comboBox2.setSelectedIndex(Integer.parseInt(data.orderPlace[src][1].toString()) - 1);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((ComboItem) comboBox1.getSelectedItem()).getValue().equals(data.orderPlace[src][0].toString()) &&
                        ((ComboItem) comboBox2.getSelectedItem()).getValue().equals(data.orderPlace[src][1].toString())) {
                    Exit();
                } else {
                    for (int i = 0; i != data.orderPlace.length; ++i) {
                        if (data.orderPlace[i][0].toString().equals(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            if (data.orderPlace[i][1].toString().equals(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                                JOptionPane.showMessageDialog(new JFrame(), "Место уже есть в заказе.");
                                comboBox1.setSelectedIndex(Integer.parseInt(data.orderPlace[src][0].toString()) - 1);
                                comboBox2.setSelectedIndex(Integer.parseInt(data.orderPlace[src][1].toString()) - 1);
                                return;
                            }
                        }
                    }
                    data.updateOrderPlace(((ComboItem) comboBox1.getSelectedItem()).getValue(), ((ComboItem) comboBox2.getSelectedItem()).getValue(), data.orderPlace[src][0].toString(), data.orderPlace[src][1].toString());
                    Exit();
                    control.setUpdate(true);
                }
            }
        });
    }

    private void Exit() {
        this.setVisible(false);
        this.dispose();
    }
}
