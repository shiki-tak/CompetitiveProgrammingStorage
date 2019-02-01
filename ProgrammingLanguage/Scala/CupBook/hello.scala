import scala.collection.mutable
// val thrill = "Will" :: "fill" :: "until" :: Nil
// println(thrill(2))
// println(thrill.count(s => s.length == 4))
// println(thrill.drop(2))
// println(thrill.dropRight(2))
// println(thrill.exists(s => s == "until"))
// println(thrill.filter(s => s.length == 4))
// println(thrill.forall(s => s.endsWith("l")))

// var jetSet = Set("Boeing", "Airbus")
// jetSet += "Lear"
// println(jetSet.contains("Cessna"))

val treasureMap = mutable.Map[Int, String]()
treasureMap += (1 -> "Go to island.")
treasureMap += (2 -> "Find big X on ground.")
treasureMap += (3 -> "Dig.")
println(treasureMap(2))

val japanMap = mutable.Map[String, String]()
japanMap += ("Shimane" -> "Matuse")
japanMap += ("Ehime" -> "Matsuyama")
japanMap += ("Kyoto" -> "kyoto")

japanMap.foreach {
    case(k, v) => println("key: " + k + "value: " + v)
}

def printArgs(args: Array[String]): Unit = {
    for (arg <- args) {
        println(arg)
    }
}

val Programming = Array("Java", "Scala", "Rust", "Haskell")
printArgs(Programming)
