package guarantee.backend.service;

import guarantee.backend.DTO.AccountDTO;
import guarantee.backend.DTO.LoginDTO;
import guarantee.backend.model.Account;
import org.springframework.stereotype.Service;

@Service
public interface IAccountService {
    Account getByUsername(String username);

    AccountDTO login(LoginDTO loginDTO);
}
