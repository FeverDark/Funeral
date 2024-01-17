import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class OrderTransportAdd extends Item {
    private int id = -1;
    private String model = null;
    private int capacity = -1;

    public OrderTransportAdd() {
        this.setType(6);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void updateDb(int id) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO OrderTransport VALUES(" + id + ", " + this.id + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDb(int id) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("DELETE FROM OrderTransport WHERE order_id = " + id + " AND transport_id = " + this.id + ";");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
