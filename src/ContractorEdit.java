import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContractorEdit extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    public ContractorEdit(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        textField3.setText("");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Поля должны быть заполненными.");
                    return;
                }
                for (int i = 0; i < data.getContractor().length; ++i) {
                    if (textField1.getText().equals(data.getContractor()[i][0].toString())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Подрядчик должен быть уникальным.");
                        return;
                    }
                }
                data.addContractor(textField1.getText(), textField2.getText(), textField3.getText());
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
