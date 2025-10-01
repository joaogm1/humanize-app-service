package humanize.service.controllers;

import humanize.service.entities.UserEntity;
import humanize.service.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserEntity userEntity) {
        userService.createUser(userEntity);
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/{login}")
    public ResponseEntity<UserEntity> getUser(@PathVariable String login) {
        UserEntity user = userService.getUserByLogin(login);
        return ResponseEntity.ok(user);
    }
}
