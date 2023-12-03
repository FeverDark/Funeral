import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderServices extends JFrame {
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton addButton;
    private JPanel mainPanel;
    private void Exit(){
        this.setVisible(false);
        this.dispose();
    }

    public OrderServices(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        for(int i = 0; i < data.getOrdering().length; ++ i) {
            comboBox1.addItem(new ComboItem(data.getOrdering()[i][0].toString(), data.getOrdering()[i][0].toString()));
        }
        for(int i = 0; i < data.getService().length; ++ i) {
            comboBox2.addItem(new ComboItem(data.getService()[i][1].toString(), data.getService()[i][0].toString()));
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ADD
                Exit();
                control.setUpdate(true);
            }
        });
    }
}
