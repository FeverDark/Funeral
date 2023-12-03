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
        for (int i = 0; i < data.getOrdering().length; ++i) {
            comboBox1.addItem(new ComboItem(data.getOrdering()[i][0].toString(), data.getOrdering()[i][0].toString()));
        }
        comboBox1.setSelectedIndex(Integer.parseInt(data.getOrderPlace()[src][0].toString()) - 1);
        for (int i = 0; i < data.getPlace().length; ++i) {
            comboBox2.addItem(new ComboItem(data.getPlace()[i][1].toString(), data.getPlace()[i][0].toString()));
        }
        comboBox2.setSelectedIndex(Integer.parseInt(data.getOrderPlace()[src][1].toString()) - 1);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i != data.getOrderPlace().length; ++i) {
                    if (data.getOrderPlace()[i][0].toString().equals(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                        if (data.getOrderPlace()[i][1].toString().equals(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                            JOptionPane.showMessageDialog(new JFrame(), "Место уже есть в заказе.");
                            comboBox1.setSelectedIndex(Integer.parseInt(data.getOrderPlace()[src][0].toString()) - 1);
                            comboBox2.setSelectedIndex(Integer.parseInt(data.getOrderPlace()[src][1].toString()) - 1);
                            return;
                        }
                    }
                }
                //data.addOrderPlace(((ComboItem) comboBox1.getSelectedItem()).getValue(), ((ComboItem) comboBox2.getSelectedItem()).getValue());
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
