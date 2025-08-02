package Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","name","email","phone"})
public class EmployeeDetails {
    String Name;
    String Id;
    String Email;
    String Phone;


    public EmployeeDetails() {
    }

    public EmployeeDetails(String Id, String Name, String Email,String Phone) {
        this.Id = Id;
        this.Name = Name;
        this.Email = Email;
        this.Phone = Phone;
    }

    @JsonProperty("id")
    public String getEmployeeId() {
        return Id;
    }

    public void setEmployeeId(String Id) {
        this.Id = Id;
    }
    @JsonProperty("name")
    public String getEmployeeName() {
        return Name;
    }

    public void setEmployeeName(String Name) {
        this.Name = Name;
    }
    @JsonProperty("phone")
    public String getEmployeePhone() {
        return Phone;
    }

    public void setEmployeePhone(String Phone) {
        this.Phone = Phone;
    }
    @JsonProperty("email")
    public String getEmployeeEmail() {
        return Email;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.Email = employeeEmail;
    }

}
