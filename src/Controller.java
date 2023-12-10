import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class Controller {
    private TableRowSorter<MyTableModel> sorter;
    private int state = 0;
    private boolean update = false;
    private JTable tempTable;

    public Controller() {
    }

    public TableRowSorter<MyTableModel> getSorter() {
        return sorter;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public JTable getTempTable() {
        return tempTable;
    }

    public void selectData(JFrame frame, DB data, Object[][] values, String[] names, String text, int cell) {
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
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        tempTable.setDefaultRenderer(String.class, leftRenderer);
        tempTable.setDefaultRenderer(Integer.class, leftRenderer);
        tempTable.setDefaultRenderer(Float.class, leftRenderer);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tempTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

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
