package AdvanceJava.Shopping;

import java.util.ArrayList;

public class User {
    private String user,password;
    private ArrayList<Item> Cart;

    public User(){
        Cart = new ArrayList<Item>();
    }

    public User(String user, String password) {
        this.user = user;
        this.password = password;
        Cart = new ArrayList<Item>();
    }

    public String getUser() {
        return user;
    }

    private String getPassword() {
        return password;
    }

    public Boolean login(String user,String password){
        return this.user.equals(user) && this.password.equals(password);
    }

    public void addToCart(String name, int price, int quantity){
        Cart.add(new Item(name,price,quantity));
    }

    public void removeFromCart(int index){
        if(Cart.size()>index){
            Cart.remove(index);
        }else {
            System.err.println("Item Doesn't Exist");
        }
    }

    public void viewCart(){
        if(Cart.size()==0){
            System.out.println("No Items in cart");
            return;
        }
        System.out.println("------------------------");
        System.out.println("Product\tPrice\tQuantity");
        for (int i = 0; i < Cart.size(); i++) {
            System.out.println((i+1) + " " +Cart.get(i));
        }
        System.out.println("------------------------");
    }
}
