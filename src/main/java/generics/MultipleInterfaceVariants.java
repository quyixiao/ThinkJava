package generics;//: generics/MultipleInterfaceVariants.java
// {CompileTimeError} (Won't compile)

interface Payable<T> {
}

class Employee1 implements Payable<Employee> {
}

/*class Hourly extends Employee1 implements Payable<Hourly> {
}*/ ///:~
