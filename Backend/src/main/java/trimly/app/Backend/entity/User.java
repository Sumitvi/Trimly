package trimly.app.Backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password; // Store hashed password only!

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String avatarUrl;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters

    // ... (generate these)
}