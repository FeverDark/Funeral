import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class CorpseOrderAdd extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JTextField textField2;
    private DatePicker datePicker1;
    private DatePicker datePicker2;

    public CorpseOrderAdd(String title, DB data, Controller control, OrderCorpseAdd item, DefaultTableModel model, ArrayList<Item> order) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        datePicker1.setDateToToday();
        datePicker2.setDateToToday();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField2.getText().isEmpty() || datePicker1.getDate() == null || datePicker2.getDate() == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Основные поля должны быть заполненными.");
                    return;
                }
                if (datePicker1.getDate().isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(new JFrame(), "Дата не должна превышать текущую.");
                    return;
                }
                if (datePicker2.getDate().isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(new JFrame(), "Дата не должна превышать текущую.");
                    return;
                }
                item.setName(textField2.getText());
                item.setBdate(datePicker1.toString());
                item.setDdate(datePicker2.toString());
                model.addRow(new Object[]{"Покойник", item.getName(), item.getBdate(), item.getDdate()});
                order.add(item);
                Exit();
            }
        });
    }

    private void Exit() {
        this.setVisible(false);
        this.dispose();
    }
}
