package guarantee.backend.service;

import guarantee.backend.DTO.AccountDTO;
import guarantee.backend.DTO.LoginDTO;
import guarantee.backend.model.Account;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public interface IAccountService {
    Account getByUsername(String username);

    AccountDTO login(LoginDTO loginDTO);

    Optional<User> getCurrentUser();

    void logout(HttpServletRequest request, HttpServletResponse response);


}
