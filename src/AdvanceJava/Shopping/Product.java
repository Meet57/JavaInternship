package AdvanceJava.Shopping;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
* The reason why I used linkedhashmap is when a person start adding category
* it should not happen that he tries to maintain a sequence and endup getting a hashup which
* is messed up
*
* Using ArrayList instead of Linkedlist because there will be
* more Read operations than update adn delete
* */
class ProductException extends Exception{
    ProductException(String str){
        super(str);
    }
}

public class Product {
    private LinkedHashMap<String, ArrayList<Item>> product = null;

    Product() {
        try {
            FileInputStream fileIn = new FileInputStream("./product.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            product = (LinkedHashMap) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception i) {
            product = new LinkedHashMap<String, ArrayList<Item>>();
        }

    }

    public void backUp(){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("./product.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(product);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void addCategory(String categoryName) {
        if (!product.containsKey(categoryName))
            product.put(categoryName, new ArrayList<Item>());
    }

    public void addProduct(String categoryName, String productName, int price) {
        if (product.containsKey(categoryName)) {
            product.get(categoryName).add(new Item(productName, price));
        } else {
            addCategory(categoryName);
            product.get(categoryName).add(new Item(productName, price));
        }
    }

    public String[] getCategories() {
        return product.keySet().toArray(new String[0]);
    }

    public String[] getCategoryProducts(String categoryName) throws ProductException {
        if (product.containsKey(categoryName)) {
            ArrayList temp = product.get(categoryName);
            StringBuilder st = new StringBuilder();
            String[] strings = new String[temp.size()];
            for (int i = 0; i < temp.size(); i++) {
                st.append(i).append(". ").append(temp.get(i));
                strings[i] = String.valueOf(st);
                st.setLength(0);
            }
            return strings;
        }else{
            throw new ProductException("Category Doesn't Exists");
        }
    }

    private Boolean containsProduct(String categoryName, int productIndex) throws ProductException {
        if(product.containsKey(categoryName)){
            if(product.get(categoryName).size()>productIndex){
                return true;
            }
            return false;
        }else{
            throw new ProductException("Category Doesn't Exists");
        }
    }

    public Item getProduct(String categoryName, int productIndex) throws ProductException {
        if(containsProduct(categoryName,productIndex)){
            return product.get(categoryName).get(productIndex);
        }else{
            throw new ProductException("Product Doesn't Exists");
        }
    }

//    public static void main(String[] args) throws ProductException {
//        Product p = new Product();
////        p.addProduct("Cloths","Shirt",500);
////        p.addProduct("Cloths","Pants",800);
////        p.addProduct("Cloths","Jeans",1800);
////        p.addProduct("Cloths","Shorts",200);
////        p.addProduct("Dairy","Paneer",80);
////        p.addProduct("Dairy","Cheese",100);
////        p.addProduct("Dairy","Milk",50);
//
//        String[] cats = p.getCategories();
//        for (int i = 0; i < cats.length; i++) {
//            System.out.print(cats[i]+" ");
//        }
//        System.out.println();
//
//        String[] products = p.getCategoryProducts("Dairy");
//        for (int i = 0; i < products.length; i++) {
//            System.out.println(products[i]);
//        }
//
//        System.out.println(p.getProduct(cats[1],1));
//
//        p.backUp();
//    }
}
