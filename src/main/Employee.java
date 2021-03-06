package main;

import utils.SuperOutput;

public class Employee implements Comparable
{
    /**
     *
     * @param o  the object to be compared.
     * @return  a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException  if the specified object is null
     * @throws ClassCastException  if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o)
    {
        if (o == null)
        {
            throw new NullPointerException();
        }

        Employee employee = (Employee) o;
        int compare = this.lastName.compareToIgnoreCase(employee.getLastName());

        if (compare > 0)
        {
            return 1;
        }
        else if (compare < 0)
        {
            return -1;
        }
        else
        {
            compare = this.firstName.compareToIgnoreCase(employee.getFirstName());

            if (compare > 0)
            {
                return 1;
            }
            else if (compare < 0)
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
    }

    public enum Field{ALL, FIRST_NAME, LAST_NAME, GENDER, TENURE, RATE, SALARY}
    private String firstName;
    private String lastName;

    // Optional
    private char gender;
    private int tenure;
    private char rate;
    private double salary;

    public static class Builder
    {
        private final String firstName;
        private final String lastName;

        // Optional
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

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setGender(char gender)
    {
        this.gender = gender;
    }

    public char getGender()
    {
        return this.gender;
    }

    public void setTenure(int tenure)
    {
        this.tenure = tenure;
    }

    public int getTenure()
    {
        return tenure;
    }

    public void setRate(char rate)
    {
        this.rate = rate;
    }

    public char getRate()
    {
        return rate;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public double getSalary()
    {
        return salary;
    }

    public void dump(SuperOutput so)
    {
        so.println("First name: " + this.firstName);
        so.println("Last name: " + this.lastName);
        so.println("Gender: " + this.gender);
        so.println("Tenure: " + this.tenure);
        so.println("Rate: " + this.rate);
        so.println("Salary: " + this.salary);
    }
}
