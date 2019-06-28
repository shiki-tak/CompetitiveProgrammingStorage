use std::time::Duration;
use tokio::prelude::*;
use tokio::timer::Interval;
use ethabi::{Event, EventParam, ParamType};
use ethereum_types::Address;
use futures::try_ready;
use futures::{Async, Future, Poll, Stream};
use web3::types::{BlockNumber, FilterBuilder, Log};
use web3::{transports, Web3};

pub struct EventFetcher {
    interval: Interval,
    web3: Web3<transports::Http>,
    address: Address,
    abi: Vec<Event>,
}

impl EventFetcher {
    pub fn new(web3: Web3<transports::Http>, address: Address, abi: Vec<Event>) -> Self {
        EventFetcher {
            interval: Interval::new_interval(Duration::from_secs(1)),
            address,
            abi,
            web3,
        }
    }
}

impl Stream for EventFetcher {
    type Item = Vec<Log>;
    type Error = ();

    fn poll(&mut self) -> Poll<Option<Vec<Log>>, ()> {
        try_ready!(self.interval.poll().map_err(|_| ()));
        let mut all_logs: Vec<web3::types::Log> = vec![];

        for event in self.abi.iter() {
            println!("{:?}", event);
        }
        Ok(Async::Ready(Some(all_logs)))
    }
}

pub struct EventWatcher {
    stream: EventFetcher,
    listeners: Vec<Box<dyn Fn(&Log) -> () + Send>>,
    _eloop: transports::EventLoopHandle,
}

impl EventWatcher {
    pub fn new(url: &str, address: Address, abi: Vec<Event>) -> EventWatcher {
        let (eloop, transport) = web3::transports::Http::new(url).unwrap();
        let web3 = web3::Web3::new(transport);
        let stream = EventFetcher::new(web3, address, abi);

        EventWatcher {
            _eloop: eloop,
            stream,
            listeners: vec![],
        }
    }
}

impl Future for EventWatcher {
    type Item = ();
    type Error = ();

    fn poll(&mut self) -> Poll<Self::Item, Self::Error> {
        loop {
            let logs = match try_ready!(self.stream.poll()) {
                Some(value) => value,
                None => continue,
            };
        }
    }
}

fn main() {
    println!("Sample Watcher started");

    // eventをhandleするcontract address
    let address: Address = match "74Dc8eB318b63a2bA0878FA427e079D040c19bDF".parse() {
        Ok(v) => v,
        Err(e) => panic!(e),
    };

    // handleするeventを登録
    let abi: Vec<Event> = vec![Event {
        name: "Set".to_owned(),
        inputs: vec![EventParam {
                name: "from".to_owned(),
                kind: ParamType::Address,
                indexed: false,
        }, EventParam {
                name: "value".to_owned(),
                kind: ParamType::String,
                indexed: false,
        }],
        anonymous: false,
    }, Event {
        name: "Get".to_owned(),
        inputs: vec![EventParam {
            name: "from".to_owned(),
            kind: ParamType::Address,
            indexed: false,
        }],
        anonymous: false,
    }];

    let mut watcher = EventWatcher::new("http://localhost:8545", address, abi);
    tokio::run(watcher)
}
