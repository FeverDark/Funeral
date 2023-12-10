import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ProductEdit extends JFrame {
    private JButton addButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JPanel mainPanel;

    public ProductEdit(String title, DB data, Controller control, int src) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        textField1.setText(Objects.toString(data.product[src][0], ""));
        textField2.setText(Objects.toString(data.product[src][1], ""));
        textField3.setText(Objects.toString(data.product[src][4], ""));
        textField4.setText(Objects.toString(data.product[src][3], ""));
        for (int i = 0; i < data.productsCategory.length; ++i) {
            comboBox1.addItem(new ComboItem(data.productsCategory[i][1].toString(), data.productsCategory[i][0].toString()));
        }
        comboBox1.setSelectedIndex(Integer.parseInt(data.product[src][2].toString()) - 1);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals(Objects.toString(data.product[src][0], "")) &&
                        textField2.getText().equals(Objects.toString(data.product[src][1], "")) &&
                        textField3.getText().equals(Objects.toString(data.product[src][4], "")) &&
                        textField4.getText().equals(Objects.toString(data.product[src][3], "")) &&
                        ((ComboItem) comboBox1.getSelectedItem()).getValue().equals(data.product[src][2].toString())) {
                    Exit();
                } else {
                    if (textField1.getText().isEmpty() || textField2.getText().isEmpty() || textField4.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(new JFrame(), "Поля должны быть заполненными.");
                        textField1.setText(Objects.toString(data.product[src][0], ""));
                        textField2.setText(Objects.toString(data.product[src][1], ""));
                        textField4.setText(Objects.toString(data.product[src][3], ""));
                        comboBox1.setSelectedIndex(Integer.parseInt(data.product[src][2].toString()) - 1);
                        return;
                    }
                    for (int i = 0; i < data.product.length; ++i) {
                        if (!textField1.getText().equals(data.product[src][0].toString())) {
                            JOptionPane.showMessageDialog(new JFrame(), "ID изменять нельзя.");
                            textField1.setText(Objects.toString(data.product[src][0], ""));
                            return;
                        }
                    }
                    try {
                        Integer.parseInt(textField1.getText());
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен ID.");
                        textField1.setText(Objects.toString(data.product[src][0], ""));
                        return;
                    }
                    try {
                        Integer.parseInt(textField4.getText());
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(new JFrame(), "Неправильно введена цена.");
                        textField4.setText(Objects.toString(data.product[src][3], ""));
                        return;
                    }
                    try {
                        if (!textField3.getText().equals("")) Integer.parseInt(textField3.getText());
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(new JFrame(), "Неправильно введено количество товаров.");
                        textField3.setText(Objects.toString(data.product[src][4], ""));
                        return;
                    }
                    data.updateProduct(textField1.getText(), textField2.getText(), ((ComboItem) comboBox1.getSelectedItem()).getValue(), textField4.getText(), textField3.getText());
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
