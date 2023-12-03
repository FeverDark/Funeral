import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.apache.commons.lang3.ArrayUtils;

import java.sql.*;
import java.util.Arrays;

public class DB {
    public boolean isLogged = false;
    public String[] clientNames = {"id", "name", "phone"};
    public String[] contractorNames = {"name", "contact", "classification"};
    public String[] corpseNames = {"id", "name", "birth_date", "death_dete", "stage", "order_id"};
    public String[] documentNames = {"id", "employer_id", "contractor", "order_id", "link"};
    public String[] employerNames = {"id", "name", "job", "phone", "stage", "login_user", "pass"};
    public String[] graveyardNames = {"id", "name", "num", "price", "area", "order_id"};
    public String[] orderingNames = {"id", "client_id", "employer_id", "order_date", "price", "comm"};
    public String[] orderPlaceNames = {"order_id", "place_id"};
    public String[] orderProductsNames = {"order_id", "product_id", "amount"};
    public String[] orderServicesNames = {"order_id", "service_id"};
    public String[] orderTransportNames = {"order_id", "transport_id"};
    public String[] placeNames = {"id", "name", "adress"};
    public String[] productNames = {"id", "name", "categorynumber", "price", "stock"};
    public String[] productsCategoryNames = {"id", "name"};
    public String[] serviceNames = {"id", "name", "price"};
    public String[] transportNames = {"id", "model", "capacity"};
    Object[][] client = {};
    Object[][] contractor = {};
    Object[][] corpse = {};
    Object[][] document = {};
    Object[][] documentConverted = {};
    Object[][] employer = {};
    Object[][] graveyard = {};
    Object[][] ordering = {};
    Object[][] orderingConverted = {};
    Object[][] orderPlace = {};
    Object[][] orderPlaceConverted = {};
    Object[][] orderProducts = {};
    Object[][] orderProductsConverted = {};
    Object[][] orderServices = {};
    Object[][] orderServicesConverted = {};
    Object[][] orderTransport = {};
    Object[][] orderTransportConverted = {};
    Object[][] place = {};
    Object[][] product = {};
    Object[][] productConverted;
    Object[][] productsCategory = {};
    Object[][] service = {};
    Object[][] transport = {};

    public DB() {

    }

    public Object getClient(int x, int y) {
        return client[x][y];
    }

    public Object[][] getClient() {
        return client;
    }

    public Object getContractor(int x, int y) {
        return contractor[x][y];
    }

    public Object[][] getContractor() {
        return contractor;
    }

    public Object getCorpse(int x, int y) {
        return corpse[x][y];
    }

    public Object[][] getCorpse() {
        return corpse;
    }

    public Object getDocument(int x, int y) {
        return document[x][y];
    }

    public Object[][] getDocument() {
        return document;
    }

    public Object getEmployer(int x, int y) {
        return employer[x][y];
    }

    public Object[][] getEmployer() {
        return employer;
    }

    public Object getGraveyard(int x, int y) {
        return graveyard[x][y];
    }

    public Object[][] getGraveyard() {
        return graveyard;
    }

    public Object getOrdering(int x, int y) {
        return ordering[x][y];
    }

    public Object[][] getOrdering() {
        return ordering;
    }

    public Object getOrderPlace(int x, int y) {
        return orderPlace[x][y];
    }

    public Object[][] getOrderPlace() {
        return orderPlace;
    }

    public Object[][] getOrderPlaceConverted() {
        return orderPlaceConverted;
    }

    public Object getOrderPProducts(int x, int y) {
        return orderProducts[x][y];
    }

    public Object[][] getOrderProducts() {
        return orderProducts;
    }

    public Object[][] getOrderProductsConverted() {
        return orderProductsConverted;
    }

    public Object getOrderServices(int x, int y) {
        return orderServices[x][y];
    }

    public Object[][] getOrderServices() {
        return orderServices;
    }

    public Object[][] getOrderServicesConverted() {
        return orderServicesConverted;
    }

    public Object getOrderTransport(int x, int y) {
        return orderTransport[x][y];
    }

    public Object[][] getOrderTransport() {
        return orderTransport;
    }

    public Object[][] getOrderTransportConverted() {
        return orderTransportConverted;
    }

    public Object getPlace(int x, int y) {
        return place[x][y];
    }

    public Object[][] getPlace() {
        return place;
    }

    public Object getProduct(int x, int y) {
        return product[x][y];
    }

    public Object[][] getProduct() {
        return product;
    }

    public Object[][] getProductConverted() {
        return productConverted;
    }

    public Object getProductsCategory(int x, int y) {
        return productsCategory[x][y];
    }

    public Object[][] getProductsCategory() {
        return productsCategory;
    }

    public Object getService(int x, int y) {
        return service[x][y];
    }

    public Object[][] getService() {
        return service;
    }

    public Object getTransport(int x, int y) {
        return transport[x][y];
    }

    public Object[][] getTransport() {
        return transport;
    }

    public void getData() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        Object[][] tempclient = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Client")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3)};
                tempclient = Arrays.copyOf(tempclient, tempclient.length + 1);
                tempclient[tempclient.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        client = Arrays.copyOf(tempclient, tempclient.length);
        Object[][] tempcontractor = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Contractor")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)};
                tempcontractor = Arrays.copyOf(tempcontractor, tempcontractor.length + 1);
                tempcontractor[tempcontractor.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        contractor = Arrays.copyOf(tempcontractor, tempcontractor.length);
        Object[][] tempcorpse = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Corpse")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)};
                tempcorpse = Arrays.copyOf(tempcorpse, tempcorpse.length + 1);
                tempcorpse[tempcorpse.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        corpse = Arrays.copyOf(tempcorpse, tempcorpse.length);
        Object[][] tempdocument = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Document")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5)};
                tempdocument = Arrays.copyOf(tempdocument, tempdocument.length + 1);
                tempdocument[tempdocument.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        document = Arrays.copyOf(tempdocument, tempdocument.length);
        Object[][] tempemployer = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Employer")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7)};
                tempemployer = Arrays.copyOf(tempemployer, tempemployer.length + 1);
                tempemployer[tempemployer.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        employer = Arrays.copyOf(tempemployer, tempemployer.length);
        Object[][] tempgraveyard = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Graveyard")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5), rs.getInt(6)};
                tempgraveyard = Arrays.copyOf(tempgraveyard, tempgraveyard.length + 1);
                tempgraveyard[tempgraveyard.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        graveyard = Arrays.copyOf(tempgraveyard, tempgraveyard.length);
        Object[][] tempordering = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Ordering")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6)};
                tempordering = Arrays.copyOf(tempordering, tempordering.length + 1);
                tempordering[tempordering.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ordering = Arrays.copyOf(tempordering, tempordering.length);
        Object[][] temporderPlace = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM OrderPlace")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getInt(2)};
                temporderPlace = Arrays.copyOf(temporderPlace, temporderPlace.length + 1);
                temporderPlace[temporderPlace.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orderPlace = Arrays.copyOf(temporderPlace, temporderPlace.length);
        Object[][] temporderProducts = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM OrderProducts")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getInt(2), rs.getInt(3)};
                temporderProducts = Arrays.copyOf(temporderProducts, temporderProducts.length + 1);
                temporderProducts[temporderProducts.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orderProducts = Arrays.copyOf(temporderProducts, temporderProducts.length);
        Object[][] temporderServices = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM OrderServices")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getInt(2)};
                temporderServices = Arrays.copyOf(temporderServices, temporderServices.length + 1);
                temporderServices[temporderServices.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orderServices = Arrays.copyOf(temporderServices, temporderServices.length);
        Object[][] temporderTransport = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM OrderTransport")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getInt(2)};
                temporderTransport = Arrays.copyOf(temporderTransport, temporderTransport.length + 1);
                temporderTransport[temporderTransport.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orderTransport = Arrays.copyOf(temporderTransport, temporderTransport.length);
        Object[][] tempplace = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Place")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3)};
                tempplace = Arrays.copyOf(tempplace, tempplace.length + 1);
                tempplace[tempplace.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        place = Arrays.copyOf(tempplace, tempplace.length);
        Object[][] tempproduct = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Product")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)};
                tempproduct = Arrays.copyOf(tempproduct, tempproduct.length + 1);
                tempproduct[tempproduct.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        product = Arrays.copyOf(tempproduct, tempproduct.length);
        Object[][] tempproductsCategory = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM ProductsCategory")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2)};
                tempproductsCategory = Arrays.copyOf(tempproductsCategory, tempproductsCategory.length + 1);
                tempproductsCategory[tempproductsCategory.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        productsCategory = Arrays.copyOf(tempproductsCategory, tempproductsCategory.length);
        Object[][] tempservice = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Service")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getInt(3)};
                tempservice = Arrays.copyOf(tempservice, tempservice.length + 1);
                tempservice[tempservice.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        service = Arrays.copyOf(tempservice, tempservice.length);
        Object[][] temptransport = new Object[][]{};
        try (Connection con = ds.getConnection();
             CallableStatement cstmt = con.prepareCall("SELECT * FROM Transport")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Object[] temp = new Object[]{rs.getInt(1), rs.getString(2), rs.getInt(3)};
                temptransport = Arrays.copyOf(temptransport, temptransport.length + 1);
                temptransport[temptransport.length - 1] = temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        transport = Arrays.copyOf(temptransport, temptransport.length);
        documentConverted = Arrays.stream(document).map(Object[]::clone).toArray(Object[][]::new);
        for (int i = 0; i < documentConverted.length; ++i) {
            for (int j = 0; j < employer.length; ++j) {
                if (documentConverted[i][1] == employer[j][0]) {
                    documentConverted[i][1] = employer[j][1];
                }

            }
        }
        orderingConverted = Arrays.stream(ordering).map(Object[]::clone).toArray(Object[][]::new);
        for (int i = 0; i < orderingConverted.length; ++i) {
            for (int j = 0; j < client.length; ++j) {
                if (orderingConverted[i][1] == client[j][0]) {
                    orderingConverted[i][1] = client[j][1];
                }

            }
            for (int j = 0; j < employer.length; ++j) {
                if (orderingConverted[i][2] == employer[j][0]) {
                    orderingConverted[i][2] = employer[j][1];
                }

            }
        }
        orderPlaceConverted = Arrays.stream(orderPlace).map(Object[]::clone).toArray(Object[][]::new);
        for (int i = 0; i < orderPlaceConverted.length; ++i) {
            for (int j = 0; j < place.length; ++j) {
                if (orderPlaceConverted[i][1] == place[j][0]) {
                    orderPlaceConverted[i][1] = place[j][1];
                }

            }
        }
        orderProductsConverted = Arrays.stream(orderProducts).map(Object[]::clone).toArray(Object[][]::new);
        for (int i = 0; i < orderProductsConverted.length; ++i) {
            for (int j = 0; j < product.length; ++j) {
                if (orderProductsConverted[i][1] == product[j][0]) {
                    orderProductsConverted[i][1] = product[j][1];
                }

            }
        }
        orderServicesConverted = Arrays.stream(orderServices).map(Object[]::clone).toArray(Object[][]::new);
        for (int i = 0; i < orderServicesConverted.length; ++i) {
            for (int j = 0; j < service.length; ++j) {
                if (orderServicesConverted[i][1] == service[j][0]) {
                    orderServicesConverted[i][1] = service[j][1];
                }

            }
        }
        orderTransportConverted = Arrays.stream(orderTransport).map(Object[]::clone).toArray(Object[][]::new);
        for (int i = 0; i < orderTransportConverted.length; ++i) {
            for (int j = 0; j < transport.length; ++j) {
                if (orderTransportConverted[i][1] == transport[j][0]) {
                    orderTransportConverted[i][1] = transport[j][1];
                }

            }
        }
        productConverted = Arrays.stream(product).map(Object[]::clone).toArray(Object[][]::new);
        for (int i = 0; i < productConverted.length; ++i) {
            for (int j = 0; j < productsCategory.length; ++j) {
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

    public void deleteClient(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM Client WHERE id = " + client[i][0])) {
                cstmt.execute();
                client = ArrayUtils.remove(client, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteContractor(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM Contractor WHERE name = '" + contractor[i][0] + "';")) {
                cstmt.execute();
                contractor = ArrayUtils.remove(contractor, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteCorpse(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM Corpse WHERE id = " + corpse[i][0])) {
                cstmt.execute();
                corpse = ArrayUtils.remove(corpse, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteDocument(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM Document WHERE id = " + document[i][0])) {
                cstmt.execute();
                document = ArrayUtils.remove(document, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteEmployer(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM Employer WHERE id = " + employer[i][0])) {
                cstmt.execute();
                employer = ArrayUtils.remove(employer, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteGraveyard(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM Graveyard WHERE id = " + graveyard[i][0])) {
                cstmt.execute();
                graveyard = ArrayUtils.remove(graveyard, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteOrdering(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM Ordering WHERE id = " + ordering[i][0])) {
                cstmt.execute();
                ordering = ArrayUtils.remove(ordering, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteOrderPlace(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM OrderPlace WHERE order_id = " + orderPlace[i][0] + " AND place_id = " + orderPlace[i][1])) {
                cstmt.execute();
                orderPlace = ArrayUtils.remove(orderPlace, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteOrderProducts(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM OrderProducts WHERE order_id = " + orderProducts[i][0] + " AND product_id = " + orderProducts[i][1])) {
                cstmt.execute();
                orderProducts = ArrayUtils.remove(orderProducts, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteOrderServices(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM OrderServices WHERE order_id = " + orderServices[i][0] + " AND service_id = " + orderServices[i][1])) {
                cstmt.execute();
                orderServices = ArrayUtils.remove(orderServices, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteOrderTransport(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM OrderTransport WHERE order_id = " + orderTransport[i][0] + " AND transport_id = " + orderTransport[i][1])) {
                cstmt.execute();
                orderTransport = ArrayUtils.remove(orderTransport, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deletePlace(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM Place WHERE id = " + place[i][0])) {
                cstmt.execute();
                place = ArrayUtils.remove(place, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteProduct(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM Product WHERE id = " + product[i][0])) {
                cstmt.execute();
                product = ArrayUtils.remove(product, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteProductCategory(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM ProductCategory WHERE id = " + productsCategory[i][0])) {
                cstmt.execute();
                productsCategory = ArrayUtils.remove(productsCategory, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteService(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM Service WHERE id = " + service[i][0])) {
                cstmt.execute();
                service = ArrayUtils.remove(service, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteTransport(int i) {
        if (i > -1) {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("admin");
            ds.setPassword("admin");
            ds.setServerName("localhost");
            ds.setPortNumber(Integer.parseInt("1433"));
            ds.setDatabaseName("Bureau");
            ds.setTrustServerCertificate(true);

            try (Connection con = ds.getConnection();
                 CallableStatement cstmt = con.prepareCall("DELETE FROM Transport WHERE id = " + transport[i][0])) {
                cstmt.execute();
                transport = ArrayUtils.remove(transport, i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addClient(String id, String fio, String phone) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO Client VALUES(" + id + ", '" + fio + "', '" + phone + "');");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addContractor(String name, String contact, String desc) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO Contractor VALUES('" + name + "', '" + contact + "', " + (desc.equals("") ? "NULL" : "'" + desc + "'") + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addCorpse(String id, String fio, String bd, String dd, String state, String order) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO Corpse VALUES(" + id + ", '" + fio + "', '" + bd + "', '" + dd + "', " + (state.equals("") ? "NULL" : "'" + state + "'") + ", " + order + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addDocument(String id, String employer, String contractor, String order, String link) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO Document VALUES(" + id + ", " + employer + ", " + (contractor.equals("NULL") ? "NULL" : ("'" + contractor + "'")) + ", " + (order.equals("NULL") ? "NULL" : (order)) + ", '" + link + "');");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addEmployer(String id, String fio, String job, String phone, String stage, String login, String password) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO Employer VALUES(" + id + ", '" + fio + "', '" + fio + "', '" + job + "', '" + phone + "', " + (stage.equals("") ? "NULL" : stage) + ", '" + login + "', '" + password + "');");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addGraveyard(String id, String name, String number, String price, String area, String order) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO Graveyard VALUES(" + id + ", '" + name + "', " + number + ", " + price + ", " + area + ", " + (order.equals("NULL") ? "NULL" : (order)) + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addOrdering(String id, String client, String employer, String data, String price, String com) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO Ordering VALUES(" + id + ", " + client + ", " + employer + ", '" + data + "', " + price + ", " + (com.equals("NULL") ? "NULL" : ("'" + com + "'")) + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addOrderPlace(String order, String place) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO OrderPlace VALUES(" + order + ", " + place + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addOrderProducts(String order, String product, String amount) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO OrderProducts VALUES(" + order + ", " + product + ", " + (amount.equals("") ? "NULL" : amount) + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addOrderServices(String order, String service) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO OrderServices VALUES(" + order + ", " + service + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addOrderTransport(String order, String transport) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO OrderTransport VALUES(" + order + ", " + transport + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addPlace(String id, String name, String adress) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO Place VALUES(" + id + ", '" + name + "', " + (adress.equals("") ? "NULL" : "'" + adress + "'") + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addProduct(String id, String name, String category, String price, String stock) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO Product VALUES(" + id + ", '" + name + "', " + category + ", " + price + ", " + (stock.equals("") ? "NULL" : "'" + stock + "'") + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addProductsCategory(String id, String name) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO ProductsCategory VALUES(" + id + ", '" + name + "');");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addService(String id, String name, String price) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO Service VALUES(" + id + ", '" + name + "', " + price + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addTransport(String id, String name, String capacity) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("INSERT INTO Transport VALUES(" + id + ", '" + name + "', " + (capacity.equals("") ? "NULL" : capacity) + ");");
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /*public boolean updateClient(int r, int c, Object data) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("UPDATE Client SET name = '" + client[r][1] + "', phone = '" + client[r][2] +"' WHERE id = " + client[r][0]);
            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateContractor(int r, int c, Object data) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("UPDATE Contractor SET name = '" + contractor[r][0] + "', contact = '" + contractor[r][1] + "', classification = '" + contractor[r][2] +"' WHERE name = '" + contractor[r][0] + "'");
            //cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateCorpse(int r, int c, Object data) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("UPDATE Corpse SET name = '" + corpse[r][1] + "', bith_date = '" + corpse[r][2] + "', death_date = '" + corpse[r][3] +"', stage = '" + corpse[r][4] +"', order_id = " + corpse[r][5] + " WHERE id = " + corpse[r][0] + "");
            //cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateDocument(int r, int c, Object data) {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("admin");
        ds.setPassword("admin");
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("Bureau");
        ds.setTrustServerCertificate(true);

        try {
            Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("UPDATE Corpse SET name = '" + corpse[r][1] + "', bith_date = '" + corpse[r][2] + "', death_date = '" + corpse[r][3] +"', stage = '" + corpse[r][4] +"', order_id = " + corpse[r][5] + " WHERE id = " + corpse[r][0] + "");
            //cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }*/
}
