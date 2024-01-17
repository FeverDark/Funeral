import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class OrderCheck extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JScrollPane mainScroll;
    private JButton button1;
    private JButton button2;
    private JPanel mainPanel;
    private JTextField textField6;
    private JTextField textField7;
    private JComboBox comboBox1;
    private JButton button3;
    private JButton button4;
    private JButton editButton;
    private JButton button5;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JButton delButton;
    private String comm = null;
    private final DB data;
    private final DefaultTableModel model;
    private ArrayList<Item> order;
    private int orderSize = 0;
    public OrderCheck(String title, DB d, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        data = d;
        model = new DefaultTableModel();
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
        model.setRowCount(0);
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
        for (int i = 0; i < data.ordering.length; ++i) {
            comboBox1.addItem(new ComboItem(data.ordering[i][0].toString(), data.ordering[i][0].toString()));
        }
        if (comboBox1.getSelectedIndex() != -1) {
            order = new ArrayList<Item>();
            for (int i = 0; i < data.ordering.length; ++i) {
                if (Integer.parseInt(data.ordering[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    textField1.setText(Objects.toString(data.ordering[i][0], ""));
                    for (int j = 0; j < data.client.length; ++j) {
                        if (Integer.parseInt(data.client[j][0].toString()) == Integer.parseInt(data.ordering[i][1].toString())) {
                            textField2.setText(Objects.toString(data.client[j][1], ""));
                            textField6.setText(Objects.toString(data.client[j][2], ""));
                        }
                    }
                    for (int j = 0; j < data.employer.length; ++j) {
                        if (Integer.parseInt(data.employer[j][0].toString()) == Integer.parseInt(data.ordering[i][2].toString())) {
                            textField3.setText(Objects.toString(data.employer[j][1], ""));
                        }
                    }
                    textField4.setText(Objects.toString(data.ordering[i][3], ""));
                    textField5.setText(Objects.toString(data.ordering[i][4], ""));
                    comm = Objects.toString(data.ordering[i][5], "");
                    textField7.setText(comm);
                    break;
                }
            }
            for (int i = 0; i < data.corpse.length; ++i) {
                if (Integer.parseInt(data.corpse[i][5].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    OrderCorpseAdd item = new OrderCorpseAdd();
                    item.setName(data.corpse[i][1].toString());
                    item.setBdate(data.corpse[i][2].toString());
                    item.setDdate(data.corpse[i][3].toString());
                    order.add(item);
                    model.addRow(new Object[]{"Покойник", data.corpse[i][1].toString(), data.corpse[i][2].toString(), data.corpse[i][3].toString(), Objects.toString(data.corpse[i][4], "")});
                }
            }
            for (int i = 0; i < data.graveyard.length; ++i) {
                if (Integer.parseInt(data.graveyard[i][5].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    OrderGraveyardAdd item = new OrderGraveyardAdd();
                    item.setId(Integer.parseInt(data.graveyard[i][0].toString()));
                    item.setName(data.graveyard[i][1].toString());
                    item.setNumber(Integer.parseInt(data.graveyard[i][2].toString()));
                    item.setArea(Float.parseFloat(data.graveyard[i][4].toString()));
                    item.setPrice(Integer.parseInt(data.graveyard[i][3].toString()));
                    order.add(item);
                    model.addRow(new Object[]{"Место на кладбище", data.graveyard[i][1].toString(), "Место: " + data.graveyard[i][2].toString(), "Площадь: " + data.graveyard[i][4].toString(), data.graveyard[i][3].toString()});
                }
            }
            for (int i = 0; i < data.orderPlace.length; ++i) {
                if (Integer.parseInt(data.orderPlace[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    for (int j = 0; j < data.place.length; ++j) {
                        if (Integer.parseInt(data.place[j][0].toString()) == Integer.parseInt(data.orderPlace[i][1].toString())) {
                            OrderPlaceAdd item = new OrderPlaceAdd();
                            item.setId(Integer.parseInt(data.place[j][0].toString()));
                            item.setName(data.place[j][1].toString());
                            item.setAdress(data.place[j][2].toString());
                            order.add(item);
                            model.addRow(new Object[]{"Место церемонии", data.place[j][1].toString(), data.place[j][2].toString() == null ? "" : data.place[j][2].toString()});
                        }
                    }
                }
            }
            for (int i = 0; i < data.orderProducts.length; ++i) {
                if (Integer.parseInt(data.orderProducts[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    for (int j = 0; j < data.place.length; ++j) {
                        if (Integer.parseInt(data.product[j][0].toString()) == Integer.parseInt(data.orderProducts[i][1].toString())) {
                            for (int k = 0; k < data.productsCategory.length; ++k) {
                                if (Integer.parseInt(data.product[j][2].toString()) == Integer.parseInt(data.productsCategory[k][0].toString())) {
                                    OrderProductsAdd item = new OrderProductsAdd();
                                    item.setId(Integer.parseInt(data.product[j][0].toString()));
                                    item.setName(data.product[j][1].toString());
                                    item.setAmount(Integer.parseInt(data.orderProducts[i][2].toString()));
                                    item.setPrice(item.getAmount() * Integer.parseInt(data.product[j][3].toString()));
                                    item.setCategory(data.productsCategory[k][1].toString());
                                    order.add(item);
                                    model.addRow(new Object[]{"Товар", data.product[j][1].toString(), data.productsCategory[k][1].toString(), "Количество:" + data.orderProducts[i][2].toString(), Integer.parseInt(data.orderProducts[i][2].toString()) * Integer.parseInt(data.product[j][3].toString())});
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < data.orderServices.length; ++i) {
                if (Integer.parseInt(data.orderServices[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    for (int j = 0; j < data.service.length; ++j) {
                        if (Integer.parseInt(data.service[j][0].toString()) == Integer.parseInt(data.orderServices[i][1].toString())) {
                            OrderServicesAdd item = new OrderServicesAdd();
                            item.setId(Integer.parseInt(data.service[j][0].toString()));
                            item.setName(data.service[j][1].toString());
                            item.setPrice(Integer.parseInt(data.service[j][2].toString()));
                            order.add(item);
                            model.addRow(new Object[]{"Услуга", data.service[j][1].toString(), "", "", data.service[j][2].toString()});
                        }
                    }
                }
            }
            for (int i = 0; i < data.orderTransport.length; ++i) {
                if (Integer.parseInt(data.orderTransport[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    for (int j = 0; j < data.transport.length; ++j) {
                        if (Integer.parseInt(data.transport[j][0].toString()) == Integer.parseInt(data.orderTransport[i][1].toString())) {
                            OrderTransportAdd item = new OrderTransportAdd();
                            item.setId(Integer.parseInt(data.transport[j][0].toString()));
                            item.setModel(data.transport[j][1].toString());
                            item.setCapacity(Integer.parseInt(data.transport[j][2].toString()));
                            order.add(item);
                            model.addRow(new Object[]{"Транспорт", data.transport[j][1].toString(), "Мест: " + data.transport[j][2].toString()});
                        }
                    }
                }
            }
            orderSize = order.size();
        }
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField7.getText().equals(comm)) {
                    SQLServerDataSource ds = new SQLServerDataSource();
                    ds.setUser("admin");
                    ds.setPassword("admin");
                    ds.setServerName("localhost");
                    ds.setPortNumber(Integer.parseInt("1433"));
                    ds.setDatabaseName("Bureau");
                    ds.setTrustServerCertificate(true);

                    try {
                        Connection con = ds.getConnection();
                        CallableStatement cstmt = con.prepareCall("UPDATE Ordering SET comm = '" + textField7.getText() + "' WHERE id = " + Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue()) + ";");
                        cstmt.execute();
                    } catch (SQLException c) {
                        c.printStackTrace();
                    }
                    Exit();
                    control.setUpdate(true);
                } else {
                    Exit();
                }
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedIndex() != -1) {
                    order = new ArrayList<Item>();
                    model.setRowCount(0);
                    for (int i = 0; i < data.ordering.length; ++i) {
                        if (Integer.parseInt(data.ordering[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            textField1.setText(Objects.toString(data.ordering[i][0], ""));
                            for (int j = 0; j < data.client.length; ++j) {
                                if (Integer.parseInt(data.client[j][0].toString()) == Integer.parseInt(data.ordering[i][1].toString())) {
                                    textField2.setText(Objects.toString(data.client[j][1], ""));
                                    textField6.setText(Objects.toString(data.client[j][2], ""));
                                }
                            }
                            for (int j = 0; j < data.employer.length; ++j) {
                                if (Integer.parseInt(data.employer[j][0].toString()) == Integer.parseInt(data.ordering[i][2].toString())) {
                                    textField3.setText(Objects.toString(data.employer[j][1], ""));
                                }
                            }
                            textField4.setText(Objects.toString(data.ordering[i][3], ""));
                            textField5.setText(Objects.toString(data.ordering[i][4], ""));
                            comm = Objects.toString(data.ordering[i][5], "");
                            textField7.setText(comm);
                            break;
                        }
                    }
                    for (int i = 0; i < data.corpse.length; ++i) {
                        if (Integer.parseInt(data.corpse[i][5].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            OrderCorpseAdd item = new OrderCorpseAdd();
                            item.setName(data.corpse[i][1].toString());
                            item.setBdate(data.corpse[i][2].toString());
                            item.setDdate(data.corpse[i][3].toString());
                            order.add(item);
                            model.addRow(new Object[]{"Покойник", data.corpse[i][1].toString(), data.corpse[i][2].toString(), data.corpse[i][3].toString(), Objects.toString(data.corpse[i][4], "")});
                        }
                    }
                    for (int i = 0; i < data.graveyard.length; ++i) {
                        if (Integer.parseInt(data.graveyard[i][5].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            OrderGraveyardAdd item = new OrderGraveyardAdd();
                            item.setId(Integer.parseInt(data.graveyard[i][0].toString()));
                            item.setName(data.graveyard[i][1].toString());
                            item.setNumber(Integer.parseInt(data.graveyard[i][2].toString()));
                            item.setArea(Float.parseFloat(data.graveyard[i][4].toString()));
                            item.setPrice(Integer.parseInt(data.graveyard[i][3].toString()));
                            order.add(item);
                            model.addRow(new Object[]{"Место на кладбище", data.graveyard[i][1].toString(), "Место: " + data.graveyard[i][2].toString(), "Площадь: " + data.graveyard[i][4].toString(), data.graveyard[i][3].toString()});
                        }
                    }
                    for (int i = 0; i < data.orderPlace.length; ++i) {
                        if (Integer.parseInt(data.orderPlace[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            for (int j = 0; j < data.place.length; ++j) {
                                if (Integer.parseInt(data.place[j][0].toString()) == Integer.parseInt(data.orderPlace[i][1].toString())) {
                                    OrderPlaceAdd item = new OrderPlaceAdd();
                                    item.setId(Integer.parseInt(data.place[j][0].toString()));
                                    item.setName(data.place[j][1].toString());
                                    item.setAdress(data.place[j][2].toString());
                                    order.add(item);
                                    model.addRow(new Object[]{"Место церемонии", data.place[j][1].toString(), data.place[j][2].toString() == null ? "" : data.place[j][2].toString()});
                                }
                            }
                        }
                    }
                    for (int i = 0; i < data.orderProducts.length; ++i) {
                        if (Integer.parseInt(data.orderProducts[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            for (int j = 0; j < data.place.length; ++j) {
                                if (Integer.parseInt(data.product[j][0].toString()) == Integer.parseInt(data.orderProducts[i][1].toString())) {
                                    for (int k = 0; k < data.productsCategory.length; ++k) {
                                        if (Integer.parseInt(data.product[j][2].toString()) == Integer.parseInt(data.productsCategory[k][0].toString())) {
                                            OrderProductsAdd item = new OrderProductsAdd();
                                            item.setId(Integer.parseInt(data.product[j][0].toString()));
                                            item.setName(data.product[j][1].toString());
                                            item.setAmount(Integer.parseInt(data.orderProducts[i][2].toString()));
                                            item.setPrice(item.getAmount() * Integer.parseInt(data.product[j][3].toString()));
                                            item.setCategory(data.productsCategory[k][1].toString());
                                            order.add(item);
                                            model.addRow(new Object[]{"Товар", data.product[j][1].toString(), data.productsCategory[k][1].toString(), "Количество:" + data.orderProducts[i][2].toString(), Integer.parseInt(data.orderProducts[i][2].toString()) * Integer.parseInt(data.product[j][3].toString())});
                                        }
                                    }
                                }
                            }
                        }
                    }
                    for (int i = 0; i < data.orderServices.length; ++i) {
                        if (Integer.parseInt(data.orderServices[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            for (int j = 0; j < data.service.length; ++j) {
                                if (Integer.parseInt(data.service[j][0].toString()) == Integer.parseInt(data.orderServices[i][1].toString())) {
                                    OrderServicesAdd item = new OrderServicesAdd();
                                    item.setId(Integer.parseInt(data.service[j][0].toString()));
                                    item.setName(data.service[j][1].toString());
                                    item.setPrice(Integer.parseInt(data.service[j][2].toString()));
                                    order.add(item);
                                    model.addRow(new Object[]{"Услуга", data.service[j][1].toString(), "", "", data.service[j][2].toString()});
                                }
                            }
                        }
                    }
                    for (int i = 0; i < data.orderTransport.length; ++i) {
                        if (Integer.parseInt(data.orderTransport[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            for (int j = 0; j < data.transport.length; ++j) {
                                if (Integer.parseInt(data.transport[j][0].toString()) == Integer.parseInt(data.orderTransport[i][1].toString())) {
                                    OrderTransportAdd item = new OrderTransportAdd();
                                    item.setId(Integer.parseInt(data.transport[j][0].toString()));
                                    item.setModel(data.transport[j][1].toString());
                                    item.setCapacity(Integer.parseInt(data.transport[j][2].toString()));
                                    order.add(item);
                                    model.addRow(new Object[]{"Транспорт", data.transport[j][1].toString(), "Мест: " + data.transport[j][2].toString()});
                                }
                            }
                        }
                    }
                    orderSize = order.size();
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLServerDataSource ds = new SQLServerDataSource();
                ds.setUser("admin");
                ds.setPassword("admin");
                ds.setServerName("localhost");
                ds.setPortNumber(Integer.parseInt("1433"));
                ds.setDatabaseName("Bureau");
                ds.setTrustServerCertificate(true);

                try {
                    Connection con = ds.getConnection();
                    CallableStatement cstmt = con.prepareCall("UPDATE Ordering SET comm = 'Закрыт' WHERE id = " + Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue()) + ";");
                    cstmt.execute();
                } catch (SQLException c) {
                    c.printStackTrace();
                }
                for (int i = 0; i < data.corpse.length; ++i) {
                    if (Integer.parseInt(data.corpse[i][5].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                        try {
                            Connection con = ds.getConnection();
                            CallableStatement cstmt = con.prepareCall("UPDATE Corpse SET stage = 'Захоронено' WHERE id = " + Integer.parseInt(data.corpse[i][0].toString()) + ";");
                            cstmt.execute();
                        } catch (SQLException c) {
                            c.printStackTrace();
                        }
                    }
                }
                Exit();
                control.setUpdate(true);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedIndex() != 0 && comboBox1.getSelectedIndex() != -1) {
                    comboBox1.setSelectedIndex(comboBox1.getSelectedIndex() - 1);
                }

            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedIndex() < comboBox1.getItemCount() - 1 && comboBox1.getSelectedIndex() != -1) {
                    comboBox1.setSelectedIndex(comboBox1.getSelectedIndex() + 1);
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField7.getText().equals("Закрыт")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Невозможно изменить закрытый заказ.");
                    return;
                }
                JFrame temp;
                Thread tempThread;
                if (radioButton1.isSelected()) {
                    OrderCorpseAdd tempItem = new OrderCorpseAdd();
                    temp = new CorpseOrderAdd("Добавить покойника", data, control, tempItem, model, order);
                    temp.setIconImage(Main.getFDImage());
                    temp.setSize(600, 400);
                    temp.setVisible(true);
                    tempThread = new Thread(() -> {
                        while (temp.isDisplayable()) {
                            try {
                                sleep(500);
                            } catch (InterruptedException c) {
                                throw new RuntimeException(c);
                            }
                        }
                        if (orderSize != order.size()) {
                            order.get(order.size() - 1).updateDb(Integer.parseInt(textField1.getText()));
                            int price = 0;
                            for (int i = 0; i < order.size(); ++i) {
                                price += order.get(i).getPrice();
                            }
                            textField5.setText(String.valueOf(price));
                            SQLServerDataSource ds = new SQLServerDataSource();
                            ds.setUser("admin");
                            ds.setPassword("admin");
                            ds.setServerName("localhost");
                            ds.setPortNumber(Integer.parseInt("1433"));
                            ds.setDatabaseName("Bureau");
                            ds.setTrustServerCertificate(true);

                            try {
                                Connection con = ds.getConnection();
                                CallableStatement cstmt = con.prepareCall("UPDATE Ordering SET price = " + price + " WHERE id = " + textField1.getText() + ";");
                                cstmt.execute();
                            } catch (SQLException g) {
                                g.printStackTrace();
                            }
                            control.setUpdate(true);
                        }
                    });
                    tempThread.start();
                } else if (radioButton2.isSelected()) {
                    OrderGraveyardAdd tempItem = new OrderGraveyardAdd();
                    temp = new OrderGraveyardAddForm("Добавить место на кладбище", data, control, tempItem, model, order);
                    temp.setIconImage(Main.getFDImage());
                    temp.setSize(600, 400);
                    temp.setVisible(true);
                    tempThread = new Thread(() -> {
                        while (temp.isDisplayable()) {
                            try {
                                sleep(500);
                            } catch (InterruptedException c) {
                                throw new RuntimeException(c);
                            }
                        }
                        if (orderSize != order.size()) {
                            order.get(order.size() - 1).updateDb(Integer.parseInt(textField1.getText()));
                            int price = 0;
                            for (int i = 0; i < order.size(); ++i) {
                                price += order.get(i).getPrice();
                            }
                            textField5.setText(String.valueOf(price));
                            SQLServerDataSource ds = new SQLServerDataSource();
                            ds.setUser("admin");
                            ds.setPassword("admin");
                            ds.setServerName("localhost");
                            ds.setPortNumber(Integer.parseInt("1433"));
                            ds.setDatabaseName("Bureau");
                            ds.setTrustServerCertificate(true);

                            try {
                                Connection con = ds.getConnection();
                                CallableStatement cstmt = con.prepareCall("UPDATE Ordering SET price = " + price + " WHERE id = " + textField1.getText() + ";");
                                cstmt.execute();
                            } catch (SQLException g) {
                                g.printStackTrace();
                            }
                            control.setUpdate(true);
                        }
                    });
                    tempThread.start();
                } else if (radioButton3.isSelected()) {
                    OrderPlaceAdd tempItem = new OrderPlaceAdd();
                    temp = new OrderPlaceAddForm("Добавить место проведения", data, control, tempItem, model, order);
                    temp.setIconImage(Main.getFDImage());
                    temp.setSize(600, 400);
                    temp.setVisible(true);
                    tempThread = new Thread(() -> {
                        while (temp.isDisplayable()) {
                            try {
                                sleep(500);
                            } catch (InterruptedException c) {
                                throw new RuntimeException(c);
                            }
                        }
                        if (orderSize != order.size()) {
                            order.get(order.size() - 1).updateDb(Integer.parseInt(textField1.getText()));
                            int price = 0;
                            for (int i = 0; i < order.size(); ++i) {
                                price += order.get(i).getPrice();
                            }
                            textField5.setText(String.valueOf(price));
                            SQLServerDataSource ds = new SQLServerDataSource();
                            ds.setUser("admin");
                            ds.setPassword("admin");
                            ds.setServerName("localhost");
                            ds.setPortNumber(Integer.parseInt("1433"));
                            ds.setDatabaseName("Bureau");
                            ds.setTrustServerCertificate(true);

                            try {
                                Connection con = ds.getConnection();
                                CallableStatement cstmt = con.prepareCall("UPDATE Ordering SET price = " + price + " WHERE id = " + textField1.getText() + ";");
                                cstmt.execute();
                            } catch (SQLException g) {
                                g.printStackTrace();
                            }
                            control.setUpdate(true);
                        }
                    });
                    tempThread.start();
                } else if (radioButton4.isSelected()) {
                    OrderProductsAdd tempItem = new OrderProductsAdd();
                    temp = new OrderProductsAddForm("Добавить товар", data, control, tempItem, model, order);
                    temp.setIconImage(Main.getFDImage());
                    temp.setSize(600, 400);
                    temp.setVisible(true);
                    tempThread = new Thread(() -> {
                        while (temp.isDisplayable()) {
                            try {
                                sleep(500);
                            } catch (InterruptedException c) {
                                throw new RuntimeException(c);
                            }
                        }
                        if (orderSize != order.size()) {
                            order.get(order.size() - 1).updateDb(Integer.parseInt(textField1.getText()));
                            int price = 0;
                            for (int i = 0; i < order.size(); ++i) {
                                price += order.get(i).getPrice();
                            }
                            textField5.setText(String.valueOf(price));
                            SQLServerDataSource ds = new SQLServerDataSource();
                            ds.setUser("admin");
                            ds.setPassword("admin");
                            ds.setServerName("localhost");
                            ds.setPortNumber(Integer.parseInt("1433"));
                            ds.setDatabaseName("Bureau");
                            ds.setTrustServerCertificate(true);

                            try {
                                Connection con = ds.getConnection();
                                CallableStatement cstmt = con.prepareCall("UPDATE Ordering SET price = " + price + " WHERE id = " + textField1.getText() + ";");
                                cstmt.execute();
                            } catch (SQLException g) {
                                g.printStackTrace();
                            }
                            control.setUpdate(true);
                        }
                    });
                    tempThread.start();
                } else if (radioButton5.isSelected()) {
                    OrderServicesAdd tempItem = new OrderServicesAdd();
                    temp = new OrderServicesAddForm("Добавить услугу", data, control, tempItem, model, order);
                    temp.setIconImage(Main.getFDImage());
                    temp.setSize(600, 400);
                    temp.setVisible(true);
                    tempThread = new Thread(() -> {
                        while (temp.isDisplayable()) {
                            try {
                                sleep(500);
                            } catch (InterruptedException c) {
                                throw new RuntimeException(c);
                            }
                        }
                        if (orderSize != order.size()) {
                            order.get(order.size() - 1).updateDb(Integer.parseInt(textField1.getText()));
                            int price = 0;
                            for (int i = 0; i < order.size(); ++i) {
                                price += order.get(i).getPrice();
                            }
                            textField5.setText(String.valueOf(price));
                            SQLServerDataSource ds = new SQLServerDataSource();
                            ds.setUser("admin");
                            ds.setPassword("admin");
                            ds.setServerName("localhost");
                            ds.setPortNumber(Integer.parseInt("1433"));
                            ds.setDatabaseName("Bureau");
                            ds.setTrustServerCertificate(true);

                            try {
                                Connection con = ds.getConnection();
                                CallableStatement cstmt = con.prepareCall("UPDATE Ordering SET price = " + price + " WHERE id = " + textField1.getText() + ";");
                                cstmt.execute();
                            } catch (SQLException g) {
                                g.printStackTrace();
                            }
                            control.setUpdate(true);
                        }
                    });
                    tempThread.start();
                } else if (radioButton6.isSelected()) {
                    OrderTransportAdd tempItem = new OrderTransportAdd();
                    temp = new OrderTransportAddForm("Добавить транспорт", data, control, tempItem, model, order);
                    temp.setIconImage(Main.getFDImage());
                    temp.setSize(600, 400);
                    temp.setVisible(true);
                    tempThread = new Thread(() -> {
                        while (temp.isDisplayable()) {
                            try {
                                sleep(500);
                            } catch (InterruptedException c) {
                                throw new RuntimeException(c);
                            }
                        }
                        if (orderSize != order.size()) {
                            order.get(order.size() - 1).updateDb(Integer.parseInt(textField1.getText()));
                            int price = 0;
                            for (int i = 0; i < order.size(); ++i) {
                                price += order.get(i).getPrice();
                            }
                            textField5.setText(String.valueOf(price));
                            SQLServerDataSource ds = new SQLServerDataSource();
                            ds.setUser("admin");
                            ds.setPassword("admin");
                            ds.setServerName("localhost");
                            ds.setPortNumber(Integer.parseInt("1433"));
                            ds.setDatabaseName("Bureau");
                            ds.setTrustServerCertificate(true);

                            try {
                                Connection con = ds.getConnection();
                                CallableStatement cstmt = con.prepareCall("UPDATE Ordering SET price = " + price + " WHERE id = " + textField1.getText() + ";");
                                cstmt.execute();
                            } catch (SQLException g) {
                                g.printStackTrace();
                            }
                            control.setUpdate(true);
                        }
                    });
                    tempThread.start();
                }
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOrder();
            }
        });
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tempTable.getSelectedRow() != -1) {
                    order.get(tempTable.getSelectedRow()).deleteDb(Integer.parseInt(textField1.getText()));
                    control.setUpdate(true);
                }
            }
        });
    }

    public OrderCheck getOrder() {
        return this;
    }

    public void updateOrder() {
        if (comboBox1.getSelectedIndex() != -1) {
            model.setRowCount(0);
            order = new ArrayList<Item>();
            for (int i = 0; i < data.ordering.length; ++i) {
                if (Integer.parseInt(data.ordering[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    textField1.setText(Objects.toString(data.ordering[i][0], ""));
                    for (int j = 0; j < data.client.length; ++j) {
                        if (Integer.parseInt(data.client[j][0].toString()) == Integer.parseInt(data.ordering[i][1].toString())) {
                            textField2.setText(Objects.toString(data.client[j][1], ""));
                            textField6.setText(Objects.toString(data.client[j][2], ""));
                        }
                    }
                    for (int j = 0; j < data.employer.length; ++j) {
                        if (Integer.parseInt(data.employer[j][0].toString()) == Integer.parseInt(data.ordering[i][2].toString())) {
                            textField3.setText(Objects.toString(data.employer[j][1], ""));
                        }
                    }
                    textField4.setText(Objects.toString(data.ordering[i][3], ""));
                    textField5.setText(Objects.toString(data.ordering[i][4], ""));
                    comm = Objects.toString(data.ordering[i][5], "");
                    textField7.setText(comm);
                    break;
                }
            }
            for (int i = 0; i < data.corpse.length; ++i) {
                if (Integer.parseInt(data.corpse[i][5].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    OrderCorpseAdd item = new OrderCorpseAdd();
                    item.setName(data.corpse[i][1].toString());
                    item.setBdate(data.corpse[i][2].toString());
                    item.setDdate(data.corpse[i][3].toString());
                    order.add(item);
                    model.addRow(new Object[]{"Покойник", data.corpse[i][1].toString(), data.corpse[i][2].toString(), data.corpse[i][3].toString(), Objects.toString(data.corpse[i][4], "")});
                }
            }
            for (int i = 0; i < data.graveyard.length; ++i) {
                if (Integer.parseInt(data.graveyard[i][5].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    OrderGraveyardAdd item = new OrderGraveyardAdd();
                    item.setId(Integer.parseInt(data.graveyard[i][0].toString()));
                    item.setName(data.graveyard[i][1].toString());
                    item.setNumber(Integer.parseInt(data.graveyard[i][2].toString()));
                    item.setArea(Float.parseFloat(data.graveyard[i][4].toString()));
                    item.setPrice(Integer.parseInt(data.graveyard[i][3].toString()));
                    order.add(item);
                    model.addRow(new Object[]{"Место на кладбище", data.graveyard[i][1].toString(), "Место: " + data.graveyard[i][2].toString(), "Площадь: " + data.graveyard[i][4].toString(), data.graveyard[i][3].toString()});
                }
            }
            for (int i = 0; i < data.orderPlace.length; ++i) {
                if (Integer.parseInt(data.orderPlace[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    for (int j = 0; j < data.place.length; ++j) {
                        if (Integer.parseInt(data.place[j][0].toString()) == Integer.parseInt(data.orderPlace[i][1].toString())) {
                            OrderPlaceAdd item = new OrderPlaceAdd();
                            item.setId(Integer.parseInt(data.place[j][0].toString()));
                            item.setName(data.place[j][1].toString());
                            item.setAdress(data.place[j][2].toString());
                            order.add(item);
                            model.addRow(new Object[]{"Место церемонии", data.place[j][1].toString(), data.place[j][2].toString() == null ? "" : data.place[j][2].toString()});
                        }
                    }
                }
            }
            for (int i = 0; i < data.orderProducts.length; ++i) {
                if (Integer.parseInt(data.orderProducts[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    for (int j = 0; j < data.place.length; ++j) {
                        if (Integer.parseInt(data.product[j][0].toString()) == Integer.parseInt(data.orderProducts[i][1].toString())) {
                            for (int k = 0; k < data.productsCategory.length; ++k) {
                                if (Integer.parseInt(data.product[j][2].toString()) == Integer.parseInt(data.productsCategory[k][0].toString())) {
                                    OrderProductsAdd item = new OrderProductsAdd();
                                    item.setId(Integer.parseInt(data.product[j][0].toString()));
                                    item.setName(data.product[j][1].toString());
                                    item.setAmount(Integer.parseInt(data.orderProducts[i][2].toString()));
                                    item.setPrice(item.getAmount() * Integer.parseInt(data.product[j][3].toString()));
                                    item.setCategory(data.productsCategory[k][1].toString());
                                    order.add(item);
                                    model.addRow(new Object[]{"Товар", data.product[j][1].toString(), data.productsCategory[k][1].toString(), "Количество:" + data.orderProducts[i][2].toString(), Integer.parseInt(data.orderProducts[i][2].toString()) * Integer.parseInt(data.product[j][3].toString())});
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < data.orderServices.length; ++i) {
                if (Integer.parseInt(data.orderServices[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    for (int j = 0; j < data.service.length; ++j) {
                        if (Integer.parseInt(data.service[j][0].toString()) == Integer.parseInt(data.orderServices[i][1].toString())) {
                            OrderServicesAdd item = new OrderServicesAdd();
                            item.setId(Integer.parseInt(data.service[j][0].toString()));
                            item.setName(data.service[j][1].toString());
                            item.setPrice(Integer.parseInt(data.service[j][2].toString()));
                            order.add(item);
                            model.addRow(new Object[]{"Услуга", data.service[j][1].toString(), "", "", data.service[j][2].toString()});
                        }
                    }
                }
            }
            for (int i = 0; i < data.orderTransport.length; ++i) {
                if (Integer.parseInt(data.orderTransport[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    for (int j = 0; j < data.transport.length; ++j) {
                        if (Integer.parseInt(data.transport[j][0].toString()) == Integer.parseInt(data.orderTransport[i][1].toString())) {
                            OrderTransportAdd item = new OrderTransportAdd();
                            item.setId(Integer.parseInt(data.transport[j][0].toString()));
                            item.setModel(data.transport[j][1].toString());
                            item.setCapacity(Integer.parseInt(data.transport[j][2].toString()));
                            order.add(item);
                            model.addRow(new Object[]{"Транспорт", data.transport[j][1].toString(), "Мест: " + data.transport[j][2].toString()});
                        }
                    }
                }
            }
            orderSize = order.size();
        }
    }

    private void Exit() {
        this.setVisible(false);
        this.dispose();
    }
}
