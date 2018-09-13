## DBはHBaseを使用予定

### SetUp

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
