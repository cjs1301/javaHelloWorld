package hello.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class MainController {


    @GetMapping("/world")
    public DataObject World (){
        return new DataObject("world!");
    }

    @GetMapping("/application")
    public ResponseEntity<String> application (@RequestHeader("x-api-key") String value){
        if(value.equals("1234")) {
            return new ResponseEntity(new DataObject("application!"), HttpStatus.OK);
        }
        return new ResponseEntity(new Error("ApiKey"), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/user")
    public ResponseEntity<String> user (@RequestHeader("Authorization") String value){
        if(value.split(" ")[1].equals("abcd")) {
            return new ResponseEntity(new DataObject("user!"), HttpStatus.OK);
        }
        return new ResponseEntity(new Error("access token"), HttpStatus.UNAUTHORIZED);
    }

    class DataObject {
        private String message;
        public String getMessage(){
            return message;
        }
        public DataObject(String message){
            this.message = "Hello "+ message;
        }
    }
    class Error {
        private String message;
        public String getMessage(){
            return message;
        }
        public Error(String message){
            this.message = "Invalid "+ message;
        }
    }
}

