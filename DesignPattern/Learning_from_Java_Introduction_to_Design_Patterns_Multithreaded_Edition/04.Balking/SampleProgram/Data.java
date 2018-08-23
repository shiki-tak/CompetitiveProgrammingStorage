import java.io.*;

public class Data {
    private final String filename;
    private String content;
    private boolean changed;

    public Data(String filename, String content) {
        this.filename = filename;   // 保存するファイルの名前
        this.content = content;     // データの内容
        this.changed = true;        // 変更した内容が保存されていないならtrue
    }

    // データの内容を書き換える
    public synchronized void change(String newContent) {
        content = newContent;
        changed = true;
    }

    // データの内容が変更されていたらファイルに保存する
    public synchronized void save() throws IOException {
        if (!changed) {
            return;
        }
        doSave();
        changed = false;
    }

    // データの内容を実際にファイルに保存する
    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " calls doSave, content = " + content);
        Writer writer = new FileWriter(filename);
        writer.write(content);
        writer.close();
    }


}