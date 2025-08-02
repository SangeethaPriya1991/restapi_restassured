package Controller;

import EmployeeDataAccessObject.EmployeeDAO;
import Model.EmployeeDetails;
import Response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employeedetails")
public class EmployeeAPIService {

    @GetMapping("/")
    public ResponseEntity<Object> getAllEmoloyeeDetails() {
        List<EmployeeDetails> employee = EmployeeDAO.getAllEmployees();
        return ResponseHandler.responseBuilder(
                "All employee details retrieved successfully",
                HttpStatus.OK,
                employee);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeDetails(@PathVariable("id") String id) {
        return EmployeeDAO.getEmployeeDetailsById(id)
                .map(employee -> ResponseHandler.responseBuilder(
                        "Requested employee details are given here",
                        HttpStatus.OK,
                        employee))
                .orElseGet(() -> ResponseHandler.responseBuilder(
                        "Employee not found",
                        HttpStatus.NOT_FOUND,
                        null));

    }
    @PostMapping
    public  ResponseEntity<Object>  createEmplpoyeeDetails(@RequestBody EmployeeDetails employeeDetails){
        EmployeeDetails addedEmployee = EmployeeDAO.addEmployee(employeeDetails);
        if(addedEmployee != null){
            return (ResponseHandler.responseBuilder(
                    "Successfully created EmployeeDetails",
                    HttpStatus.OK,
                    addedEmployee));
        }else{
            return ResponseHandler.responseBuilder(
                    "Failed to create EmployeeDetails",
                    HttpStatus.NOT_FOUND,
                    null);
        }
    }
    @PutMapping("/{id}")
    public  ResponseEntity<Object>  updateEmplpoyeeDetails(@PathVariable("id") String id, @RequestBody EmployeeDetails employeeDetails){
        if(!id.equals(employeeDetails.getEmployeeId())){
            return (ResponseHandler.responseBuilder(
                    "Path Id and body Id do not match",
                    HttpStatus.BAD_REQUEST,
                    null));
        }
        EmployeeDetails updatedDetails = EmployeeDAO.updateEmployee(employeeDetails);
        if(updatedDetails != null){
            return ResponseHandler.responseBuilder(
                    "Successfully updated EmployeeDetails",
                    HttpStatus.OK,
                    updatedDetails);
        } else{
            return ResponseHandler.responseBuilder(
                            "Employee not found",
                            HttpStatus.NOT_FOUND,
                            null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmplpoyeeDetails(@PathVariable("id") String id){
        boolean removed =EmployeeDAO.deleteEmployeeBYId(id);
        if(removed){
            return ResponseHandler.responseBuilder(
                    "Employee id deleted Successfully",
                    HttpStatus.OK,
                    removed);
        }
        else{
            return ResponseHandler.responseBuilder(
                    "Employee not found",
                    HttpStatus.NOT_FOUND,
                    null);
        }


    }

}
