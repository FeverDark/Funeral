import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GraveyardEdit extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;

    public GraveyardEdit(String title, DB data, Controller control, int src) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        textField1.setText(Objects.toString(data.getGraveyard()[src][0], ""));
        textField2.setText(Objects.toString(data.getGraveyard()[src][1], ""));
        textField3.setText(Objects.toString(data.getGraveyard()[src][2], ""));
        textField4.setText(Objects.toString(data.getGraveyard()[src][3], ""));
        textField5.setText(Objects.toString(data.getGraveyard()[src][4], ""));
        comboBox1.addItem(new ComboItem("", "NULL"));
        for (int i = 0; i < data.getOrdering().length; ++i) {
            comboBox1.addItem(new ComboItem(data.getOrdering()[i][0].toString(), data.getOrdering()[i][0].toString()));
        }
        if(data.getGraveyard()[src][5] != null) comboBox1.setSelectedIndex(Integer.parseInt(data.getGraveyard()[src][5].toString()));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty() || textField4.getText().isEmpty() || textField5.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Основные поля должны быть заполненными.");
                    textField1.setText(Objects.toString(data.getGraveyard()[src][0], ""));
                    textField2.setText(Objects.toString(data.getGraveyard()[src][1], ""));
                    textField3.setText(Objects.toString(data.getGraveyard()[src][2], ""));
                    textField4.setText(Objects.toString(data.getGraveyard()[src][3], ""));
                    textField5.setText(Objects.toString(data.getGraveyard()[src][4], ""));
                    if(data.getGraveyard()[src][5] != null) comboBox1.setSelectedIndex(Integer.parseInt(data.getGraveyard()[src][5].toString()));
                    return;
                }
                for (int i = 0; i < data.getGraveyard().length; ++i) {
                    if (!textField1.getText().equals(data.getGraveyard()[src][0].toString())) {
                        JOptionPane.showMessageDialog(new JFrame(), "ID изменять нельзя.");
                        textField1.setText(Objects.toString(data.getGraveyard()[src][0], ""));
                        return;
                    }
                }
                try {
                    Integer.parseInt(textField1.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен ID.");
                    textField1.setText(Objects.toString(data.getGraveyard()[src][0], ""));
                    return;
                }
                try {
                    Integer.parseInt(textField4.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введена цена.");
                    textField4.setText(Objects.toString(data.getGraveyard()[src][3], ""));
                    return;
                }
                try {
                    Float.parseFloat(textField5.getText());
                } catch (NumberFormatException c) {
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введена площадь.");
                    textField5.setText(Objects.toString(data.getGraveyard()[src][4], ""));
                    return;
                }
                //data.addGraveyard(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText(), ((ComboItem) comboBox1.getSelectedItem()).getValue());
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
