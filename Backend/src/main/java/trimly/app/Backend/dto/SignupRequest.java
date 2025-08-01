package trimly.app.Backend.dto;


import trimly.app.Backend.entity.Role;

public class SignupRequest {
    public String name;
    public String email;
    public String phone;
    public String password;
    public Role role;
}