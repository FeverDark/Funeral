import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderServices extends JFrame {
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton addButton;
    private JPanel mainPanel;

    public OrderServices(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        for (int i = 0; i < data.ordering.length; ++i) {
            comboBox1.addItem(new ComboItem(data.ordering[i][0].toString(), data.ordering[i][0].toString()));
        }
        for (int i = 0; i < data.service.length; ++i) {
            comboBox2.addItem(new ComboItem(data.service[i][1].toString(), data.service[i][0].toString()));
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i != data.orderServices.length; ++i) {
                    if (data.orderServices[i][0].toString().equals(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                        if (data.orderServices[i][1].toString().equals(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                            JOptionPane.showMessageDialog(new JFrame(), "Услуга уже есть в заказе.");
                            return;
                        }
                    }
                }
                data.addOrderServices(((ComboItem) comboBox1.getSelectedItem()).getValue(), ((ComboItem) comboBox2.getSelectedItem()).getValue());
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
