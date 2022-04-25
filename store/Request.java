package main.store;

public class Request {
    private int requestId;
    private Store requestingStore;
    private Product product;
    private int quantity;

//    Request(Request request){
//        requestId = request.getRequestId();
//        requestingStore = request.getRequestingStore();
//        product = request.getProduct();
//        quantity = request.getQuantity();
//    }

    public Request(int requestId, Store requestingStore, Product product, int quantity) {
        this.requestId = requestId;
        this.requestingStore = requestingStore;
        this.product = product;
        this.quantity = quantity;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Store getRequestingStore() {
        return requestingStore;
    }

    public void setRequestingStore(Store requestingStore) {
        this.requestingStore = requestingStore;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    public String[] getInfo() {
//        String[] info = new String[4];
//        info[1] = requestingStore.Name;
//        info[2] = product.name;
//        info[3] = String.valueOf(quantity);
//        return info;
//    }

    @Override
    public String toString() {
        return requestingStore + "\t\t" + product + "\t\t" + quantity ;
    }

    public static String[][] toTable(Request[] rList) {
        String[][] info = new String[rList.length][4];
        int i = 0;
        int j;
        for (Request r : rList) {
            j = 0;
            info[i][j++] = String.valueOf(i + 1);
            info[i][j++] = r.getProduct().name;
            info[i][j++] = r.getRequestingStore().Name;
            info[i][j] = String.format("%d pcs", r.getQuantity());
            i++;
        }
        return info;
    }
}
class Status{
    static final int pending = 1;
    static final int declined = 0;
    static final int accepted = 2;
    static final int cancelled = 3;
}