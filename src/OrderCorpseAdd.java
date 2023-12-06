import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class OrderCorpseAdd extends Item {
    private String name = null;
    private String bdate = null;
    private String ddate = null;

    public OrderCorpseAdd() {
        this.setType(1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
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
            CallableStatement cstmt = con.prepareCall("INSERT INTO Corpse VALUES('" + name + "', '" + bdate + "', '" + ddate + "', " + "NULL" + ", " + id + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
