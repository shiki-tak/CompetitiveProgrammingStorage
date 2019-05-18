struct Circle {
    x: f64,
    y: f64,
    radius: f64,
}

impl Circle {
    fn new(x: f64, y: f64, radius: f64) -> Self {
        Self { x: x, y: y, radius: radius }
    }
    fn area(&self) -> f64 {
        std::f64::consts::PI * (self.radius * self.radius)
    }

    fn grow(&self, increment: f64) -> Circle {
        Circle::new(self.x, self.y, self.radius + increment)
    }

    fn reference(&self) {
        println!("taking self by reference!");
    }

    fn mutable_reference(&self) {
        println!("taking self by mutable reference!");
    }
    fn takes_ownership(&self) {
        println!("taking ownership of self!");
    }
}

struct CircleBuilder {
    x: f64,
    y: f64,
    radius: f64,
}

impl CircleBuilder {
    fn new() -> Self {
        Self { x: 0.0, y: 0.0, radius: 1.0, }
    }

    fn x(&mut self, coordinate: f64) -> &mut CircleBuilder {
        self.x = coordinate;
        self
    }

    fn y(&mut self, coordinate: f64) -> &mut CircleBuilder {
        self.y = coordinate;
        self
    }

    fn radius(&mut self, radius: f64) -> &mut CircleBuilder {
        self.radius = radius;
        self
    }

    fn finalize(&self) -> Circle {
        Circle::new(self.x, self.y, self.radius)
    }
}

fn main() {
    let c = Circle::new(0.0, 0.0, 2.0);
    println!("{}", c.area());

    let d = c.grow(2.0).area();
    println!("{}", d);

    let e = CircleBuilder::new()
                .x(1.0)
                .y(2.0)
                .radius(2.0)
                .finalize();

    println!("area: {}", e.area());
    println!("x: {}", e.x);
    println!("y: {}", e.y);
}
