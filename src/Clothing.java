class Clothing extends Product {                            //Rochana Godigamuwa Program(20221116)
                                                            //Start Date 15.12.2023       //End Date 12.01.2024
    private double Size;
    private String Color;

    Clothing(String Product_ID,String Product_Name,int No_Available_Items, double Price, double Size, String Color){
        super(Product_ID,Product_Name,No_Available_Items,Price);
        this.Size = Size;
        this.Color = Color;
    }
    //Getters for Cloths


    public double getSize() {
        return Size;
    }

    public String getColor() {
        return Color;
    }

    //Setters for Clothing

    public void setSize(double size) {
        Size = size;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String saveClothing(){
        return("Clothing" + "-" + getProduct_ID() + "-" +  getProduct_Name() + "-" + getNo_Available_Items() + "-" + getPrice() + "-" + getSize() + "-" + getColor());
    }

    public void displayDetails(){
        System.out.println("ProductID: " + getProduct_ID()+ "\nName: "+
                getProduct_Name()+"\nBrand:" +getSize()+ "\nPrice:"+getPrice()+ "\nColour: "+getColor()+" weeks "+"\nAvailabale products: "+getNo_Available_Items());
    }
}