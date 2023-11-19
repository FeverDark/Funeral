import java.sql.*;
import java.util.Arrays;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DB {
    public boolean isLogged = false;
    Object[][] client = {};
    public String[] clientNames = {"id", "name", "phone"};
    Object[][] contractor = {};
    public String[] contractorNames = {"name", "contact", "classification"};
    Object[][] corpse = {};
    public String[] corpseNames = {"id", "name", "birth_date", "death_dete", "stage", "order_id"};
    Object[][] document = {};
    public String[] documentNames = {"id", "employer_id", "contractor", "order_id", "link"};
    Object[][] employer = {};
    public String[] employerNames = {"id", "name", "job", "phone", "stage", "login_user", "pass"};
    Object[][] graveyard = {};
    public String[] graveyardNames = {"id", "name", "num", "price", "area", "order_id"};
    Object[][] ordering = {};
    Object[][] orderingConverted = {};
    public String[] orderingNames = {"id", "client_id", "employer_id", "order_date", "price", "comm"};
    Object[][] orderPlace = {};
    Object[][] orderPlaceConverted = {};
    public String[] orderPlaceNames = {"order_id", "place_id"};
    Object[][] orderProducts = {};
    Object[][] orderProductsConverted = {};
    public String[] orderProductsNames = {"order_id", "product_id", "amount"};
    Object[][] orderServices = {};
    Object[][] orderServicesConverted = {};
    public String[] orderServicesNames = {"order_id", "service_id"};
    Object[][] orderTransport = {};
    Object[][] orderTransportConverted = {};
    public String[] orderTransportNames = {"order_id", "transport_id"};
    Object[][] place = {};
    public String[] placeNames = {"id", "name", "adress"};
    Object[][] product = {};
    Object[][] productConverted;
    public String[] productNames = {"id", "name", "categorynumber", "price", "stock"};
    Object[][] productsCategory = {};
    public String[] productsCategoryNames = {"id", "name"};
    Object[][] service = {};
    public String[] serviceNames = {"id", "name", "price"};
    Object[][] transport = {};
    public String[] transportNames = {"id", "model", "capacity"};
    public DB() {

    }
    public void getData() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        client = new Object[][]{};
        try (Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("SELECT * FROM Client");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3)};
                client = Arrays.copyOf(client, client.length + 1);
                client[client.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        contractor = new Object[][]{};
        try (Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("SELECT * FROM Contractor");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)};
                contractor = Arrays.copyOf(contractor, contractor.length + 1);
                contractor[contractor.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        corpse = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Corpse");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)};
                corpse = Arrays.copyOf(corpse, corpse.length + 1);
                corpse[corpse.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        document = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Document");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5)};
                document = Arrays.copyOf(document, document.length + 1);
                document[document.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        employer = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Employer");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7)};
                employer = Arrays.copyOf(employer, employer.length + 1);
                employer[employer.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        graveyard = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Graveyard");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5), rs.getInt(6)};
                graveyard = Arrays.copyOf(graveyard, graveyard.length + 1);
                graveyard[graveyard.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ordering = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Ordering");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6)};
                ordering = Arrays.copyOf(ordering, ordering.length + 1);
                ordering[ordering.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orderPlace = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM OrderPlace");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getInt(2)};
                orderPlace = Arrays.copyOf(orderPlace, orderPlace.length + 1);
                orderPlace[orderPlace.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orderProducts = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM OrderProducts");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getInt(2), rs.getInt(3)};
                orderProducts = Arrays.copyOf(orderProducts, orderProducts.length + 1);
                orderProducts[orderProducts.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orderServices = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM OrderServices");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getInt(2)};
                orderServices = Arrays.copyOf(orderServices, orderServices.length + 1);
                orderServices[orderServices.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orderTransport = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM OrderTransport");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getInt(2)};
                orderTransport = Arrays.copyOf(orderTransport, orderTransport.length + 1);
                orderTransport[orderTransport.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        place = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Place");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3)};
                place = Arrays.copyOf(place, place.length + 1);
                place[place.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        product = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Product");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)};
                product = Arrays.copyOf(product, product.length + 1);
                product[product.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        productsCategory = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM ProductsCategory");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2)};
                productsCategory = Arrays.copyOf(productsCategory, productsCategory.length + 1);
                productsCategory[productsCategory.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        service = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Service");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getInt(3)};
                service = Arrays.copyOf(service, service.length + 1);
                service[service.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        transport = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Transport");) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getInt(3)};
                transport = Arrays.copyOf(transport, transport.length + 1);
                transport[transport.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*for(int i = 0; i<contractor.length; ++i) {
            for(int j = 0; j<contractor[i].length; ++j) {
                System.out.println(String.valueOf(contractor[i][j]));
            }
        }*/
        orderingConverted = Arrays.copyOf(ordering,  ordering.length);
        for (int i = 0; i< ordering.length; ++i) {
            for(int j = 0; j<client.length; ++j) {
                if (orderingConverted[i][1] == client[j][0]) {
                    orderingConverted[i][1] = client[j][1];
                }

            }
            for(int j = 0; j<employer.length; ++j) {
                if (orderingConverted[i][2] == employer[j][0]) {
                    orderingConverted[i][2] = employer[j][1];
                }

            }
        }
        orderPlaceConverted = Arrays.copyOf(orderPlace,  orderPlace.length);
        for (int i = 0; i< orderPlace.length; ++i) {
            for(int j = 0; j<place.length; ++j) {
                if (orderPlaceConverted[i][1] == place[j][0]) {
                    orderPlaceConverted[i][1] = place[j][1];
                }

            }
        }
        orderProductsConverted = Arrays.copyOf(orderProducts,  orderProducts.length);
        for (int i = 0; i< orderProducts.length; ++i) {
            for(int j = 0; j<product.length; ++j) {
                if (orderProductsConverted[i][1] == product[j][0]) {
                    orderProductsConverted[i][1] = product[j][1];
                }

            }
        }
        orderServicesConverted = Arrays.copyOf(orderServices,  orderServices.length);
        for (int i = 0; i< orderServices.length; ++i) {
            for(int j = 0; j<service.length; ++j) {
                if (orderServicesConverted[i][1] == service[j][0]) {
                    orderServicesConverted[i][1] = service[j][1];
                }

            }
        }
        orderTransportConverted = Arrays.copyOf(orderTransport, orderTransport.length);
        for (int i = 0; i< orderTransport.length; ++i) {
            for(int j = 0; j<transport.length; ++j) {
                if (orderTransportConverted[i][1] == transport[j][0]) {
                    orderTransportConverted[i][1] = transport[j][1];
                }

            }
        }
        productConverted = Arrays.copyOf(product, product.length);
        for (int i = 0; i< product.length; ++i) {
            for(int j = 0; j<productsCategory.length; ++j) {
                if (productConverted[i][2] == productsCategory[j][0]) {
                    productConverted[i][2] = productsCategory[j][1];
                }

            }
        }
    }

    public boolean login(String login, String password) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("{?=call dbo.loginInBureau(?, ?)}");
            cstmt.registerOutParameter(1, Types.BOOLEAN);
            cstmt.setString(2, login);
            cstmt.setString(3, password);
            cstmt.execute();
            return cstmt.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteClient(int i){
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                CallableStatement cstmt = con.prepareCall("DELETE FROM Client WHERE id = " + client[i][0]);) {
                cstmt.execute();
                System.arraycopy(client, i + 1, client, i, client.length - 1 - i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
