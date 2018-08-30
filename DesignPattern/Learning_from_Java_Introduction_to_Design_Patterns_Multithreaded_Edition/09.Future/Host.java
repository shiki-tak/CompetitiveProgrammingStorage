import java.util.concurrent.Callable;

public class Host {
    public Data request(final int count, final char c) {
        System.out.println("    request(" + count + ", " + c + ") BEGIN");

        // FutureDataのインスタンスを作る
        final FutureData future = new FutureData(
        		new Callable<RealData>() {
        			public RealData call() {
        				return new RealData(count, c);
        			}
        		}
        	);

        // RealDataのインスタンスを作るための新しいスレッドを起動する
        new Thread(future).start();

        System.out.println("    request(" + count + ", " + c + ") END");

        // FutureDataのインスタンスを戻り値とする
        return future;
    }
}