package main.store;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBConnection {
    private Connection con;
    private PreparedStatement preparedStatement;
    SQLiteDataSource dataSource;
    private ResultSet resultSet;
    private ResultSet resultSet2;

    public DBConnection(String url) {
        try {
            dataSource = new SQLiteDataSource();
            dataSource.setUrl("jdbc:sqlite:" + url);
            con = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(String UserName, String role ,String password) {
        String insertAccount = "INSERT INTO Accounts (userName, role, password) VALUES  (?, ?, ?)";
        try{
            preparedStatement = con.prepareStatement(insertAccount);
            preparedStatement.setString(1, UserName);
            preparedStatement.setString(2, role);
            preparedStatement.setString(3, password);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStore(int Id, String location) {
        String insertStore = "INSERT INTO Store VALUES (?,?)";
        try {
            preparedStatement = con.prepareStatement(insertStore);
            preparedStatement.setInt(1, Id);
            preparedStatement.setString(2, location);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account fetchAccount(String userName) {
        String selectAccount = "SELECT * FROM Accounts WHERE userName = ?";
        try {
            preparedStatement = con.prepareStatement(selectAccount);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Account(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Store fetchStore(int id) {
        String selectStore = "SELECT userName FROM Accounts, Store WHERE accountId = ?";

        try {
            preparedStatement = con.prepareStatement(selectStore);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(1);
                return new Store(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean nameIsFree(String userName) {

        String selectAccount = "SELECT * FROM Accounts WHERE userName = ?";
        try {
            preparedStatement = con.prepareStatement(selectAccount);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Product fetchProduct(String productName) {
        String selectProduct = "SELECT * FROM Product WHERE productName = ?";
        try {
            preparedStatement = con.prepareStatement(selectProduct);
            preparedStatement.setString(1, productName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product fetchProduct(int id) {
        String selectProduct = "SELECT * FROM Product WHERE productId = ?";
        try {
            preparedStatement = con.prepareStatement(selectProduct);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addNewProduct(String productName, String category) {
        String insertProduct = "INSERT INTO Product (productName, category) VALUES (?,?)";
        try {
            preparedStatement = con.prepareStatement(insertProduct);
            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, category);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void issueGRN(Account acc, Product product, int quantity) {
        String insertProduct = "INSERT INTO GRN (storeId, productId, quantity) VALUES (?,?,?)";
        try {
            preparedStatement = con.prepareStatement(insertProduct);
            preparedStatement.setInt(1, acc.getAccId());
            preparedStatement.setInt(2, product.getId());
            preparedStatement.setInt(3, quantity);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newInventory(int id, Product product) {
        String insertInventory = "INSERT INTO Inventory (storeId, productId) VALUES (?,?)";
        try {
            preparedStatement = con.prepareStatement(insertInventory);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, product.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateInventory(int id, Product product, int quantity) {
        String updateInventory = "UPDATE Inventory SET quantity = ? WHERE storeId = ? AND productId = ?";
        try {
            preparedStatement = con.prepareStatement(updateInventory);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(3, product.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[][] fetchAllInventory(int id) {
        String selectAllStock = "SELECT productName, quantity " +
                "FROM Product INNER JOIN Inventory I on Product.productId = I.productId " +
                "WHERE storeId = ? order by productName";

        List<Inventory> list = new LinkedList<>();
        String[][] inventory;
        try {
            preparedStatement = con.prepareStatement(selectAllStock);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Inventory(
                        resultSet.getString(1),
                        resultSet.getInt(2)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        inventory = new String[list.size()][3];
        int i = 0;
        int j;
        for (Inventory item: list) {
            j = 0;
            inventory[i][j++] = String.valueOf(i + 1);
            inventory[i][j++] = item.getProduct();
            inventory[i][j] = String.format("%d pcs", item.getQuantity());
            i++;
        }
        return inventory;
    }

    public int fetchInventory(int id, Product product) {
        String getStock = "SELECT quantity FROM Inventory WHERE storeId = ? AND productId = ?";
        try {
            preparedStatement = con.prepareStatement(getStock);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, product.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void addProduct(Account acc, String productName, String category, int quantity) {
        int stock;
        Product product = fetchProduct(productName);
        if (product == null) {
            addNewProduct(productName, category);
            product = fetchProduct(productName);
        }
        issueGRN(acc, product, quantity);
        stock = fetchInventory(acc.getAccId(), product);
        if (stock == -1) {
            newInventory(acc.getAccId(), product);
            updateInventory(acc.getAccId(), product, quantity);
        } else {
            updateInventory(acc.getAccId(), product, quantity + stock);
        }
    }

    public void receiveProduct(int id, Product product, int quantity) {
        int stock = fetchInventory(id, product);
        if (stock == -1) {
            newInventory(id, product);
            updateInventory(id, product, quantity);
        } else {
            updateInventory(id, product, quantity + stock);
        }
    }

    public Store[] fetchStoreList(Account acc) {
        String getStoreList = "SELECT storeId, userName FROM Accounts, Store WHERE accountId =  Store.storeId";
        List<Store> list = new LinkedList<>();
        Store[] storeArray;
        int i = 0;
        try {
            preparedStatement = con.prepareStatement(getStoreList);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Store(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Store currentStore = new Store(acc.getAccId(), acc.getUserName());
        System.out.println("id: " + acc.getAccId() + "\nName: " + acc.getUserName());
        storeArray = new Store[list.size() - 1];
        for (Store s : list) {
            if (acc.getAccId() != s.storeId) {
                storeArray[i++] = s;
            }
        }
        return storeArray;
    }

    public Product[] fetchStoreProduct(int id) {
        String getProduct = "SELECT Product.productId, productName, category FROM Product " +
                "INNER JOIN Inventory I on Product.productId = I.productId AND storeId = ?";
        List<Product> list = new LinkedList<>();
        try {
            preparedStatement = con.prepareStatement(getProduct);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new Product[0]);
    }

    public Product[] fetchProductList() {
        String getProductList = "SELECT * FROM Product";
        List<Product> list = new LinkedList<>();
        try {
            preparedStatement = con.prepareStatement(getProductList);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new Product[0]);
    }

    public void newRequest(Account acc, Product product, Store sender, int quantity) {
        String insertRequest = "INSERT INTO Request (requestingStore, deliveringStore, " +
                "productId, quantity, status) VALUES (?,?,?,?,?)";
        try {
            preparedStatement = con.prepareStatement(insertRequest);
            preparedStatement.setInt(1, acc.getAccId());
            preparedStatement.setInt(2, sender.getStoreId());
            preparedStatement.setInt(3, product.getId());
            preparedStatement.setInt(4, quantity);
            preparedStatement.setInt(5, Status.pending);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Request[] fetchRequestList(Account acc) {
        String getRequest = "SELECT requestId, productId, requestingStore, quantity" +
                " FROM Request WHERE deliveringStore = ? AND status = ?";
        List<Request> list = new LinkedList<>();

        try {
            preparedStatement = con.prepareStatement(getRequest);
            preparedStatement.setInt(1, acc.getAccId());
            preparedStatement.setInt(2, Status.pending);
            resultSet2 = preparedStatement.executeQuery();
            while (resultSet2.next()) {
                int id = resultSet2.getInt(1);
                int productId = resultSet2.getInt(2);
                int storeId  = resultSet2.getInt(3);
                int quantity = resultSet2.getInt(4);
                Store store = fetchStore(storeId);
                Product product = fetchProduct(productId);
                Request request = new Request(id, store, product, quantity);
                list.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.toArray(new Request[0]);
    }

    public void changeStatus(int requestId, int status) {
        String updateRequest = "UPDATE Request SET status = ? WHERE requestId = ?";
        try {
            preparedStatement = con.prepareStatement(updateRequest);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, requestId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delivery(int requestId) {
        String insertDelivery= "INSERT INTO Delivery (requestId) values (?) ";
        try {
            preparedStatement = con.prepareStatement(insertDelivery);
            preparedStatement.setInt(1,requestId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void acceptRequest(Account acc, Request request) {
        int stock = fetchInventory(acc.getAccId(), request.getProduct());

        if (stock >= request.getQuantity()) {
            stock -= request.getQuantity();
            updateInventory(acc.getAccId(), request.getProduct(), stock);
            receiveProduct(request.getRequestingStore().storeId, request.getProduct(), request.getQuantity());
            changeStatus(request.getRequestId(), Status.accepted);
            delivery(request.getRequestId());
        } else {
            changeStatus(request.getRequestId(), Status.declined);
        }
    }

    public String[][] fetchSentItem(int id) {
        String selectItem = "SELECT orderDate, userName, productName, quantity from Accounts " +
                "INNER JOIN Request R on Accounts.accountId = R.requestingStore " +
                "INNER JOIN Product P on P.productId = R.productId WHERE R.deliveringStore = ? AND R.status = 2";

        List<String[]> list = new LinkedList<>();
        String[][] sList;
        int i = 0;
        try {
            preparedStatement = con.prepareStatement(selectItem);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String[] str = new String[5];
                str[0] = String.valueOf(++i);
                str[1] = resultSet.getString(1);
                str[2] = resultSet.getString(2);
                str[3] = resultSet.getString(3);
                str[4] = resultSet.getString(4);
                list.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        i = 0;
        sList = new String[list.size()][5];
        for (String[] s : list) {
            sList[i] = s;
            i++;
        }
        return sList;
    }

    public String[][] fetchReceivedItem(int id) {
        String selectItem = "SELECT orderDate, userName, productName, quantity from Accounts " +
                "INNER JOIN Request R on Accounts.accountId = R.deliveringStore " +
                "INNER JOIN Product P on P.productId = R.productId " +
                "WHERE R.requestingStore = ? AND R.status = 2";

        List<String[]> list = new LinkedList<>();
        String[][] sList;
        int i = 0;
        try {
            preparedStatement = con.prepareStatement(selectItem);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String[] str = new String[5];
                str[0] = String.valueOf(++i);
                str[1] = resultSet.getString(1);
                str[2] = resultSet.getString(2);
                str[3] = resultSet.getString(3);
                str[4] = resultSet.getString(4);
                list.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        i = 0;
        sList = new String[list.size()][5];
        for (String[] s : list) {
            sList[i] = s;
            i++;
        }
        return sList;
    }

    public String[][] fetchGRN(int id) {
        String selectItem = "SELECT receivedDate, productName, quantity from GRN " +
                "INNER JOIN Product P on GRN.productId = P.productId WHERE storeId = ?";

        List<String[]> list = new LinkedList<>();
        String[][] sList;

        int i = 0;
        try {
            preparedStatement = con.prepareStatement(selectItem);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String[] str = new String[5];
                str[0] = String.valueOf(++i);
                str[1] = resultSet.getString(1);
                str[2] = resultSet.getString(2);
                str[3] = "Other";
                str[4] = resultSet.getString(3);
                list.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        i = 0;
        sList = new String[list.size()][5];
        for (String[] s : list) {
            sList[i++] = s;

        }
        return sList;
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    public void open() {
        try {
            con = dataSource.getConnection();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    public boolean isClosed() {
        try {
            return con.isClosed();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }


}
