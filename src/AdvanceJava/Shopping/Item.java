package AdvanceJava.Shopping;

public class Item implements Comparable<Item>,java.io.Serializable {
    private int price,quantity;
    private String name;

    public Item(String name,int price) {
        this.price = price;
        this.name = name;
    }

    public Item(String name,int price,int quantity) {
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        if(getQuantity()==0)
            return getName()+"\t"+getPrice();
        return getName()+"\t"+getPrice()+"\t\t"+getQuantity();
    }

    @Override
    public int compareTo(Item item) {
        return Integer.compare(getPrice(),item.getPrice());
    }
}
