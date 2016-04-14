package main;

public class Employee implements Comparable
{
    private String firstName;
    private String lastName;
    private char gender;
    private int tenure;
    private char rate;
    private double salary;

    @Override
    public int compareTo(Object o)
    {
        return 0;
    }

    public static class Builder
    {
        private final String firstName;
        private final String lastName;

        private char gender = '0';
        private int tenure = 0;
        private char rate = '0';
        private double salary = 0.0;

        public Builder(String firstName, String lastName)
        {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Builder gender(char gender)
        {
            this.gender = gender;
            return this;
        }
        public Builder tenure(int tenure)
        {
            this.tenure = tenure;
            return this;
        }
        public Builder rate(char rate)
        {
            this.rate = rate;
            return this;
        }
        public Builder salary(double salary)
        {
            this.salary = salary;
            return this;
        }

        public Employee build()
        {
            return new Employee(this);
        }
    }

    public Employee(Builder builder)
    {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
        this.tenure = builder.tenure;
        this.rate = builder.rate;
        this.salary = builder.salary;
    }

    public Employee compareTo(T)
    {

    }

    public void dump()
    {
        System.out.println("First name: " + this.firstName);
        System.out.println("Last name: " + this.lastName);
        System.out.println("Gender: " + this.gender);
        System.out.println("Tenure: " + this.tenure);
        System.out.println("Rate: " + this.rate);
        System.out.println("Salary: " + this.salary);
    }
}
