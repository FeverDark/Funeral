import javax.swing.*;
import java.awt.*;

public class Controller {
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
    public void selectClient(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.client, data.clientNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
    public void selectContractor(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.contractor, data.contractorNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
    public void selectCorpse(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.corpse, data.corpseNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
    public void selectDocument(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.document, data.documentNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
    public void selectEmployer(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.employer, data.employerNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
    public void selectGraveyard(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.graveyard, data.graveyardNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
    public void selectOrdering(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.ordering, data.orderingNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
    public void selectOrderPlace(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.orderPlace, data.orderPlaceNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
    public void selectOrderProducts(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.orderProducts, data.orderProductsNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
    public void selectOrderServices(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.orderServices, data.orderServicesNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
    public void selectOrderTransport(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.orderTransport, data.orderTransportNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
    public void selectPlace(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.place, data.placeNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
    public void selectProduct(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.productConverted, data.productNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
    public void selectProductsCategory(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.productsCategory, data.productsCategoryNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }public void selectService(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.service, data.serviceNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
    public void selectTransport(JFrame frame, DB data){
        frame.getContentPane().removeAll();
        MainForm mainForm = new MainForm("main", data, this);
        tempTable = new JTable(data.transport, data.transportNames);
        tempTable.setPreferredScrollableViewportSize(new Dimension(250, 100));
        mainForm.getMainScroll().setViewportView(tempTable);
        frame.setContentPane(mainForm.getMainPanel());
        frame.revalidate();
        frame.repaint();
    }
}
