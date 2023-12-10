import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderPlace extends JFrame {
    private JPanel mainPanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton addButton;

    public OrderPlace(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        for (int i = 0; i < data.ordering.length; ++i) {
            comboBox1.addItem(new ComboItem(data.ordering[i][0].toString(), data.ordering[i][0].toString()));
        }
        for (int i = 0; i < data.place.length; ++i) {
            comboBox2.addItem(new ComboItem(data.place[i][1].toString(), data.place[i][0].toString()));
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i != data.orderPlace.length; ++i) {
                    if (data.orderPlace[i][0].toString().equals(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                        if (data.orderPlace[i][1].toString().equals(((ComboItem) comboBox2.getSelectedItem()).getValue())) {
                            JOptionPane.showMessageDialog(new JFrame(), "Место уже есть в заказе.");
                            return;
                        }
                    }
                }
                data.addOrderPlace(((ComboItem) comboBox1.getSelectedItem()).getValue(), ((ComboItem) comboBox2.getSelectedItem()).getValue());
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
