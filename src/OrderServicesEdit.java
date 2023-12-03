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
        for (int i = 0; i < data.getOrdering().length; ++i) {
            comboBox1.addItem(new ComboItem(data.getOrdering()[i][0].toString(), data.getOrdering()[i][0].toString()));
        }
        comboBox1.setSelectedIndex(Integer.parseInt(data.getOrderServices()[src][0].toString()) - 1);
        for (int i = 0; i < data.getService().length; ++i) {
            comboBox2.addItem(new ComboItem(data.getService()[i][1].toString(), data.getService()[i][0].toString()));
        }
        comboBox2.setSelectedIndex(Integer.parseInt(data.getOrderServices()[src][1].toString()) - 1);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i != data.getOrderServices().length; ++i) {
                    if (data.getOrderServices()[i][0].toString().equals(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                        if (data.getOrderServices()[i][1].toString().equals(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                            JOptionPane.showMessageDialog(new JFrame(), "Услуга уже есть в заказе.");
                            comboBox1.setSelectedIndex(Integer.parseInt(data.getOrderServices()[src][0].toString()) - 1);
                            comboBox2.setSelectedIndex(Integer.parseInt(data.getOrderServices()[src][1].toString()) - 1);
                            return;
                        }
                    }
                }
                //data.addOrderServices(((ComboItem) comboBox1.getSelectedItem()).getValue(), ((ComboItem) comboBox2.getSelectedItem()).getValue());
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
