DROP TABLE IF EXISTS accouns CASCADE;
DROP TABLE IF EXISTS transactions CASCADE;

CREATE TABLE IF NOT EXISTS accounts(
	address varchar(255) NOT NULL,
	balance double precision NOT NULL,
	nonce int NOT NULL,
	PRIMARY KEY(address)
);

CREATE TABLE IF NOT EXISTS transactions(
	tx_hash varchar(255) NOT NULL,
	to_address varchar(255) NOT NULL,
	from_address varchar(255) NOT NULL,
	value double precision NOT NULL,
	"data" varchar(255),
	gas_limit double precision NOT NULL,
	gas_price double precision NOT NULL,
	nonce int NOT NULL,
	PRIMARY KEY(tx_hash)
);