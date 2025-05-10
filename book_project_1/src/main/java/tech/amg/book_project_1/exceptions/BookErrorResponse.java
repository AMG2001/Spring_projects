package tech.amg.book_project_1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

public class BookErrorResponse extends ResponseEntity {

    public BookErrorResponse(String message,HttpStatus status) {
        super(buildBody(message,status.value()),status);
    }

    private static Map<String,Object> buildBody(String message, int status){
        Map<String,Object> body = new HashMap<>();
        body.put("message",message);
        body.put("status",status);
        body.put("timeStamp",OffsetDateTime.now().toLocalTime().toString());
        return body;
    }
}
