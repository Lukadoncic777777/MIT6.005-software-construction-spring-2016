public class Main {
    public enum PenColor {
        BLACK, GRAY, RED, PINK, ORANGE,
        YELLOW, GREEN, CYAN, BLUE, MAGENTA;
    }
    public static void main(String[] args) {

        System.out.println("Hello world!");
        int[] a=new int[100];
        a[1]= PenColor.BLUE.ordinal();
        System.out.println(a[1]);
    }
}