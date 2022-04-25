package main.store;

public class Product {
    protected int id;
    protected String name;
    protected String category;

    public Product(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Product(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Inventory {

    String product;
    int quantity;

    public Inventory(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }



    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}

class Store {
    int storeId;
    String Name;

    public Store(int storeId, String name) {
        this.storeId = storeId;
        Name = name;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return  Name;
    }
}