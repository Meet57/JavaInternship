package BasicJava;

import java.io.*;
import java.util.HashMap;

class Stack implements java.io.Serializable {
    private int size, temp, arr[], head = 0;

    Stack(int size) {
        this.size = size;
        arr = new int[size];
    }

    public int length() {
        // System.out.println(head);
        return head;
    }

    public int total() {
        temp = 0;
        for (int i = 0; i < head; i++) {
            temp += arr[i];
        }
        return temp;
    }

    public void push(int x) {
        if (head >= size) {
            System.out.println("BasicJava.Stack Overflow !");
        } else {
            arr[head] = x;
            head++;
        }
    }

    public int pop() {
        if (head <= 0) {
            System.out.println("BasicJava.Stack Underflow !");
            return 0;
        } else {
            temp = arr[head - 1];
            head--;
            return temp;
        }
    }

    public int peep() {
        if (head <= 0) {
            System.out.println("BasicJava.Stack Underflow !");
            return 0;
        } else {
            return arr[head - 1];
        }
    }
}

class Bank implements java.io.Serializable{
    private HashMap<String, BankAccount> list = new HashMap<String, BankAccount>();

    public void createAccount(String user, String pass) {
        if (!list.containsKey(user)){
            list.put(user, new BankAccount(user, pass));
        }else{
            System.out.println("Please select any other userID");
        }
    }

    public BankAccount login(String user, String pass) {
        if (list.containsKey(user)) {
            if (list.get(user).login(user, pass)) {
                return list.get(user);
            }
        }
        return null;
    }

    public void accountDetails() {
        System.out.println(list);
    }
}
class BankAccount implements java.io.Serializable{
    private boolean isAuth = false;
    private int amount = 0;
    private HashMap<String, String> User = new HashMap<String, String>();
    private Stack temp, transaction = new Stack(50);

    BankAccount(String user, String pass) {
        // For creating a user im using constructor
        User.put(user, pass);
    }

    public boolean login(String user, String pass) {
        if (User.containsKey(user)) {
            if (User.get(user) == pass) {
                isAuth = true;
            }
        }
        return isAuth;
    }

    public void logout() {
        isAuth = false;
    }

    public int getBalance() {
        amount = transaction.total();
        return amount;
    }

    public void getTransactionDetails() {
        if (isAuth) {
            temp = transaction;
            System.out.println("-- Transaction details --");
            int size = temp.length();
            for (int i = 0; i < size; i++) {
                System.out.println(temp.pop());
            }
            System.out.println("-------------------------");
        }
    }

    public void depositAmount(int x) {
        if (isAuth) {
            transaction.push(x);
        }
    }

    public void withdrawAmount(int x) {
        if (isAuth) {
            if (getBalance() >= x) {
                transaction.push(-x);
            } else {
                System.out.println("No Enough Balance to withdraw amount : " + x);
            }
        }
    }
}

class Main {
    public static void main(String[] args) {

//        BasicJava.Bank abc = new BasicJava.Bank();
//        abc.createAccount("BasicJava.Meet", "1234");
//        abc.createAccount("Dhruv", "1234");

        Bank abc = getObject();
//        abc.accountDetails();

        BankAccount meet = abc.login("BasicJava.Meet", "1234");
        System.out.println(meet.getBalance());

        BankAccount dhruv = abc.login("Dhruv", "1234");
        System.out.println(dhruv.getBalance());

//        saveObject(abc);
    }

    public static void saveObject(Bank a){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("./bank.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(a);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Bank getObject(){
        Bank a = null;
        try {
            FileInputStream fileIn = new FileInputStream("./bank.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            a = (Bank) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("BasicJava.Employee class not found");
            c.printStackTrace();
        }
        return a;
    }
}