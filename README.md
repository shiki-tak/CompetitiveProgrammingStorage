## Implementation of Ethereum like blockchain using Java.

- Consensus(PoW)
- Account
- Merkle Tree
- Merkle Patricia Tree
- Bloom Filter
- Transfer
- Capitals-based checksum
- JSON RPC

## Goal

- Implemention of Ethereum like Blokchain
- Save data to HBase
- Implemention JSON RPC
- Mining
- Plasma MVP

##  HBase SetUp

- Install
```
$ cd /usr/local/lib/
$ curl -LO http://ftp.riken.jp/net/apache/hbase/1.2.6.1/hbase-1.2.6.1-bin.tar.gz
$ tar xzvf hbase-1.2.6.1-bin.tar.gz
$ ln -s hbase-1.2.6.1/ hbase
```

- Setting
```
$ export HBASE_HOME=/usr/local/lib/hbase-1.2.6.1
$ export PATH=$HBASE_HOME/bin:$PATH
$ hbase version
HBase 1.2.6.1
Source code repository file:///home/busbey/projects/hbase/hbase-release-staging/hbase-1.2.6.1 revision=Unknown
Compiled by busbey on Sun Jun  3 23:19:26 CDT 2018
From source with checksum 8bbad3724e1501dbe32107e20b780499
```

- Start
```
$ cd $HBASE_HOME/bin
$ start-hbase.sh                          // HBaseの起動
$ hbase shell                             // JRuby ベースの対話型シェルを起動
```

## API
#### Create Account
```
curl -X  POST -H "Content-Type:application/json" -d '{"id":"1","jsonrpc":"2.0","method":"newAccount","params":["initBalance"]}' http://localhost:8080/account
```

#### Get Balance
```
curl -X  POST -H "Content-Type:application/json" -d '{"id":"1","jsonrpc":"2.0","method":"getBalance","params":["address"]}' http://localhost:8080/account
```

#### Send Transaction
```
curl -X  POST -H "Content-Type:application/json" -d '{"id":"1","jsonrpc":"2.0","method":"sendTransaction","params":["to", "from", "value", "gasLimit", "gasPrice"]}' http://localhost:8080/transaction
```

#### Get Transaction By Hash
```
curl -X  POST -H "Content-Type:application/json" -d '{"id":"1","jsonrpc":"2.0","method":"getTransactionByHash","params":["transactionHash"]}' http://localhost:8080/transaction
```

#### Get Block By Height
```
curl -X  POST -H "Content-Type:application/json" -d '{"id":"1","jsonrpc":"2.0","method":"getBlockByHeight","params":["height"]}' http://localhost:8080/blockchain
```
#### Get Block By Hash
```
curl -X  POST -H "Content-Type:application/json" -d '{"id":"1","jsonrpc":"2.0","method":"getBlockByHash","params":["blockHash"]}' http://localhost:8080/blockchain
```

#### Create Genesis Block
```
curl -X  POST -H "Content-Type:application/json" -d '{"id":"1","jsonrpc":"2.0","method":"createGenesisBlock","params":[]}' http://localhost:8080/blockchain
```

## Reference
#### Account
- [Ruby で Ethereum のアドレスを生成する](http://diary.piyopiyo.jp/entry/ruby_ethereum_address_generator)
- [Ethereumのアドレス生成アルゴリズム](https://qiita.com/ippo012/items/c64a2c4d873c0faf187c)

#### Core
- [EthereumのTransactionHashの計算方法](https://y-nakajo.hatenablog.com/entry/2018/03/08/001041)
- [EIP-155](https://y-nakajo.hatenablog.com/entry/2018/03/08/001041)

#### Trie
- [ブルームフィルタ](https://ja.wikipedia.org/wiki/%E3%83%96%E3%83%AB%E3%83%BC%E3%83%A0%E3%83%95%E3%82%A3%E3%83%AB%E3%82%BF)
- [ビットコインにおけるブルームフィルターとマークルツリーパス](http://pebble8888.hatenablog.com/entry/2018/02/12/172819)
- [作って学ぶBitcoin！ゼロから作るSPVウォレット](https://qiita.com/lotz/items/1aa6cf18aa193f40c647)

#### RLP
- [RLP](https://github.com/ethereum/wiki/wiki/%5BJapanese%5D-RLP)