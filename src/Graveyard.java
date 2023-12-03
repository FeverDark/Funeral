import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class Graveyard extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;
    private void Exit(){
        this.setVisible(false);
        this.dispose();
    }

    public Graveyard(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        comboBox1.addItem(new ComboItem("", "NULL"));
        for(int i = 0; i < data.getOrdering().length; ++ i) {
            comboBox1.addItem(new ComboItem(data.getOrdering()[i][0].toString(), data.getOrdering()[i][0].toString()));
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty() || textField4.getText().isEmpty() || textField5.getText().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(), "Основные поля должны быть заполненными.");
                    return;
                }
                for(int i = 0; i < data.getGraveyard().length; ++i){
                    if (textField1.getText().equals(data.getGraveyard()[i][0].toString())) {JOptionPane.showMessageDialog(new JFrame(), "ID должен быть уникальным."); return;}
                }
                try {
                    Integer.parseInt(textField1.getText());
                } catch(NumberFormatException c){
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введен ID.");
                    return;
                }
                try {
                    Integer.parseInt(textField4.getText());
                } catch(NumberFormatException c){
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введена цена.");
                    return;
                }
                try {
                    Float.parseFloat(textField5.getText());
                } catch(NumberFormatException c){
                    JOptionPane.showMessageDialog(new JFrame(), "Неправильно введена площадь.");
                    return;
                }
                data.addGraveyard(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText(), textField5.getText(), ((ComboItem)comboBox1.getSelectedItem()).getValue());
                Exit();
                control.setUpdate(true);
            }
        });
    }
}
