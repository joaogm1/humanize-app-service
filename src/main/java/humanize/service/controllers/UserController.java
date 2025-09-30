package humanize.service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @PostMapping("/create")
    public String createUser() {
        return "userCreated";
    }

    @GetMapping("/{login}")
    public String getUser() {
        return "userDetails";
    }

}
