package guarantee.backend.controller;


import guarantee.backend.DTO.AccountDTO;
import guarantee.backend.model.Account;
import guarantee.backend.model.Role;
import guarantee.backend.service.IAccountService;
import guarantee.backend.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    @Qualifier("AccountService")
    private IAccountService accountService;

    @Autowired
    @Qualifier("RoleService")
    private IRoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAll() {
        List<Account> list = accountService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/delete")
    @ResponseBody
    public boolean deleteAccount(@RequestBody Long id) {
        return accountService.deleteAccount(id);
    }

    @GetMapping("/get-role-under-admin")
    public ResponseEntity<List<String>> getRoleUnderAdmin() {
        List<String> list = roleService.getRoleUnderAdmin();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseBody
    public boolean createAccount(@RequestBody AccountDTO dto) {
        return this.accountService.createAccount(dto);

    }
}
