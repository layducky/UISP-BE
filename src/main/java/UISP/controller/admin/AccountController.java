package UISP.controller.admin;

import UISP.util.ApiMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import UISP.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/user/{email}")
    @ApiMessage("Delete User")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
        System.out.println(email + "nono");
        boolean deleted = userService.deleteUserByEmail(email);
        System.out.println(email + " deleted: " + deleted);
        if (deleted) {
            return ResponseEntity.ok("User with email " + email + " has been deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}