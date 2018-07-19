public class StringBuilderTest {
    
    static void stringBuilderToUpperCase(StringBuilder sb) {
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            char uc = Character.toUpperCase(c);
            sb.setCharAt(i, uc);
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("引数に文字列を指定してください。");
            System.exit(-1);
        }
        StringBuilder input = new StringBuilder(args[0]);
        stringBuilderToUpperCase(input);
        System.out.println(input);
    }
}