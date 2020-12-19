package guarantee.backend.service.Impl;

import guarantee.backend.DTO.AccountDTO;
import guarantee.backend.DTO.LoginDTO;
import guarantee.backend.model.Account;
import guarantee.backend.model.Role;
import guarantee.backend.repositories.AccountRepository;
import guarantee.backend.service.IAccountService;
import org.modelmapper.ModelMapper;
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
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Role roleAdmin;
    @Autowired
    private Role roleSaler;
    @Autowired
    private Role roleTech;

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

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    @Override
    public List<Account> getAll() {
        List<Account> list = (List<Account>) repository.findAll();
        return list;
    }

    @Override
    public boolean deleteAccount(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createAccount(AccountDTO dto) {
        try {
            Account account = modelMapper.map(dto, Account.class);
            account.setPassword(encodePassword(account.getPassword()));
            String role = dto.getRole();
            switch (role) {
                case "ROLE_SALER": {
                    account.setRole(roleSaler);
                    break;
                }
                case "ROLE_TECHNICIANS": {
                    account.setRole(roleTech);
                    break;
                }

            }
            repository.save(account);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
