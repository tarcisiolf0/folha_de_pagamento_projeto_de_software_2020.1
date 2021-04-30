package payment;

import java.util.Scanner;

import app.EmployessFunction;

import java.util.ArrayList;
import java.util.Calendar;

import wage.Paycheck;
import employees.Sales;
import employees.Employee;
import employees.Hourly;
import employees.Comissioned;
import employees.Salaried;
import date.Day;

public class Payroll {
    
    public static void PaymentList(ArrayList<Employee> employeesList) {
        
        Scanner input = new Scanner(System.in);
        String date = new String();
        String dayWeekString = new String();
        double salary = 0.0f;
        int dayWeekInt = -1;
        int weekMonth = -1;
        int lastDayMonth = -1;
        int dayDate = -1;

        System.out.println("Digite a data de hoje no formato dd/MM/yyyy");
        date = input.nextLine();

        //System.out.println("Data "+ date);
        String paymentAgenda = new String();
        //Paycheck newPaycheck = null;
        
        String[] dateSplit = date.split("/");
        dayDate = Integer.parseInt(dateSplit[0]);

        for (Employee employee : employeesList){
            salary = -1.0f;
            dayWeekInt = -1;
            weekMonth = -1;
            lastDayMonth = -1;

            paymentAgenda = employee.getSalary().getPaymentAgenda();
            //System.out.println("Agenda de pagamento "+ paymentAgenda);
            dayWeekString = Day.getWeekDayString(date);
            //System.out.println("Dia da semana "+ dayWeekString);
            weekMonth = Day.WeekMonth(date);
            //System.out.println("Nº da semana do mês "+ weekMonth);
            lastDayMonth = Day.LastDayMonth(date);
            //System.out.println("Último dia do mês "+ lastDayMonth);
            //System.out.println("\n");

            System.out.println("\n");
            if (paymentAgenda.intern() == "Weekly".intern() && dayWeekString.intern() == "SEX".intern()) {
                System.out.println("Pagamento Horista\n");
                System.out.println(employee.printHourlyInfo(employee));

                double normalTaxe = employee.getSalary().getNormalTaxe();
                
                salary = employee.getSalary().calcSalary(employee, "Hourly", 0.0f, 0.0f, 0.0f, ((Hourly)employee).getTimecard(), normalTaxe, employee.getFiliated());

                System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
                System.out.println("O valor do pagamento é de R$" + salary);

                ((Hourly)employee).getTimecard().setNumberHours(0.0f);
                employee.getSalary().setPaymentDate(date);
            
            }

            else if(paymentAgenda.intern() == "Monthly".intern() && dayDate == lastDayMonth){
                System.out.println("Pagamento Salariado\n");
                System.out.println(employee.printSalariedInfo(employee));

                double baseSalary = employee.getSalary().getBaseSalary();

                salary = employee.getSalary().calcSalary(employee, "Salaried", ((Salaried)employee).getBaseSalary(), 0.0f, 0.0f, null, 0.0f, employee.getFiliated());

                System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
                System.out.println("O valor do pagamento é de R$" + salary);

                employee.getSalary().setPaymentDate(date);

            }

            else if(paymentAgenda.intern() == "Two-Weekly".intern() && (weekMonth == 2 || weekMonth == 4) && (dayWeekString.intern() == "SEX".intern())){
                System.out.println("Pagamento Vendedor\n");
                System.out.println(employee.printComissionedInfo(employee));

                double baseSalary = ((Comissioned)employee).getBaseSalary();
                double comission = ((Comissioned)employee).getComission();
                double sales = ((Comissioned)employee).getSales().getValue();

                salary = employee.getSalary().calcSalary(employee ,"Comissioned", baseSalary, comission, sales, null, 0.0f, employee.getFiliated());

                System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
                System.out.println("O valor do pagamento é de R$" + salary);

                ((Comissioned)employee).getSales().setValue(0.0f);
                employee.getSalary().setPaymentDate(date);
            }
        }
    }

    public void changeEmployeePaymentAgenda(ArrayList<Employee> employeesList) {

        Scanner input = new Scanner(System.in);
        String name = new String();
        int paymentAgenda = -1;
        int indexOfEmployee = -1;

        System.out.println("Digite o nome do empregado onde será editada a agenda de pagamento");
        name = input.nextLine();
        System.out.println("Digite o nº da opção da nova agenda de pagamento do empregado dentre as seguintes opções:\n '1 - Semanalmente', '2 - Mensalmente', '3 - Bi-semanalmente'");
        paymentAgenda = input.nextInt();

        indexOfEmployee = EmployessFunction.getIndexList(employeesList, name);
        Employee employee = employeesList.get(indexOfEmployee);

        switch (paymentAgenda) {
            case 1:
                employee.getSalary().setPaymentAgenda("Weekly");
                break;
            case 2:
                employee.getSalary().setPaymentAgenda("Monthly");
                break;
            case 3:
                employee.getSalary().setPaymentAgenda("Two-Weekly");
                break;
            default:
                break;
        }

        employeesList.set(indexOfEmployee, employee);
    }
}
