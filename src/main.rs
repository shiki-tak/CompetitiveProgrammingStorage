use chrono::Local;

use feature::task_controller;

fn main() {
    println!("Hello, Todo");
    // TODO: generate id
    // let task_json = task_controller::create(1, "test_title-1".to_string(), "test_content_1".to_string(), Local::now().timestamp());    // set
    // println!("{:?}", task_json);

    // let task = task_controller::get(0);
    // println!("{:?}", task);

    // task_controller::delete(1);

    let tasks = task_controller::gets();
    println!("{:?}", tasks);


}
