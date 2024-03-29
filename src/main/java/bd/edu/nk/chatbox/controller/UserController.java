package bd.edu.nk.chatbox.controller;


import bd.edu.nk.chatbox.model.User;
import bd.edu.nk.chatbox.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<User>> getUsers(){
        List<User> userList = new ArrayList<>();
        userList = userService.getUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping(value = "{userName}")
    public ResponseEntity<User> getUsers(@PathVariable String userName){
        User user = userService.getUsersByName(userName);
        System.out.println("find by id");
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable String userName){
        boolean bool = userService.deleteByName(userName);
        System.out.println("delete on process");
        if(bool)
            return ResponseEntity.ok(userName);
        return ResponseEntity.notFound().build();
    }
}
