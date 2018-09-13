package etherjava.rlp;

// RLP Encodeing Test
public class Main {
    public static void main(String[] args) {
        RlpString rlpStringCat = new RlpString("cat".getBytes());
        RlpString rlpStringDog = new RlpString("dog".getBytes());
        RlpList rlpList = new RlpList(rlpStringCat, rlpStringDog);

        // 文字列のRLPエンコーディング
        byte[] resRlpStringEncoding = RlpEncoder.encode(rlpStringDog);

        // リストのRLPエンコーディング
        byte[] resRlpListEncoding = RlpEncoder.encode(rlpList);

        // Listのエンコーディングをデコーディング
        RlpList listDecode = RlpDecoder.decode(resRlpListEncoding);

        System.out.println(resRlpStringEncoding);
        System.out.println(listDecode);

        // Listのエンコーディング結果
        for (int i = 0; i < resRlpListEncoding.length; i++) {
            System.out.println(resRlpListEncoding[i]);
        }
    }
}