package guarantee.backend.service.Impl;

import guarantee.backend.DTO.AccountDTO;
import guarantee.backend.DTO.LoginDTO;
import guarantee.backend.model.Account;
import guarantee.backend.repositories.AccountRepository;
import guarantee.backend.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component("AccountService")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Account getByUsername(String username) {
        Account account = repository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("No user found " + username));
        return account;
    }

    @Override
    public AccountDTO login(LoginDTO requestUser) {
        AccountDTO accountDTO = new AccountDTO();
        UsernamePasswordAuthenticationToken authenticationTokenRequest = new
                UsernamePasswordAuthenticationToken(requestUser.getUsername(), requestUser.getPassword());
        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationTokenRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            User user = (User) authentication.getPrincipal();
            if (null != user) {

                accountDTO.setUsername(user.getUsername());
                accountDTO.setRole(user.getAuthorities().toArray()[0].toString());
            }

        } catch (BadCredentialsException ex) {
            ex.printStackTrace();
            return null;
        }
        return accountDTO;
    }

    @Override
    public Optional<User> getCurrentUser() {
        User principal = (User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
