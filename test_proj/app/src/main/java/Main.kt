// function
fun testFunction(name: String){
//    fun testFunction(name: String): Int{
//    fun testFunction(name: String): String{
    println(name)

//        return 12
//        return "12"
}

fun loopFunction(){
    val num: Array<Int> = arrayOf(1, 2, 3, 4, 5)
    num[1] = 100

    for(v in num){ println(v) }

    for(i in 1..3){ println(i) }

    for(i in 1..10 step 2){ println(i) }

    for(i in 10 downTo 0 step 2){ println(i) }
}

class Person(var name: String){

}

class Person2{
    constructor(name2: String){ println(name2) }
}

open class Person3(val name3: String){
    var a = 1
    private val b = 2
    protected val c = 3
    internal val d = 4

    inner class Age{
        fun setAge(age: Int){ a = age }
    }

    init { println(name3) }
}

class Scientist(major: String): Person3(major){
}

data class Person4(val name4: String, val age: Int)

fun main() {
    testFunction("test")
    loopFunction()

    val person: Person = Person(name = "tester")
    val person2: Person2 = Person2(name2 = "tester2")
    val person3: Person3 = Person3(name3 = "tester3")
    val person4: Person4 = Person4(name4 = "tester4", age = 28)
    val (name, age) = Person4("tester4", 28)
    val scientist: Scientist = Scientist(major = "machine learning")

    println(person.name)

//    println(person2.name2)

    println(person3.name3)
    println(person3.a)
    person3.Age().setAge(18);
    println(person3.a)
    println(person3.d)

    println(person4.name4)
    println(person4.age)
    println(name)
    println(age)

    println(scientist.name3)
}