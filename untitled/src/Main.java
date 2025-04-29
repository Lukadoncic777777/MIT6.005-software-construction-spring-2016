import java.util.ArrayList;
import java.util.List;

public class Main {
    public enum PenColor {
        BLACK, GRAY, RED, PINK, ORANGE,
        YELLOW, GREEN, CYAN, BLUE, MAGENTA;
    }
    public static void main(String[] args) {

        List<String> a = new ArrayList<>();
        a.add("cat");
        List<String> b = a;
        b.add("dog");
        System.out.println(a);
        System.out.println(b);

    }
}