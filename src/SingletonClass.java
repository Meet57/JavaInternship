
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
}

// Driver Class
class SingletonClass
{
    public static void main(String args[])
    {
        MySingleton a = MySingleton.getInstance();
        MySingleton b = MySingleton.getInstance();
        a.x = a.x + 10;
        System.out.println("Value of a.x = " + a.x);
        b.x = b.x + 20;
        System.out.println("Value of a.x = " + a.x);
    }
}