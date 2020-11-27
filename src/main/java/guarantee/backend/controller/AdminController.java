package guarantee.backend.controller;


import guarantee.backend.DTO.PolicyDTO;
import guarantee.backend.model.Policy;
import guarantee.backend.service.IPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/policy")
public class AdminController {
    @Qualifier("PolicyService")
    @Autowired
    private IPolicyService policyService;

    @GetMapping("/{id}")
    public ResponseEntity<PolicyDTO> getPolicyById(@PathVariable("id") Long id) {
        PolicyDTO policy = policyService.getById(id);
        return new ResponseEntity<>(policy, HttpStatus.OK);
    }

    @PostMapping("/save-policy")
    public ResponseEntity<PolicyDTO> savePolicy(@RequestBody Policy policy) {
        PolicyDTO policy1 = policyService.savePolicy(policy);
        if (null != policy1)
            return new ResponseEntity<>(policy1, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
