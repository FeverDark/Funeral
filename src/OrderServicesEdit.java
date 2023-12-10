import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderServicesEdit extends JFrame {
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton addButton;
    private JPanel mainPanel;

    public OrderServicesEdit(String title, DB data, Controller control, int src) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        for (int i = 0; i < data.ordering.length; ++i) {
            comboBox1.addItem(new ComboItem(data.ordering[i][0].toString(), data.ordering[i][0].toString()));
        }
        comboBox1.setSelectedIndex(Integer.parseInt(data.orderServices[src][0].toString()) - 1);
        for (int i = 0; i < data.service.length; ++i) {
            comboBox2.addItem(new ComboItem(data.service[i][1].toString(), data.service[i][0].toString()));
        }
        comboBox2.setSelectedIndex(Integer.parseInt(data.orderServices[src][1].toString()) - 1);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((ComboItem) comboBox1.getSelectedItem()).getValue().equals(data.orderServices[src][0].toString()) &&
                        ((ComboItem) comboBox2.getSelectedItem()).getValue().equals(data.orderServices[src][1].toString())) {
                    Exit();
                } else {
                    for (int i = 0; i != data.orderServices.length; ++i) {
                        if (data.orderServices[i][0].toString().equals(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            if (data.orderServices[i][1].toString().equals(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                                JOptionPane.showMessageDialog(new JFrame(), "Услуга уже есть в заказе.");
                                comboBox1.setSelectedIndex(Integer.parseInt(data.orderServices[src][0].toString()) - 1);
                                comboBox2.setSelectedIndex(Integer.parseInt(data.orderServices[src][1].toString()) - 1);
                                return;
                            }
                        }
                    }
                    data.updateOrderServices(((ComboItem) comboBox1.getSelectedItem()).getValue(), ((ComboItem) comboBox2.getSelectedItem()).getValue(), data.orderServices[src][0].toString(), data.orderServices[src][1].toString());
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
