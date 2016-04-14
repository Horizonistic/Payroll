package main;

import utils.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public void readFiles()
    {
        String filename = "data/payfile.txt";
        BufferedReader br;

        /*
            Payfile
         */
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

        /*
            Hire
         */
        filename = "data/hirefile.txt";

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
            return;
        }
    }

    public void printStats()
    {
        this.so.println("Contents of the entire list: ");
        this.list.dump();
        this.so.println("Number of Employees: ");
        this.so.println("");
    }

    public ObjectList findObjects(ObjectList list, Object...)
    {

    }
}
