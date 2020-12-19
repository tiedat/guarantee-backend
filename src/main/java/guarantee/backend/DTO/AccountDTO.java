package guarantee.backend.DTO;

import lombok.Data;

@Data
public class AccountDTO {
    private Long id;
    private String username;
    private String password;
    private String role;
}
