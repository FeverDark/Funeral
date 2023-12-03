import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderTransportEdit extends JFrame {
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton addButton;
    private JPanel mainPanel;

    public OrderTransportEdit(String title, DB data, Controller control, int src) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        for (int i = 0; i < data.getOrdering().length; ++i) {
            comboBox1.addItem(new ComboItem(data.getOrdering()[i][0].toString(), data.getOrdering()[i][0].toString()));
        }
        comboBox1.setSelectedIndex(Integer.parseInt(data.getOrderTransport()[src][0].toString()) - 1);
        for (int i = 0; i < data.getTransport().length; ++i) {
            comboBox2.addItem(new ComboItem(data.getTransport()[i][1].toString(), data.getTransport()[i][0].toString()));
        }
        comboBox2.setSelectedIndex(Integer.parseInt(data.getOrderTransport()[src][1].toString()) - 1);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i != data.getOrderTransport().length; ++i) {
                    if (data.getOrderTransport()[i][0].toString().equals(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                        if (data.getOrderTransport()[i][1].toString().equals(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                            JOptionPane.showMessageDialog(new JFrame(), "Транспорт уже есть в заказе.");
                            comboBox1.setSelectedIndex(Integer.parseInt(data.getOrderTransport()[src][0].toString()) - 1);
                            comboBox2.setSelectedIndex(Integer.parseInt(data.getOrderTransport()[src][1].toString()) - 1);
                            return;
                        }
                    }
                }
                //data.addOrderTransport(((ComboItem) comboBox1.getSelectedItem()).getValue(), ((ComboItem) comboBox2.getSelectedItem()).getValue());
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
