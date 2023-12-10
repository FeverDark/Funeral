import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class OrderCreation extends JFrame {
    private final Thread sumThread;
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JButton addButton;
    private JButton createButton;
    private JScrollPane mainScroll;
    private JTextField textField4;
    private JTextField textField5;
    private int clientId = -1;
    private int orderId = -1;
    private int price = 0;

    public OrderCreation(String title, DB data, Controller control) {
        super(title);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                sumThread.interrupt();
            }
        });
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        textField3.setText(data.employerName);
        DefaultTableModel model = new DefaultTableModel();
        JTable tempTable = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        model.addColumn("");
        model.addColumn("");
        model.addColumn("");
        model.addColumn("");
        model.addColumn("");
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        tempTable.setDefaultRenderer(String.class, leftRenderer);
        tempTable.setDefaultRenderer(Integer.class, leftRenderer);
        tempTable.setDefaultRenderer(Float.class, leftRenderer);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tempTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tempTable.removeEditor();
        this.mainScroll.setViewportView(tempTable);
        for (int i = 0; i < data.employer.length; ++i) {
            if (Integer.parseInt(data.employer[i][0].toString()) == data.employerId)
                textField3.setText(data.employer[i][1].toString());
            break;
        }
        ArrayList<Item> order = new ArrayList<Item>();
        sumThread = new Thread(() -> {
            while (true) {
                price = 0;
                for (int i = 0; i < order.size(); ++i) {
                    price += order.get(i).getPrice();
                }
                textField5.setText(String.valueOf(price));
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        sumThread.start();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame temp;
                if (radioButton1.isSelected()) {
                    OrderCorpseAdd tempItem = new OrderCorpseAdd();
                    temp = new CorpseOrderAdd("Добавить покойника", data, control, tempItem, model, order);
                    temp.setIconImage(Main.getFDImage());
                    temp.setSize(600, 400);
                    temp.setVisible(true);
                } else if (radioButton2.isSelected()) {
                    OrderGraveyardAdd tempItem = new OrderGraveyardAdd();
                    temp = new OrderGraveyardAddForm("Добавить место на кладбище", data, control, tempItem, model, order);
                    temp.setIconImage(Main.getFDImage());
                    temp.setSize(600, 400);
                    temp.setVisible(true);
                } else if (radioButton3.isSelected()) {
                    OrderPlaceAdd tempItem = new OrderPlaceAdd();
                    temp = new OrderPlaceAddForm("Добавить место проведения", data, control, tempItem, model, order);
                    temp.setIconImage(Main.getFDImage());
                    temp.setSize(600, 400);
                    temp.setVisible(true);
                } else if (radioButton4.isSelected()) {
                    OrderProductsAdd tempItem = new OrderProductsAdd();
                    temp = new OrderProductsAddForm("Добавить товар", data, control, tempItem, model, order);
                    temp.setIconImage(Main.getFDImage());
                    temp.setSize(600, 400);
                    temp.setVisible(true);
                } else if (radioButton5.isSelected()) {
                    OrderServicesAdd tempItem = new OrderServicesAdd();
                    temp = new OrderServicesAddForm("Добавить услугу", data, control, tempItem, model, order);
                    temp.setIconImage(Main.getFDImage());
                    temp.setSize(600, 400);
                    temp.setVisible(true);
                } else if (radioButton6.isSelected()) {
                    OrderTransportAdd tempItem = new OrderTransportAdd();
                    temp = new OrderTransportAddForm("Добавить транспорт", data, control, tempItem, model, order);
                    temp.setIconImage(Main.getFDImage());
                    temp.setSize(600, 400);
                    temp.setVisible(true);
                }
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty() || textField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Информация о клиенте должна быть заполнена.");
                    return;
                }
                SQLServerDataSource ds = new SQLServerDataSource();
                ds.setUser("admin");
                ds.setPassword("admin");
                ds.setServerName("localhost");
                ds.setPortNumber(Integer.parseInt("1433"));
                ds.setDatabaseName("Bureau");
                ds.setTrustServerCertificate(true);
                for (int i = 0; i < data.client.length; ++i) {
                    if (data.client[i][1].toString().equals(textField1.getText()) && data.client[i][2].toString().equals(textField2.getText())) {
                        clientId = Integer.parseInt(data.client[i][0].toString());
                        break;
                    }
                }
                if (clientId == -1) {
                    try {
                        Connection con = ds.getConnection();
                        CallableStatement cstmt = con.prepareCall("INSERT INTO Client VALUES('" + textField1.getText() + "', '" + textField2.getText() + "');");
                        cstmt.execute();
                    } catch (SQLException c) {
                        c.printStackTrace();
                    }
                    try {
                        Connection con = ds.getConnection();
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT id FROM Client WHERE name = '" + textField1.getText() + "' AND phone = '" + textField2.getText() + "';");
                        while (rs.next()) {
                            clientId = rs.getInt(1);
                        }
                    } catch (SQLException c) {
                        c.printStackTrace();
                    }
                }
                price = 0;
                for (int i = 0; i < order.size(); ++i) {
                    price += order.get(i).getPrice();
                }
                try {
                    Connection con = ds.getConnection();
                    CallableStatement cstmt = con.prepareCall("INSERT INTO Ordering VALUES(" + clientId + ", " + data.employerId + ", '" + LocalDate.now() + "', " + price + ", " + (textField4.getText().isEmpty() ? "NULL" : "'" + textField4.getText() + "'") + ");");
                    cstmt.execute();
                } catch (SQLException c) {
                    c.printStackTrace();
                }
                try {
                    Connection con = ds.getConnection();
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT id FROM Ordering WHERE client_id = " + clientId + " AND employer_id = " + data.employerId + " AND order_date = '" + LocalDate.now() + "' AND price = " + price + ";");
                    while (rs.next()) {
                        orderId = rs.getInt(1);
                    }
                } catch (SQLException c) {
                    c.printStackTrace();
                }
                for (int i = 0; i < order.size(); ++i) {
                    order.get(i).updateDb(orderId);
                }
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