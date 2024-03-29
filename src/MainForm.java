import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private JLabel label1;
    private JScrollPane mainScroll;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JTextField filter;
    private JButton button20;
    private JButton orderButton;
    private JButton checkButton;
    private JButton exitButton;
    private JPanel tablePanel;

    public MainForm(String title, DB data, Controller control) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(1);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(2);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(3);
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(4);
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(5);
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(6);
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(7);
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(8);
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(9);
            }
        });
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(10);
            }
        });
        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(11);
            }
        });
        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(12);
            }
        });
        button13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(13);
            }
        });
        button14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(14);
            }
        });
        button15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(15);
            }
        });
        button16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setState(16);
            }
        });
        filter.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter(control);
                    }

                    public void insertUpdate(DocumentEvent e) {
                        newFilter(control);
                    }

                    public void removeUpdate(DocumentEvent e) {
                        newFilter(control);
                    }
                });
        button19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    int tempState = control.getState();
                    switch (tempState) {
                        case 1:
                            data.deleteClient(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 2:
                            if (!data.superUser) {
                                JOptionPane.showMessageDialog(new JFrame(), "Только руководство может удалять подрядчиков.");
                                return;
                            }
                            data.deleteContractor(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 3:
                            data.deleteCorpse(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 4:
                            data.deleteDocument(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 5:
                            if (!data.superUser) {
                                JOptionPane.showMessageDialog(new JFrame(), "Только руководство может увольнять сотрудников.");
                                return;
                            }
                            data.deleteEmployer(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 6:
                            data.deleteGraveyard(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 7:
                            data.deleteOrdering(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 8:
                            data.deleteOrderPlace(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 9:
                            data.deleteOrderProducts(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 10:
                            data.deleteOrderServices(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 11:
                            data.deleteOrderTransport(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 12:
                            data.deletePlace(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 13:
                            data.deleteProduct(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 14:
                            data.deleteProductCategory(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 15:
                            data.deleteService(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                        case 16:
                            if (!data.superUser) {
                                JOptionPane.showMessageDialog(new JFrame(), "Только руководство может удалять транспорт.");
                                return;
                            }
                            data.deleteTransport(control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                            break;
                    }
                    control.setUpdate(true);
                }).start();
            }
        });
        button18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    if (control.getTempTable().getSelectedRow() != -1) {
                        int tempState = control.getState();
                        JFrame temp;
                        switch (tempState) {
                            case 1:
                                temp = new ClientEdit("Изменить клиента", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                            case 2:
                                temp = new ContractorEdit("Изменить подрядчика", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                            case 3:
                                temp = new CorpseEdit("Изменить тело", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                            case 4:
                                temp = new DocumentEdit("Изменить документ", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                            case 5:
                                temp = new EmployerEdit("Изменить сотрудника", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                            case 6:
                                temp = new GraveyardEdit("Изменить место на кладбище", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                            case 7:
                                temp = new OrderingEdit("Изменить заказ", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                            case 8:
                                temp = new OrderPlaceEdit("Изменить место заказа", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(750, 400);
                                temp.setVisible(true);
                                break;
                            case 9:
                                temp = new OrderProductsEdit("Изменить товар заказа", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                            case 10:
                                temp = new OrderServicesEdit("Изменить услугу заказа", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                            case 11:
                                temp = new OrderTransportEdit("Изменить транспорт заказа", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                            case 12:
                                temp = new PlaceEdit("Изменить место", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                            case 13:
                                temp = new ProductEdit("Изменить товар", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                            case 14:
                                temp = new ProductsCategoryEdit("Изменить категорию товара", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                            case 15:
                                temp = new ServiceEdit("Изменить услугу", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                            case 16:
                                temp = new TransportEdit("Изменить транспорт", data, control, control.getTempTable().convertRowIndexToModel(control.getTempTable().getSelectedRow()));
                                temp.setIconImage(getFDImage());
                                temp.setSize(600, 400);
                                temp.setVisible(true);
                                break;
                        }
                    }
                }).start();
            }
        });
        button17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    int tempState = control.getState();
                    JFrame temp;
                    switch (tempState) {
                        case 1:
                            temp = new Client("Добавить клиента", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                        case 2:
                            temp = new Contractor("Добавить подрядчика", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                        case 3:
                            temp = new Corpse("Добавить тело", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                        case 4:
                            temp = new Document("Добавить документ", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                        case 5:
                            temp = new Employer("Добавить сотрудника", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                        case 6:
                            temp = new Graveyard("Добавить место на кладбище", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                        case 7:
                            temp = new Ordering("Добавить заказ", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                        case 8:
                            temp = new OrderPlace("Добавить место к заказу", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(750, 400);
                            temp.setVisible(true);
                            break;
                        case 9:
                            temp = new OrderProducts("Добавить товар к заказу", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                        case 10:
                            temp = new OrderServices("Добавить услугу к заказу", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                        case 11:
                            temp = new OrderTransport("Добавить транспорт к заказу", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                        case 12:
                            temp = new Place("Добавить место", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                        case 13:
                            temp = new Product("Добавить товар", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                        case 14:
                            temp = new ProductsCategory("Добавить категорию товара", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                        case 15:
                            temp = new Service("Добавить услугу", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                        case 16:
                            temp = new Transport("Добавить транспорт", data, control);
                            temp.setIconImage(getFDImage());
                            temp.setSize(600, 400);
                            temp.setVisible(true);
                            break;
                    }
                }).start();
            }
        });
        button20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setUpdate(true);
            }
        });
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    JFrame temp = new OrderCreation("Создание заказа", data, control);
                    temp.setIconImage(getFDImage());
                    temp.setSize(1000, 600);
                    temp.setVisible(true);
                }).start();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.isLogged = false;
            }
        });
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    JFrame temp = new OrderCheck("Просмотр заказов", data, control);
                    temp.setIconImage(getFDImage());
                    temp.setSize(1000, 600);
                    temp.setVisible(true);
                }).start();
            }
        });
    }

    protected static Image getFDImage() {
        java.net.URL imgURL = Main.class.getResource("images/favicon.png");
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            return null;
        }
    }

    private void newFilter(Controller control) {
        RowFilter<MyTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filter.getText(), 0, 1, 2, 3, 4, 5, 6);
        } catch (java.util.regex.PatternSyntaxException g) {
            return;
        }
        control.getSorter().setRowFilter(rf);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JLabel getLabel1() {
        return label1;
    }

    public JScrollPane getMainScroll() {
        return mainScroll;
    }
}
