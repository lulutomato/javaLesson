package koreait.solution;

public abstract class Product {
    
    protected int price;
    //1)
    protected String prdName;

    public abstract String sell (Object object);

    @Override
    public String toString() {
        return "PrdName" +prdName +  "price=" + price ; 
    }



    
}

