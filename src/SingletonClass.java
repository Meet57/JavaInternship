
class MySingleton
{
    static MySingleton instance = null;
    public int x = 10;
    private MySingleton() { } //This prevents to make objects
    static public MySingleton getInstance()
    {
        if (instance == null)
            instance = new MySingleton();

        return instance;
    }

    @Override
    public String toString() {
        return String.valueOf(x);
    }
}

// Driver Class
class SingletonClass
{
    public static void main(String args[])
    {
        MySingleton a = MySingleton.getInstance();
        MySingleton b = MySingleton.getInstance();
        a.x = a.x + 10;
        System.out.println("Value of a.x = " + a);
        b.x = b.x + 20;
        System.out.println("Value of a.x = " + a);
    }
}