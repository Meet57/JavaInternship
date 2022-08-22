package AdvanceJava.Shopping;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

class ApplicationException extends Exception {
    ApplicationException(String str) {
        super(str);
    }
}

public class Application {
    private HashMap<String, User> application;

    Application() {
        try {
            FileInputStream fileIn = new FileInputStream("./product.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            application = (HashMap) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception i) {
            application = new HashMap<>();
        }
        application = new HashMap<>();
    }

    public Boolean createAccount(String user, String password) throws ApplicationException {
        if (!application.containsKey(user)) {
            application.put(user, new User(user, password));
            return true;
        }
        throw new ApplicationException("User Name Already Exists");
    }

    public User login(String user, String password) throws ApplicationException {
        if (application.containsKey(user)) {
            if (application.get(user).login(user, password)) {
                return application.get(user);
            }
            throw new ApplicationException("Incorrect Password");
        }
        throw new ApplicationException("User Doesn't Exists");
    }

    public void backUp() {
        try {
            FileOutputStream fileOut = new FileOutputStream("./application.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(application);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void MenuToAddItems(Product p) throws ProductException {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        String[] category;
        while (choice != 4) {
            System.out.print("1. Get Category\n2. See products\n3. Add Item\n4. Exit From Vendor\nSelect: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    category = p.getCategories();
                    if (category.length > 0) {
                        for (int i = 0; i < category.length; i++) {
                            System.out.println(i + ". " + category[i]);
                        }
                    } else {
                        System.out.println("No Products");
                    }
                    sc.nextLine();
                    break;
                case 2:
                    category = p.getCategories();
                    if (category.length > 0) {
                        System.out.println("For which Category you want to see Products ?");
                        for (int i = 0; i < category.length; i++) {
                            System.out.println(i + ". " + category[i]);
                        }
                        System.out.print("Select: ");
                        choice = sc.nextInt();
                        if (choice > category.length - 1) {
                            System.err.println("Wrong Option");
                            break;
                        }
                        String[] Products = p.getCategoryProducts(category[choice]);
                        for (int i = 0; i < Products.length; i++) {
                            System.out.println(Products[i]);
                        }
                        System.out.println();
                    } else {
                        System.out.println("No Products");
                    }
                    sc.nextLine();
                    break;
                case 3:
                    System.out.print("Category Name: ");
                    sc.nextLine();
                    String cat = sc.nextLine();
                    System.out.print("Product Name: ");
                    String name = sc.nextLine();
                    System.out.print(name + " price: ");
                    int price = sc.nextInt();
                    p.addProduct(cat, name, price);
                    sc.nextLine();
                    break;
                default:
                    break;
            }
        }
    }

    public static void UserInteraction(User user, Product p) throws ProductException {
        Scanner sc = new Scanner(System.in);
        int choice = 0, quantity, flag;
        String temp, name;
        String[] category;
        while (choice != 4) {
            System.out.print("1. Show Cart\n2. Add Item\n3. Remove Item\n4. Exit From User\nSelect: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    user.viewCart();
                    break;
                case 2:
                    category = p.getCategories();
                    if (category.length > 0) {
                        System.out.println("From which Category you want to add Products ?");
                        for (int i = 0; i < category.length; i++) {
                            System.out.println(i + ". " + category[i]);
                        }
                        System.out.print("Select: ");
                        choice = sc.nextInt();
                        if (choice > category.length - 1) {
                            System.err.println("Wrong Option");
                            break;
                        }
                        System.out.println("Products: ");
                        String[] Products = p.getCategoryProducts(category[choice]);
                        for (int i = 0; i < Products.length; i++) {
                            System.out.println(Products[i]);
                        }
                        System.out.print("Select the product: ");
                        flag = sc.nextInt();
                        Item x = p.getProduct(category[choice], flag);
                        System.out.print("Quantity : ");
                        flag = sc.nextInt();
                        user.addToCart(x.getName(), x.getPrice(), flag);
                        sc.nextLine();
                    } else {
                        System.out.println("No Products");
                    }
                    break;
                case 3:
                    user.viewCart();
                    System.out.println("Which product you want to remove :");
                    flag = sc.nextInt();
                    user.removeFromCart(flag - 1);
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) throws ApplicationException, ProductException {
        Application app = new Application();
        Product p = new Product();

//        Dummy Data
        p.addProduct("Dairy", "Milk", 60);
        p.addProduct("Dairy", "Paneer", 60);
        p.addProduct("Dairy", "Cheese", 60);
        p.addProduct("Dairy", "Chocolate", 60);
        p.addProduct("Cloths", "Pant", 60);
        p.addProduct("Cloths", "Shirt", 60);
        p.addProduct("Cloths", "Belt", 60);

        app.createAccount("meet", "meet");
        app.login("meet","meet").addToCart("Milk",80,5);
        app.login("meet","meet").addToCart("Jeans",800,2);


        Scanner sc = new Scanner(System.in);
        String user, pass;
        int choice = 0;
        while (choice != 4) {
            System.out.print("1. Login\n2. Create Account\n3. Add Items\n4. Exit\nSelect: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Username: ");
                    user = sc.nextLine();
                    System.out.print("Password: ");
                    pass = sc.nextLine();
                    try {
                        User temp = app.login(user, pass);
                        UserInteraction(temp, p);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    break;
                case 2:
                    sc.nextLine();
                    System.out.print("Username: ");
                    user = sc.nextLine();
                    System.out.print("Password: ");
                    pass = sc.nextLine();
                    try {
                        app.createAccount(user, pass);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    break;
                case 3:
                    MenuToAddItems(p);
                    break;
                default:
                    break;
            }
        }
    }
}
