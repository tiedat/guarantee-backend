package guarantee.backend.controller;

import guarantee.backend.DTO.AccountDTO;
import guarantee.backend.DTO.LoginDTO;
import guarantee.backend.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    @Qualifier("AccountService")
    private IAccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<AccountDTO> login(@RequestBody LoginDTO loginDTO) {
        AccountDTO accountDTO = accountService.login(loginDTO);
        if (null != accountDTO)
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        return new
                ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/logout")
    public HttpStatus logoutPage(HttpServletRequest request, HttpServletResponse response) {
        accountService.logout(request, response);
        return HttpStatus.OK;
    }

}
