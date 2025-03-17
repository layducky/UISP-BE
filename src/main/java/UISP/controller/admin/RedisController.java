package UISP.controller.admin;

import UISP.service.UserService;
import UISP.util.ApiMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RedisController {
    private final RedisTemplate<String, String> redisTemplate;
    private final UserService userService;

    public RedisController(RedisTemplate<String,String> redisTemplate, UserService userService) {
        this.redisTemplate = redisTemplate;
        this.userService = userService;
    }
    @PostMapping("/addBlacklist")
    @ApiMessage("Add User to BLACKLIST")
    public ResponseEntity<?> addUserToBlacklist(@RequestParam("email") String email) {
        redisTemplate.opsForSet().add("blacklist", email);
        return ResponseEntity.ok("Add User to BLACKLIST");
    }
    @GetMapping("/checkUser")
    @ApiMessage("Check User in Redis")
    public ResponseEntity<?> checkUser(@RequestParam("email") String email) {
        return ResponseEntity.ok(redisTemplate.opsForSet().isMember("blacklist",email));
    }
}
