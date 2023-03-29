package com.myrestapi.restapi;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Servers")
public class ServerController {
   

    @Autowired
    private ServerRepository serverRepository;
    

// Getting all server data present in database
    @GetMapping("/allservers")
    public List<Server> getAllserver(){
        return serverRepository.findAll();
    }


// Getting a server data by id from database
    @GetMapping("/serverbyId")
    public ResponseEntity<Server> getServerById(@RequestParam long id) {
        Optional<Server> server = serverRepository.findById(id);
        if (server.isPresent()) {
            return ResponseEntity.ok().body(server.get());
        } else {
            throw new ServerNotFoundException("Server not found");
        }
    }
    

// Getting a all server data related to given name from database with non-case-sensitive
    @GetMapping("/serverbyName")
    public ResponseEntity<List<Server>> getServerByName(@RequestParam String name) {
        Optional<List<Server>> serverbyname = serverRepository.findByNameIgnoreCase(name);
        if (serverbyname.isPresent()) {
            return ResponseEntity.ok(serverbyname.get());//.body(server.get());
        } else {
            throw new ServerNotFoundException("Server not found");
        }
    }


// Adding a new server data to database
    @PostMapping("/addserver")
    public String addServer(@RequestBody Server server){
        serverRepository.save(server);
        return ("Server is added successfully");
    }


// Updating a server data with id in database
    @PutMapping("/updateserver/{id}")
    public Server updatServer(@PathVariable long id, @RequestBody Server server){

        Server existServer = serverRepository.findById(id).orElse(null);
        existServer.setName(server.getName());
        existServer.setLanguage(server.getLanguage());
        existServer.setFramework(server.getFramework());
        return serverRepository.save(existServer);
    }


// Deleting server data with id from database
    @DeleteMapping("/")
    public String deleteServer(@RequestParam long id) {
        if(serverRepository.existsById(id)) {
            serverRepository.deleteById(id);
            return "Server is deleted seccessfully";
        }
        else{
            throw new ServerNotFoundException("Server not found");
        }
        
    }


// Exception handler for ServerNotFoundException
    @ExceptionHandler(ServerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> ServerNotFoundExceptionHandler(ServerNotFoundException ex) {
        Map<String, Object> errorResponse = new LinkedHashMap<>();
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
