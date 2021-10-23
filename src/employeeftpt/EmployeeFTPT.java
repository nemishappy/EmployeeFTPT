/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeeftpt;

import java.util.List;
import java.util.Scanner;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nemishappy
 */
public class EmployeeFTPT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {   
        callMenu();
    }

    public static void callMenu() {
        Scanner sc = new Scanner(System.in);
        Boolean isDone = false;
        String name;
        int type;
        long id;
        do {
            System.out.println("==== Employee DBMS lite ==== ");
            System.out.println("1.View All Employee.");
            System.out.println("2.Insert Employee.");
            System.out.println("3.Update Employee.");
            System.out.println("4.Delete Employee.");
            System.out.println("0.Exit.");
            System.out.println("============================ ");
            int i = sc.nextInt();
            switch (i) {
                case 0:
                    isDone = true;
                    break;
                case 1:
                    List<Employee> empList = EmployeeTable.findAllEmployee();
                    printAllEmployee(empList);

                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("Enter Employee Name:");
                    name = sc.nextLine();
                    System.out.println("Enter Employee Type:");
                    System.out.println("Enter 1 for Full-Time");
                    System.out.println("Enter 2 for Part-Time");
                    type = sc.nextInt();
                    if (type == 1) {
                        System.out.println("Enter Salary:");
                        int salary = sc.nextInt();
                        FulltimeEmp emp = new FulltimeEmp();
                        emp.setName(name);
                        emp.setSalary(salary);
                        EmployeeTable.insertEmployee(emp);
                    } else {
                        System.out.println("Enter Hours Work:");
                        int HW = sc.nextInt();
                        ParttimeEmp emp = new ParttimeEmp();
                        emp.setName(name);
                        emp.setHoursWork(HW);
                        EmployeeTable.insertEmployee(emp);
                    }
                    System.out.println("Successful.");
                    break;
                case 3:
                    System.out.println("Enter Employee ID:");
                    id = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Enter New Employee Name:");
                    name = sc.nextLine();
                    System.out.println("Enter Employee Type:");
                    System.out.println("Enter 1 for Full-Time");
                    System.out.println("Enter 2 for Part-Time");
                    type = sc.nextInt();
                    if (type == 1) {
                        System.out.println("Enter new Salary:");
                        int salary = sc.nextInt();
                        FulltimeEmp emp = (FulltimeEmp) EmployeeTable.findEmployeeById(id);
                        emp.setName(name);
                        emp.setSalary(salary);
                        if (emp != null) {
                            EmployeeTable.updateEmployee(emp);
                        } else {
                            System.out.println("Not found.");
                            break;
                        }
                    } else {
                        System.out.println("Enter Hours Work:");
                        int HW = sc.nextInt();
                        ParttimeEmp emp = (ParttimeEmp) EmployeeTable.findEmployeeById(id);
                        emp.setName(name);
                        emp.setHoursWork(HW);
                        if (emp != null) {
                            EmployeeTable.updateEmployee(emp);
                        } else {
                            System.out.println("Not found.");
                            break;
                        }
                    }
                    System.out.println("Successful.");
                    break;
                case 4:
                    System.out.println("Enter Employee ID:");
                    id = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Enter Employee Type:");
                    System.out.println("Enter 1 for Full-Time");
                    System.out.println("Enter 2 for Part-Time");
                    type = sc.nextInt();
                    if (type == 1) {
                        FulltimeEmp emp = (FulltimeEmp) EmployeeTable.findEmployeeById(id);
                        if (emp != null) {
                            EmployeeTable.removeEmployee(emp);
                        } else {
                            System.out.println("Not found.");
                            break;
                        }
                    } else {
                        ParttimeEmp emp = (ParttimeEmp) EmployeeTable.findEmployeeById(id);
                        if (emp != null) {
                            EmployeeTable.removeEmployee(emp);
                        } else {
                            System.out.println("Not found.");
                            break;
                        }
                    }
                    System.out.println("Successful.");
                    break;
                default:
                    System.out.println("Please enter number 0-4");
            }
            System.out.println("============================ ");
        } while (!isDone);
    }

    public static void printAllEmployee(List<Employee> employeeList) {
        for (Employee emp : employeeList) {
            System.out.print(emp.getId() + " ");
            System.out.print(emp.getName() + " ");
            DiscriminatorValue val = emp.getClass().getAnnotation(DiscriminatorValue.class);
            String type = val.value();
            System.out.print(type + " ");
            if(type.equals("FT")){
                FulltimeEmp ft = (FulltimeEmp)emp;
                System.out.println(ft.getSalary() + " ");
            }else{
                ParttimeEmp pt = (ParttimeEmp)emp;
                System.out.println(pt.getHoursWork() + " ");
            }
        }
    }

    

}
