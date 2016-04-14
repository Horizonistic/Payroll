package main;

import utils.*;

public class Driver
{
    public static void main(String[] args)
    {
        SuperOutput so = new SuperOutput("csis.txt");
        Payroll pr = new Payroll(so);
        pr.readFiles();
    }
}
