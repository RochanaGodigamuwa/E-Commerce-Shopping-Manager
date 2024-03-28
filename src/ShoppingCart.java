import java.util.ArrayList;
import java.util.List;

class ShoppingCart {
    private List<Product> productsList;

    public ShoppingCart() {                            //Rochana Godigamuwa Program(20221116)
                                                       //Start Date 15.12.2023       //End Date 12.01.2024
        this.productsList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productsList.add(product);
        System.out.println("Product added to the cart.");
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public double calculateTotalCost() {
        double totalCost = 0;
        for (Product product : productsList) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }



}
