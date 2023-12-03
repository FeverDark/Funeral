import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableColumn;
import java.awt.*;

public class Controller {
    public void setUpColumn(JTable table, Object[][] data, int dataN, TableColumn col) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();
        for(int i = 0; i < data.length; ++ i) {
            comboBox.addItem(data[i][dataN].toString());
        }

        col.setCellEditor(new DefaultCellEditor(comboBox));

        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        col.setCellRenderer(renderer);
    }
    public boolean voidCheck(Object data){
        if (data == null || data.toString().isEmpty()){
            JOptionPane.showMessageDialog(new JFrame(), "Поле не должно быть пустым.");
            return true;
        }
        return false;
    }
    public boolean uniqueCheck(Object data, int r, int c, Object[][] check){
        boolean unique = false;
        for(int i = 0; i < check.length; ++i){
            if (data.toString().equals(check[i][c].toString()) && i != r) {unique = true; break;}
        }
        if (unique) {
            JOptionPane.showMessageDialog(new JFrame(), "Поле должно быть уникальным.");
        }
        return unique;
    }
    private boolean checkId(Object o, Object[][] ordering) {
        for(int i = 0; i<ordering.length; ++ i) {
            if (o.toString().equals(ordering[i][0].toString())) {
                return false;
            }
        }
        JOptionPane.showMessageDialog(new JFrame(), "Поле должно ссылаться на ключ.");
        return true;
    }
    private TableRowSorter<MyTableModel> sorter;
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private int state = 0;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    private boolean update = false;

    public JTable getTempTable() {
        return tempTable;
    }

    private JTable tempTable;

    public Controller(){

    }
    public void selectData(JFrame frame, DB data, Object[][] values, String[] names, String text, int cell){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("Похоронное бюро", data, this);
        MyTableModel model = new MyTableModel();
        model.setData(values);
        model.setColumnNames(names);
        model.setEditCall(cell);
        sorter = new TableRowSorter<MyTableModel>(model);
        tempTable = new JTable(model);
        tempTable.setRowSorter(sorter);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment( JLabel.LEFT );
        tempTable.setDefaultRenderer(String.class, leftRenderer);
        tempTable.setDefaultRenderer(Integer.class, leftRenderer);
        tempTable.setDefaultRenderer(Float.class, leftRenderer);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tempTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );

        tempTable.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {
                        int viewRow = tempTable.getSelectedRow();
                        if (viewRow < 0) {
                            //Selection got filtered away.
                            //statusText.setText("");
                        } else {
                            int modelRow = tempTable.convertRowIndexToModel(viewRow);
                            //System.out.println();
                            //System.out.println(String.format("Selected Row in view: %d. " + "Selected Row in model: %d.", viewRow, modelRow));
                        }
                    }
                }
        );
        mainForm.getMainScroll().setViewportView(tempTable);
        mainForm.getLabel1().setText(text);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
}
