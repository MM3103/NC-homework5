package hw5.model;

public class Employee {
    private String firstName;
    private String secondName;
    private String lastName;
    private int age;
    private int salary;
    private String email;
    private String job;

    public Employee() {

    }

    public Employee(String firstName, String secondName, String lastName, int age, int salary, String email, String job) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
        this.email = email;
        this.job = job;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String middleName) {
        this.secondName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return firstName + "$" +
                secondName + "$" +
                lastName + "$" +
                age + "$" +
                salary + "$" +
                email + "$" +
                job;
    }
}
