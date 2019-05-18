use chrono:: {Utc, DateTime };
use model::task::Task;


fn main() {
    println!("Hello, Todo");
    let deadline: DateTime<Utc> = Utc::now();
    let task = Task::new("".to_string(), "".to_string(), deadline);
    println!("{:?}", task);
}
