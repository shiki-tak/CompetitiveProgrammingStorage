use chrono:: {Utc, DateTime };

use feature::task_controller;

fn main() {
    println!("Hello, Todo");
    let deadline: DateTime<Utc> = Utc::now();

    let task = task_controller::create("".to_string(), "".to_string(), deadline);
    println!("{:?}", task);
}
