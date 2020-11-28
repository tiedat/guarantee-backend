package guarantee.backend.DTO;

import lombok.Data;

@Data
public class LoginDTO {
    private Long id;
    private String username;
    private String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDTO() {
    }
}
