import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Document extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JTextField textField2;

    public Document(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        for (int i = 0; i < data.employer.length; ++i) {
            comboBox1.addItem(new ComboItem(data.employer[i][1].toString(), data.employer[i][0].toString()));
        }
        comboBox2.addItem(new ComboItem("", "NULL"));
        for (int i = 0; i < data.contractor.length; ++i) {
            comboBox2.addItem(new ComboItem(data.contractor[i][0].toString(), data.contractor[i][0].toString()));
        }
        comboBox3.addItem(new ComboItem("", "NULL"));
        for (int i = 0; i < data.ordering.length; ++i) {
            comboBox3.addItem(new ComboItem(data.ordering[i][0].toString(), data.ordering[i][0].toString()));
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Поля должны быть заполненными.");
                    return;
                }
                data.addDocument(((ComboItem) comboBox1.getSelectedItem()).getValue(), ((ComboItem) comboBox2.getSelectedItem()).getValue(), ((ComboItem) comboBox3.getSelectedItem()).getValue(), textField2.getText());
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
