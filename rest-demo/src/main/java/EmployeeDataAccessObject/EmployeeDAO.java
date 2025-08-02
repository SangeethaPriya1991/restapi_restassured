package EmployeeDataAccessObject;

import Model.EmployeeDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO {
    private static List<EmployeeDetails> employeeDetailsList = new ArrayList<>();

    static{
        employeeDetailsList.add(new EmployeeDetails("101","Sarah","sarah@example.com","1234345"));
        employeeDetailsList.add(new EmployeeDetails("102","Yosef","yosef@example.com","56745367"));
        employeeDetailsList.add(new EmployeeDetails("103","Geneia","geneia@example.com","237898498"));
        employeeDetailsList.add(new EmployeeDetails("104","Shlomo","shlomo@example.com","868756537"));
        employeeDetailsList.add(new EmployeeDetails("105","Rahul","rahul@example.com","37687564785"));
        employeeDetailsList.add(new EmployeeDetails("106","Nancy","nancy@example.com","88666453"));
    }

    public static List<EmployeeDetails> getAllEmployees(){
        return employeeDetailsList;
    }
    public static Optional<EmployeeDetails> getEmployeeDetailsById(String id){
        return employeeDetailsList.stream()
                .filter(emp->emp.getEmployeeId().equals(id))
                        .findFirst();
    }
    public static EmployeeDetails addEmployee(EmployeeDetails employeeDetails){
        boolean added = employeeDetailsList.add(employeeDetails);
        return added ?employeeDetails : null;

    }
    public static EmployeeDetails updateEmployee(EmployeeDetails updatedemployee){
        for(int i=0;i<employeeDetailsList.size();i++){
            if(employeeDetailsList.get(i).getEmployeeId().equals(updatedemployee.getEmployeeId())){
                employeeDetailsList.set(i,updatedemployee);
                return updatedemployee;
            }
        }return null;
    }
    public static boolean deleteEmployeeBYId(String Id){
        return employeeDetailsList.removeIf(emp->emp.getEmployeeId().equals(Id));
    }
}
