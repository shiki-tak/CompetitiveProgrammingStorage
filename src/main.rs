use chrono::Local;

use feature::task_controller;

fn main() {
    println!("Hello, Todo");
    let deadline = Local::now().timestamp();

    // TODO: generate id
    // let task_json = task_controller::create(0, "".to_string(), "".to_string(), deadline);    // set
    // println!("{:?}", task_json);
    let task = task_controller::get(0);
    println!("{:?}", task);
}
