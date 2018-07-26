import java.util.*;

public class MyList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("one");
        list.add("two");
        list.add("three");

        for (String s: list) {
            System.out.println(s);
        }
    }
}