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
import java.util.Objects;

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
    private String comm = null;

    public OrderCheck(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        DefaultTableModel model = new DefaultTableModel();
        JTable tempTable = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            ;
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
        for (int i = 0; i < data.getOrdering().length; ++i) {
            comboBox1.addItem(new ComboItem(data.getOrdering()[i][0].toString(), data.getOrdering()[i][0].toString()));
        }
        if (comboBox1.getSelectedIndex() != -1) {
            for (int i = 0; i < data.getOrdering().length; ++i) {
                if (Integer.parseInt(data.getOrdering()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    textField1.setText(Objects.toString(data.getOrdering()[i][0], ""));
                    for (int j = 0; j < data.getClient().length; ++j) {
                        if (Integer.parseInt(data.getClient()[j][0].toString()) == Integer.parseInt(data.getOrdering()[i][1].toString())) {
                            textField2.setText(Objects.toString(data.getClient()[j][1], ""));
                            textField6.setText(Objects.toString(data.getClient()[j][2], ""));
                        }
                    }
                    for (int j = 0; j < data.getEmployer().length; ++j) {
                        if (Integer.parseInt(data.getEmployer()[j][0].toString()) == Integer.parseInt(data.getOrdering()[i][2].toString())) {
                            textField3.setText(Objects.toString(data.getEmployer()[j][1], ""));
                        }
                    }
                    textField4.setText(Objects.toString(data.getOrdering()[i][3], ""));
                    textField5.setText(Objects.toString(data.getOrdering()[i][4], ""));
                    comm = Objects.toString(data.getOrdering()[i][5], "");
                    textField7.setText(comm);
                    break;
                }
            }
            for (int i = 0; i < data.getCorpse().length; ++i) {
                if (Integer.parseInt(data.getCorpse()[i][5].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    model.addRow(new Object[]{"Покойник", data.getCorpse()[i][1].toString(), data.getCorpse()[i][2].toString(), data.getCorpse()[i][3].toString(), data.getCorpse()[i][4].toString()});
                }
            }
            for (int i = 0; i < data.getGraveyard().length; ++i) {
                if (Integer.parseInt(data.getGraveyard()[i][5].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    model.addRow(new Object[]{"Место на кладбище", data.getGraveyard()[i][1].toString(), data.getGraveyard()[i][2].toString(), data.getGraveyard()[i][4].toString(), data.getGraveyard()[i][3].toString()});
                }
            }
            for (int i = 0; i < data.getOrderPlace().length; ++i) {
                if (Integer.parseInt(data.getOrderPlace()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    for (int j = 0; j < data.getPlace().length; ++j) {
                        if (Integer.parseInt(data.getPlace()[j][0].toString()) == Integer.parseInt(data.getOrderPlace()[i][1].toString())) {
                            model.addRow(new Object[]{"Место церемонии", data.getPlace()[j][1].toString(), data.getPlace()[j][2].toString() == null ? "" : data.getPlace()[j][2].toString()});
                        }
                    }
                }
            }
            for (int i = 0; i < data.getOrderProducts().length; ++i) {
                if (Integer.parseInt(data.getOrderProducts()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    for (int j = 0; j < data.getPlace().length; ++j) {
                        if (Integer.parseInt(data.getProduct()[j][0].toString()) == Integer.parseInt(data.getOrderProducts()[i][1].toString())) {
                            for (int k = 0; k < data.getProductsCategory().length; ++k) {
                                if (Integer.parseInt(data.getProduct()[j][2].toString()) == Integer.parseInt(data.getProductsCategory()[k][0].toString())) {
                                    model.addRow(new Object[]{"Товар", data.getProduct()[j][1].toString(), data.getProductsCategory()[k][1].toString(), data.getOrderProducts()[i][2].toString(), data.getProduct()[j][3].toString()});
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < data.getOrderServices().length; ++i) {
                if (Integer.parseInt(data.getOrderServices()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    for (int j = 0; j < data.getService().length; ++j) {
                        if (Integer.parseInt(data.getService()[j][0].toString()) == Integer.parseInt(data.getOrderServices()[i][1].toString())) {
                            model.addRow(new Object[]{"Услуга", "", "", data.getService()[j][1].toString(), data.getService()[j][2].toString()});
                        }
                    }
                }
            }
            for (int i = 0; i < data.getOrderTransport().length; ++i) {
                if (Integer.parseInt(data.getOrderTransport()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                    for (int j = 0; j < data.getTransport().length; ++j) {
                        if (Integer.parseInt(data.getTransport()[j][0].toString()) == Integer.parseInt(data.getOrderTransport()[i][1].toString())) {
                            model.addRow(new Object[]{"Транспорт", data.getTransport()[j][1].toString(), data.getTransport()[j][2].toString()});
                        }
                    }
                }
            }
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
                    model.setRowCount(0);
                    for (int i = 0; i < data.getOrdering().length; ++i) {
                        if (Integer.parseInt(data.getOrdering()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            textField1.setText(Objects.toString(data.getOrdering()[i][0], ""));
                            for (int j = 0; j < data.getClient().length; ++j) {
                                if (Integer.parseInt(data.getClient()[j][0].toString()) == Integer.parseInt(data.getOrdering()[i][1].toString())) {
                                    textField2.setText(Objects.toString(data.getClient()[j][1], ""));
                                    textField6.setText(Objects.toString(data.getClient()[j][2], ""));
                                }
                            }
                            for (int j = 0; j < data.getEmployer().length; ++j) {
                                if (Integer.parseInt(data.getEmployer()[j][0].toString()) == Integer.parseInt(data.getOrdering()[i][2].toString())) {
                                    textField3.setText(Objects.toString(data.getEmployer()[j][1], ""));
                                }
                            }
                            textField4.setText(Objects.toString(data.getOrdering()[i][3], ""));
                            textField5.setText(Objects.toString(data.getOrdering()[i][4], ""));
                            comm = Objects.toString(data.getOrdering()[i][5], "");
                            textField7.setText(comm);
                            break;
                        }
                    }
                    for (int i = 0; i < data.getCorpse().length; ++i) {
                        if (Integer.parseInt(data.getCorpse()[i][5].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            model.addRow(new Object[]{"Покойник", data.getCorpse()[i][1].toString(), data.getCorpse()[i][2].toString(), data.getCorpse()[i][3].toString(), data.getCorpse()[i][4].toString()});
                        }
                    }
                    for (int i = 0; i < data.getGraveyard().length; ++i) {
                        if (Integer.parseInt(data.getGraveyard()[i][5].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            model.addRow(new Object[]{"Место на кладбище", data.getGraveyard()[i][1].toString(), data.getGraveyard()[i][2].toString(), data.getGraveyard()[i][4].toString(), data.getGraveyard()[i][3].toString()});
                        }
                    }
                    for (int i = 0; i < data.getOrderPlace().length; ++i) {
                        if (Integer.parseInt(data.getOrderPlace()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            for (int j = 0; j < data.getPlace().length; ++j) {
                                if (Integer.parseInt(data.getPlace()[j][0].toString()) == Integer.parseInt(data.getOrderPlace()[i][1].toString())) {
                                    model.addRow(new Object[]{"Место церемонии", data.getPlace()[j][1].toString(), data.getPlace()[j][2].toString() == null ? "" : data.getPlace()[j][2].toString()});
                                }
                            }
                        }
                    }
                    for (int i = 0; i < data.getOrderProducts().length; ++i) {
                        if (Integer.parseInt(data.getOrderProducts()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            for (int j = 0; j < data.getPlace().length; ++j) {
                                if (Integer.parseInt(data.getProduct()[j][0].toString()) == Integer.parseInt(data.getOrderProducts()[i][1].toString())) {
                                    for (int k = 0; k < data.getProductsCategory().length; ++k) {
                                        if (Integer.parseInt(data.getProduct()[j][2].toString()) == Integer.parseInt(data.getProductsCategory()[k][0].toString())) {
                                            model.addRow(new Object[]{"Товар", data.getProduct()[j][1].toString(), data.getProductsCategory()[k][1].toString(), data.getOrderProducts()[i][2].toString(), data.getProduct()[j][3].toString()});
                                        }
                                    }
                                }
                            }
                        }
                    }
                    for (int i = 0; i < data.getOrderServices().length; ++i) {
                        if (Integer.parseInt(data.getOrderServices()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            for (int j = 0; j < data.getService().length; ++j) {
                                if (Integer.parseInt(data.getService()[j][0].toString()) == Integer.parseInt(data.getOrderServices()[i][1].toString())) {
                                    model.addRow(new Object[]{"Услуга", data.getService()[j][1].toString(), data.getService()[j][2].toString()});
                                }
                            }
                        }
                    }
                    for (int i = 0; i < data.getOrderTransport().length; ++i) {
                        if (Integer.parseInt(data.getOrderTransport()[i][0].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                            for (int j = 0; j < data.getTransport().length; ++j) {
                                if (Integer.parseInt(data.getTransport()[j][0].toString()) == Integer.parseInt(data.getOrderTransport()[i][1].toString())) {
                                    model.addRow(new Object[]{"Транспорт", data.getTransport()[j][1].toString(), data.getTransport()[j][2].toString()});
                                }
                            }
                        }
                    }
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
                for (int i = 0; i < data.getCorpse().length; ++i) {
                    if (Integer.parseInt(data.getCorpse()[i][5].toString()) == Integer.parseInt(((ComboItem) comboBox1.getSelectedItem()).getValue())) {
                        try {
                            Connection con = ds.getConnection();
                            CallableStatement cstmt = con.prepareCall("UPDATE Corpse SET stage = 'Захоронено' WHERE id = " + Integer.parseInt(data.getCorpse()[i][0].toString()) + ";");
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
    }

    private void Exit() {
        this.setVisible(false);
        this.dispose();
    }
}
