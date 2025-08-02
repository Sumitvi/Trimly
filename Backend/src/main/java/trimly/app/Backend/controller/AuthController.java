package trimly.app.Backend.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trimly.app.Backend.dto.LoginRequest;
import trimly.app.Backend.dto.LoginResponse;
import trimly.app.Backend.dto.SignupRequest;
import trimly.app.Backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok().body("Signup successful!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}