import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class OrderPlaceAdd extends Item {

    private int id = -1;
    private String name = null;
    private String adress = null;

    public OrderPlaceAdd() {
        this.setType(3);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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
            CallableStatement cstmt = con.prepareCall("INSERT INTO OrderPlace VALUES(" + id + ", " + this.id + ");");
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
            CallableStatement cstmt = con.prepareCall("DELETE FROM OrderPlace WHERE order_id = " + id + " AND place_id = " + this.id + ";");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
