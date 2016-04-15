package main;

import utils.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Payroll
{
    private SuperOutput so;
    private ObjectList list;

    public Payroll(SuperOutput so)
    {
        this.so = so;
        list = new ObjectList(this.so);
    }

    public void readPayroll()
    {
        String filename = "data/payfile.txt";
        BufferedReader br;

        // Open file
        try
        {
            br = new BufferedReader(new FileReader(filename));
        }
        catch (FileNotFoundException e)
        {
            this.so.println("File " + filename + " not found or cannot be opened.");
            return;
        }

        // Read line-by-line
        try
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] info = Pattern.compile("[\\s]+").split(line);
                Employee employee = new Employee.Builder(info[0], info[1])
                        .gender(info[2].charAt(0))
                        .tenure(Integer.valueOf(info[3]))
                        .rate(info[4].charAt(0))
                        .salary(Double.valueOf(info[5]))
                        .build();
                this.list.addLast(employee);
            }
        }
        catch (IOException e)
        {
            this.so.println("Error reading " + filename);
            return;
        }

        try
        {
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        this.printStats();
        //ObjectList newList = this.findEmployee(this.list, new Employee.Builder("John", "Smith").gender('F').tenure(8).rate('W').salary(150.00).build(), Employee.Field.GENDER);
    }

    public void readHire()
    {
        String filename = "data/hirefile.txt";
        BufferedReader br;

        // Open
        try
        {
            br = new BufferedReader(new FileReader(filename));
        }
        catch (FileNotFoundException e)
        {
            this.so.println("File " + filename + " not found or cannot be opened.");
            return;
        }

        // Line by line
        try
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] info = Pattern.compile("[\\s]+").split(line);
                Employee employee = new Employee.Builder(info[0], info[1])
                        .gender(info[2].charAt(0))
                        .tenure(Integer.valueOf(info[3]))
                        .rate(info[4].charAt(0))
                        .salary(Double.valueOf(info[5]))
                        .build();
                this.list.addLast(employee);
            }
        }
        catch (IOException e)
        {
            this.so.println("Error reading " + filename);
        }
    }

    public void readFire()
    {
        // todo
    }

    public void printStats()
    {
        Employee employee;
        this.so.println("Contents of the entire list: ");
        this.list.dump();

        this.so.println("Number of Employees: ");
        this.so.println(list.size());
        this.so.println();

        this.so.println("First name of all women: ");
        so.println();
        employee = new Employee.Builder("", "").gender('F').build();
        ObjectList tempList = findEmployee(this.list, employee, Employee.Field.GENDER);
        ObjectListNode temp = tempList.getFirstNode();
        while (temp != null)
        {
            this.printEmployee((Employee) temp.getInfo(), Employee.Field.FIRST_NAME);
            temp = temp.getNext();
        }

        // TODO: Figure out how to calculate yearly salaries gracefully
        this.so.println("Names and salaries of all weekly employees who make >$35k/year and have been employees for 5+ years: ");
        so.println();
        employee = new Employee.Builder("", "").rate('W').build();
        tempList = findEmployee(this.list, employee, Employee.Field.RATE);
        temp = tempList.getFirstNode();
        while (temp != null)
        {
            this.printEmployee((Employee) temp.getInfo(), Employee.Field.FIRST_NAME);
            temp = temp.getNext();
        }


        this.so.println("Raise of $0.75 to <$10/hour employees, and raise of $50 to <$350/week employees: ");
        this.so.println("Weekly: ");
        this.so.println();
        employee = new Employee.Builder("", "").rate('H').salary(10.00).build();
        tempList = findEmployee(this.list, employee, Employee.Field.RATE);

        temp = tempList.getFirstNode();
        ObjectList tempList2 = new ObjectList(this.so);
        while (temp != null)
        {
            if (((Employee) temp.getInfo()).getSalary() < employee.getSalary())
            {
                ((Employee) temp.getInfo()).setSalary(((Employee) temp.getInfo()).getSalary() + .75);
                this.printEmployee((Employee) temp.getInfo(), Employee.Field.FIRST_NAME, Employee.Field.LAST_NAME, Employee.Field.SALARY);
                tempList2.addLast(new ObjectListNode(temp.getInfo()));
            }
            temp = temp.getNext();
        }

        this.so.println("Monthly: ");
        this.so.println();
        employee = new Employee.Builder("", "").rate('W').salary(350.00).build();

        tempList = findEmployee(this.list, employee, Employee.Field.RATE);temp = tempList.getFirstNode();
        tempList2 = new ObjectList(this.so);
        while (temp != null)
        {
            if (((Employee) temp.getInfo()).getSalary() < employee.getSalary())
            {
                this.printEmployee((Employee) temp.getInfo(), Employee.Field.FIRST_NAME, Employee.Field.LAST_NAME, Employee.Field.SALARY);
                ((Employee) temp.getInfo()).setSalary(((Employee) temp.getInfo()).getSalary() + 50.0);
                this.printEmployee((Employee) temp.getInfo(), Employee.Field.FIRST_NAME, Employee.Field.LAST_NAME, Employee.Field.SALARY);
                tempList2.addLast(new ObjectListNode(temp.getInfo()));
            }
            temp = temp.getNext();
        }

        // todo: sort list alphabetically according to last name

        // todo: hirefile.txt

        // todo: firefile.txt
    }

    public ObjectList findEmployee(ObjectList list, Employee employee, Employee.Field... fields)
    {
        // ((Comparable)o).compareTo(p.getInfo())
        if (list.isEmpty())
        {
            return list;
        }
        else if (fields.length == 0)
        {
            return list;
        }

        ObjectListNode temp = list.getFirstNode();
        ObjectList newList = new ObjectList(this.so);

        if (fields[0] == Employee.Field.FIRST_NAME)
        {
            while (temp != null)
            {
                if ((((Employee) temp.getInfo()).getFirstName()).equalsIgnoreCase(employee.getFirstName()))
                {
                    newList.addLast(new ObjectListNode(temp.getInfo()));
                }
                temp = temp.getNext();
            }
        }
        else if (fields[0] == Employee.Field.LAST_NAME)
        {
            while (temp != null)
            {
                if ((((Employee) temp.getInfo()).getLastName()).equalsIgnoreCase(employee.getLastName()))
                {
                    newList.addLast(new ObjectListNode(temp.getInfo()));
                }
                temp = temp.getNext();
            }
        }
        else if (fields[0] == Employee.Field.GENDER)
        {
            while (temp != null)
            {
                if ((((Employee) temp.getInfo()).getGender()) == (employee.getGender()))
                {
                    newList.addLast(new ObjectListNode(temp.getInfo()));
                }
                temp = temp.getNext();
            }
        }
        else if (fields[0] == Employee.Field.TENURE)
        {
            while (temp != null)
            {
                if ((((Employee) temp.getInfo()).getTenure()) == (employee.getTenure()))
                {
                    newList.addLast(new ObjectListNode(temp.getInfo()));
                }
                temp = temp.getNext();
            }
        }
        else if (fields[0] == Employee.Field.RATE)
        {
            while (temp != null)
            {
                if ((((Employee) temp.getInfo()).getRate()) == (employee.getRate()))
                {
                    newList.addLast(new ObjectListNode(temp.getInfo()));
                }
                temp = temp.getNext();
            }
        }
        else if (fields[0] == Employee.Field.SALARY)
        {
            while (temp != null)
            {
                if (Double.compare((((Employee) temp.getInfo()).getSalary()), employee.getSalary()) == 0)
                {
                    newList.addLast(new ObjectListNode(temp.getInfo()));
                }
                temp = temp.getNext();
            }
        }

        if (fields.length > 0)
        {
            newList = findEmployee(newList, employee, Arrays.copyOfRange(fields, 1, fields.length));
        }
        return newList;
    }

    public void printEmployee(Employee employee, Employee.Field... fields)
    {
        if (employee == null)
        {
            return;
        }

        if (fields[0] == Employee.Field.ALL)
        {
            employee.dump(this.so);
        }

        for (Employee.Field field : fields)
        {
            switch (field)
            {
                case FIRST_NAME:
                    System.out.println("First name: " + employee.getFirstName());
                    break;

                case LAST_NAME:
                    System.out.println("Last name: " + employee.getLastName());
                    break;

                case GENDER:
                    System.out.println("Gender: " + employee.getGender());
                    break;

                case TENURE:
                    System.out.println("Tenure: " + employee.getTenure());
                    break;

                case RATE:
                    System.out.println("Rate: " + employee.getRate());
                    break;

                case SALARY:
                    System.out.println("Salary: " + employee.getSalary());
                    break;
            }
        }
        this.so.println();
    }
}