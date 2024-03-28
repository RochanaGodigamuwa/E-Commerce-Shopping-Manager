//Rochana Godigamuwa Program(20221116)
//Start Date 15.12.2023       //End Date 12.01.2024
class Electronics extends Product {
    private String Brand_Name;
    private int Warranty_Period;

    Electronics(String Product_ID, String Product_Name,int No_Available_Items, double Price,String Brand_Name, int Warranty_Period){
        super(Product_ID,Product_Name,No_Available_Items,Price);
        this.Brand_Name = Brand_Name;
        this.Warranty_Period = Warranty_Period;
    }



    //Getters for Electronics

    public String getBrand_Name() {
        return Brand_Name;
    }

    public int getWarranty_Period() {
        return Warranty_Period;
    }




    //Setters for Electronics
    public void setBrand_Name(String brand_Name) {
        Brand_Name = brand_Name;
    }

    public void setWarranty_Period(int warranty_Period) {
        Warranty_Period = warranty_Period;
    }
    public void displayDetails(){
        System.out.println("ProductID: " + getProduct_ID()+ "\nCategory: Electronics" +"\nName: "+
                getProduct_Name()+ "\nPrice:"+getPrice()+ "\nBrand:" +getBrand_Name()+ "\nWaranty Period: "+getWarranty_Period()+" weeks "+"\nAvailabale products: "+getNo_Available_Items());
    }

    public String saveElectronic(){
        return("Electronic" + "-" + getProduct_ID() + "-" +  getProduct_Name() + "-" + getNo_Available_Items() + "-" + getPrice() + "-" + getBrand_Name() + "-" + getWarranty_Period());
    }
}