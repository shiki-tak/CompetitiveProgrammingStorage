# truffle v4.0から導入されたtruffle developでAlisTokenを送金してみた
現在、ICOのスマートコントラクトを勉強しています。挙動を確かめるには公開されているコントラクトを
動かしてみるのが一番ということで、　truffleを使って日本からのICOに成功したALISさんのコントラクトを動かしてみました。

##1. AlisProjectからICO用のリポジトリをクローンする
```
git clone https://github.com/AlisProject/ico-contracts.git
```

##2. package.jsonを修正する
最近、truffle4.0がリリースされたので、今回はそちらを使ってみたいと思います。

package.jsonを開き、truffleのバージョンを修正します。

```
"truffle": "^4.0.1
```

##3. 初期化を行う

```
cd ico-contracts
yarn
yarn truffle install
```

##4. truffle developでtruffle consoleに入る
```
truffle develop
Truffle Develop started at http://localhost:9545/

Accounts:
(0) 0x627306090abab3a6e1400e9345bc60c78a8bef57
(1) 0xf17f52151ebef6c7334fad080c5704d77216b732
(2) 0xc5fdf4076b8f3a5357c5e395ab970b5b54098fef
(3) 0x821aea9a577a9b44299b9c15c88cf3087f3b5544
(4) 0x0d1d4e623d10f9fba5db95830f7d3839406c6af2
(5) 0x2932b7a2355d6fecc4b5c0b6bd44cc31df247a2e
(6) 0x2191ef87e392377ec08e7c08eb105ef5448eced5
(7) 0x0f4f2ac550a1b4e2280d04c21cea7ebd822934b5
(8) 0x6330a553fc93768f612722bb8c2ec78ac90b3bbc
(9) 0x5aeda56215b167893e80b4fe645ba6d5bab767de

Mnemonic: candy maple cake sugar pudding cream honey rich smooth crumble sweet treat

truffle(develop)>
```

入った直後はジェネシスブロックのみ生成されている

```
truffle(develop)> web3.eth.getBlock(0)
{ number: 0,
  hash: '0xfd85abc5c33426eea01981f63e7f9af8f3e57824e49b326c322fa6f94681db4e',
  parentHash: '0x0000000000000000000000000000000000000000000000000000000000000000',
  nonce: '0x0',
  sha3Uncles: '0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347',
  logsBloom: '0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000',
  transactionsRoot: '0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421',
  stateRoot: '0x8c1542b0acbe71d771bed6ea15289effa959cac412f3570d88417d99b4337eb5',
  receiptRoot: '0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421',
  miner: '0x0000000000000000000000000000000000000000',
  difficulty: { [String: '0'] s: 1, e: 0, c: [ 0 ] },
  totalDifficulty: { [String: '0'] s: 1, e: 0, c: [ 0 ] },
  extraData: '0x0',
  size: 1000,
  gasLimit: 6721975,
  gasUsed: 0,
  timestamp: 1512646997,
  transactions: [],
  uncles: [] }
truffle(develop)> web3.eth.getBlock(1)
null
```

##5. デプロイするためにCrowdsale.jsonを修正する
クローンしたままのCrowdsale.jsonではICOの開始が"startBlock": 900000, "icoStartTime": 2145884400で設定されている。
このままではいつまでたってもICOが開始しないので、適当に修正する。
（タイムスタンプは http://url-c.com/tc/ を使って取得すると簡単。）

##6. コントラクトをデプロイする

```
truffle(develop)> migrate
Compiling ./contracts/AlisCrowdsale.sol...
Compiling ./contracts/AlisFund.sol...
Compiling ./contracts/AlisToken.sol...
Compiling ./contracts/Migrations.sol...
Compiling ./contracts/WhitelistedCrowdsale.sol...
Compiling ./contracts/lib/BurnableToken.sol...
Compiling ./contracts/lib/MultiSigWallet.sol...

〜〜（省略）〜〜

Using network 'develop'.

Running migration: 1_initial_migration.js
  Deploying Migrations...
  ... 0x4c3af85df09145685cecac605627132e391d52798ccc6d54b4bee13ddfcc4bfe
  Migrations: 0xf25186b5081ff5ce73482ad761db0eb0d25abfbf
Saving artifacts...
Running migration: 2_deploy_contracts.js
  Deploying AlisFund...
  ... 0x068443e6592b56042538fee913b88d3c2f92a72dbf02dab449ebf9ef5737021d
  AlisFund: 0x8f0483125fcb9aaaefa9209d8e9d7b9c8b9fb90f
  Deploying AlisCrowdsale...
  ... 0xda0d0c1e1065b583d2e720737143853df87a1090b9efe58085c5160e3456adbe
  AlisCrowdsale: 0x9fbda871d559710256a2502a2517b794b482db40
Saving artifacts...
```

##7. トークンを購入してみる
1. トークンを購入するユーザーを変数に入れる。
```
truffle(develop)> bob = web3.eth.accounts[1]
'0xf17f52151ebef6c7334fad080c5704d77216b732'
```

2. migrateでデプロイしたAlisCrowdsaleの内容をcrowdsale変数に割り当てて、クラウドセールのトークンアドレスを取得する。
```
truffle(develop)> bob = web3.eth.accounts[1]
'0xf17f52151ebef6c7334fad080c5704d77216b732'
truffle(develop)> AlisCrowdsale.deployed().then(inst => { crowdsale = inst })
undefined
truffle(develop)> crowdsale.token().then(addr => { tokenAddress = addr } )
undefined
truffle(develop)> tokenAddress
'0xa830ec357b950fde26d220b0f256b6d47529bdbd'
```

3. AlisTokenのインスタンスを作る。以下で使用されているatメソッドに指定したクラウドセールのトークンアドレスからそのトークンインスタンスを取得する。
```
truffle(develop)> AlisTokenInstance = AlisToken.at(tokenAddress)
TruffleContract {
constructor:
{ [Function: TruffleContract]
  _static_methods:
   { setProvider: [Function: setProvider],
     new: [Function: new],
     at: [Function: at],
     deployed: [Function: deployed],

〜〜（省略）〜〜
```

4. AlisTokenを購入する予定のbobのアドレスのAlisTokenを確認する。

```
truffle(develop)> AlisTokenInstance.balanceOf(bob).then(balance => balance.toString(10))
'0'
```

5. AlisTokenを購入する。
トークンセールの開始直後は1 ETH=2900 ALIS なので、5 ETH分のトークンを購入してみる。

```
truffle(develop)> AlisCrowdsale.deployed().then(inst => inst.sendTransaction({ from: bob, value: web3.toWei(5, "ether")}))
{ tx: '0xbf05fed43266d5aa301bcc58e9801df0ef89f1807b0cf3b2ac3321c86fba5542',
  receipt:
   { transactionHash: '0xbf05fed43266d5aa301bcc58e9801df0ef89f1807b0cf3b2ac3321c86fba5542',
     transactionIndex: 0,
     blockHash: '0xb9746b9fab1df6ae50ea129c31f2e21baadd50d2732ee36ca6d7bf653fdf3e11',
     blockNumber: 34,
     gasUsed: 110452,
     cumulativeGasUsed: 110452,
     contractAddress: null,
     logs: [ [Object], [Object] ] },
  logs:
   [ { logIndex: 1,
       transactionIndex: 0,
       transactionHash: '0xbf05fed43266d5aa301bcc58e9801df0ef89f1807b0cf3b2ac3321c86fba5542',
       blockHash: '0xb9746b9fab1df6ae50ea129c31f2e21baadd50d2732ee36ca6d7bf653fdf3e11',
       blockNumber: 34,
       address: '0xd54b47f8e6a1b97f3a84f63c867286272b273b7c',
       type: 'mined',
       event: 'TokenPurchase',
       args: [Object] } ] }
```

6. bobの購入したトークンを確認する。
```
truffle(develop)> AlisTokenInstance.balanceOf(bob).then(balance => account1GusTokenBalance = balance.toString(10))
'14500000000000000000000'
```

コントラクトでuint256 public decimals = 18;と指定していた影響で18個0が後ろにくっついる。実際にいくらトークンを買ったかを見るためにether単位でトークンの数を見てみる。
```
truffle(develop)> web3.fromWei(account1GusTokenBalance, "ether")
'14500'
```

5 EHT × 2900 ALIS分のトークンが購入できていることが確認できる。
