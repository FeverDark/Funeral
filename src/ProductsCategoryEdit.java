import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductsCategoryEdit extends JFrame {
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton addButton;

    public ProductsCategoryEdit(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Поля должны быть заполненными.");
                    return;
                }
                for (int i = 0; i < data.getProductsCategory().length; ++i) {
                    if (textField1.getText().equals(data.getProductsCategory()[i][0].toString())) {
                        JOptionPane.showMessageDialog(new JFrame(), "ID должен быть уникальным.");
                        return;
                    }
                }
                try {
                    Integer.parseInt(textField1.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен ID.");
                    return;
                }
                data.addProductsCategory(textField1.getText(), textField2.getText());
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
