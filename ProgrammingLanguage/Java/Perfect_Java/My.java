public class My extends Base {
    private final String s = "456";

    @Override
    void exec() {
        System.out.println("my:exec " + s);
    }

    public static void main(String[] args) {
        Base base = new Base();
        base.exec();

        My my1 = new My();
        my1.exec();

        Base my2 = new My();
        my2.exec();
    }
}