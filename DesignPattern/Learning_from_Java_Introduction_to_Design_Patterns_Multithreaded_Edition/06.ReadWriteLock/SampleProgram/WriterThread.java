import java.util.Random;

public class WriterThread extends Thread {
    private static final Random random = new Random();
    private final Data data;
    private final String fillter;
    private int index = 0;

    public WriterThread(Data data, String fillter) {
        this.data = data;
        this.fillter = fillter;
    }

    public void run() {
        try {
            while (true) {
                char c = nextchar();
                data.write(c);
                Thread.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {

        }
    }

    private char nextchar() {
        char c = fillter.charAt(index);
        index++;
        if (index >= fillter.length()) {
            index = 0;
        }
        return c;
    }
}