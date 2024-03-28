

interface ShoppingManager {
    default void addProduct(Product product) {
    }

    void deleteProduct(String productID);

    //List<Product> getProductList();

    void saveProductListToFile();

    void loadProductListFromFile();

}

