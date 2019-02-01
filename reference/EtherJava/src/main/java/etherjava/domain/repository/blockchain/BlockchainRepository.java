package etherjava.domain.repository.blockchain;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import etherjava.domain.model.blockchain.Block;

@Repository
public class BlockchainRepository {
	static Configuration conf;
	private final Connection connection;
	private final HTable hTableBlockchain;

	public BlockchainRepository() throws IOException {
		// instantiate Configuration class
		conf = new Configuration();
		conf = HBaseConfiguration.create(conf);
		conf.set(HConstants.ZOOKEEPER_QUORUM, "localhost");
		// Connectionクラスの初期化
		connection = ConnectionFactory.createConnection(conf);
	   // instantiate HTable class
	   hTableBlockchain = (HTable) connection.getTable(TableName.valueOf("etherjava:blockchainTest"));	// test用のテーブル
	}

	public void save(Block block) throws IOException {
		Put put = new Put(Bytes.toBytes(String.valueOf(block.getHeight())));

		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("height"), Bytes.toBytes(String.valueOf(block.getHeight())));
		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("blockSize"), Bytes.toBytes(String.valueOf(block.getBlockSize())));
		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("parentHash"), Bytes.toBytes(String.valueOf(block.getBlockHeader().getParentHash())));
		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("blockHash"), Bytes.toBytes(String.valueOf(block.getBlockHeader().getBlockHash())));
		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("merkleRoot"), Bytes.toBytes(String.valueOf(block.getBlockHeader().getMerkleRoot())));
		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("logsBloom"), Bytes.toBytes(String.valueOf(block.getBlockHeader().getLogsBloom())));
		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("nonce"), Bytes.toBytes(String.valueOf(block.getBlockHeader().getNonce())));
		put.addColumn(Bytes.toBytes("fam"), Bytes.toBytes("timeStamp"), Bytes.toBytes(String.valueOf(block.getBlockHeader().getTimeStamp())));

		hTableBlockchain.put(put);
	}

	public Block findOne(int index) throws IOException {
		try {
			Result getResult = hTableBlockchain.get(new Get(Bytes.toBytes(String.valueOf(index))));

			String height = Bytes.toString(getResult.getValue(Bytes.toBytes("fam"), Bytes.toBytes("height")));
			String blockSize = Bytes.toString(getResult.getValue(Bytes.toBytes("fam"), Bytes.toBytes("blockSize")));
			String parentHash = Bytes.toString(getResult.getValue(Bytes.toBytes("fam"), Bytes.toBytes("parentHash")));
			String blockHash = Bytes.toString(getResult.getValue(Bytes.toBytes("fam"), Bytes.toBytes("blockHash")));
			String merkleRoot = Bytes.toString(getResult.getValue(Bytes.toBytes("fam"), Bytes.toBytes("merkleRoot")));
			String logsBloomToString = Bytes.toString(getResult.getValue(Bytes.toBytes("fam"), Bytes.toBytes("logsBloom")));
			String nonce = Bytes.toString(getResult.getValue(Bytes.toBytes("fam"), Bytes.toBytes("nonce")));
			String timeStamp = Bytes.toString(getResult.getValue(Bytes.toBytes("fam"), Bytes.toBytes("timeStamp")));

			Block block = new Block(
					Long.parseLong(blockSize),
					Integer.parseInt(height),
					parentHash,
					blockHash,
					merkleRoot,
					logsBloomToString,
					Integer.parseInt(nonce),
					Long.parseLong(timeStamp)
					);

			return block;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
