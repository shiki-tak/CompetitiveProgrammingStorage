use chrono::Local;

use feature::task_controller;

fn main() {
    println!("Hello, Todo");
    let deadline = Local::now().timestamp();

    // TODO: generate id
    let task_json = task_controller::create(0, "".to_string(), "".to_string(), deadline);
    println!("{:?}", task_json);
}
