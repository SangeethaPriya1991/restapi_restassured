package Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpstatus,Object responseObject){

        Map<String,Object> response = new HashMap<>();
        response.put("Message",message);
        response.put("Http Status",httpstatus);
        response.put("Data",responseObject);
        return new ResponseEntity<>(response,httpstatus);
    }
    }

