//Rochana Godigamuwa Program(20221116)
//Start Date 15.12.2023       //End Date 12.01.2024

abstract class Product {
    private String Product_ID;
    private String Product_Name;
    private int No_Available_Items;
    private double Price;

    int Number_Of_Products = 0;

    public Product(String Product_ID,String Product_Name,int No_Available_Items, double Price){
        this.Product_ID = Product_ID;
        this.Product_Name = Product_Name;
        this.No_Available_Items = No_Available_Items;
        this.Price = Price;
        Number_Of_Products += 1;
    }

    //Getters for product
    public String getProduct_ID() {
        return Product_ID;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public int getNo_Available_Items() {
        return No_Available_Items;
    }

    public double getPrice() {
        return Price;
    }

    public int getNumber_Of_Products() {
        return Number_Of_Products;
    }
//Setters for product

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public void setNo_Available_Items(int no_Available_Items) {
        No_Available_Items = no_Available_Items;
    }

    public void setPrice(double price) {
        Price = price;
    }

    @Override
    public String toString(){
        return "Product ID: " + Product_ID +
                "\nProduct Name: " + Product_Name +
                "\nNumber Item: " + No_Available_Items +
                "\nPrice: " + Price;
    }
}

