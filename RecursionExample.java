public class RecursionExample {
    public static void printer() {
        System.out.println("hello");
        printer();
    }


    public static void main(String[] args)  {
        printer();
    }
}