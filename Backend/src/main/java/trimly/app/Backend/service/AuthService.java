package trimly.app.Backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import trimly.app.Backend.dto.LoginRequest;
import trimly.app.Backend.dto.LoginResponse;
import trimly.app.Backend.dto.SignupRequest;
import trimly.app.Backend.entity.User;
import trimly.app.Backend.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // ✅ Single constructor for Dependency Injection
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.email)) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPhone(request.phone);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setRole(request.role);
        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user);
        LoginResponse response = new LoginResponse();
        response.token = token;
        response.name = user.getName();
        response.email = user.getEmail();
        response.role = user.getRole().name();
        return response;
    }
}
