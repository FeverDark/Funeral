import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class OrderProductsAdd extends Item {
    private int id = -1;
    private String name = null;
    private int price = -1;
    private int amount = -1;
    private String category = null;

    public OrderProductsAdd() {
        this.setType(4);
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

    @Override
    public int getPrice() {
        return price * amount;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
            CallableStatement cstmt = con.prepareCall("INSERT INTO OrderProducts VALUES(" + id + ", " + this.id + ", " + amount + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("UPDATE Product SET stock = stock - " + amount + " WHERE id = " + this.id + ";");
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
            CallableStatement cstmt = con.prepareCall("DELETE FROM OrderProducts WHERE order_id = " + id + " AND product_id = " + this.id + ";");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("UPDATE Product SET stock = stock + " + amount + " WHERE id = " + this.id + ";");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
