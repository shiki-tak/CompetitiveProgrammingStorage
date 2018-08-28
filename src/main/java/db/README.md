## DBはHBaseを使用予定

### Setting
```
$ brew install hbase							// homebrewでHbaseをインストール
$ export HBASE_HOME=/usr/local/hbase-2.0.0		// パスを設定
$ export PATH=$HBASE_HOME/bin:$PATH
$ cd $HBASE_HOME/bin
$ ./start-hbase.sh								// HBaseの起動
$ hbase shell									// JRuby ベースの対話型シェルを起動
```