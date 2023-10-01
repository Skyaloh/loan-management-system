package com.credable.lms.resource;

import com.credable.lms.service.LoanManagementService;
import com.credable.lms.service.LoanQueryService;
import com.credable.lms.service.dto.LoanDTO;
import com.credable.lms.service.dto.LoanRequestDTO;
import com.credable.lms.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoanResource {

    private final Logger log = LoggerFactory.getLogger(SubscriptionResource.class);

    private final LoanManagementService loanManagementService;

    private final LoanQueryService loanQueryService;

    public LoanResource(LoanManagementService loanManagementService, LoanQueryService loanQueryService) {
        this.loanManagementService = loanManagementService;
        this.loanQueryService = loanQueryService;
    }

    @PostMapping("/loan/request")
    public ResponseEntity<LoanDTO> requestLoan(@RequestBody LoanRequestDTO loanRequest) {
        log.debug("REST request to save loanRequest : {}", loanRequest);

        LoanDTO loanDTO = loanManagementService.applyForLoan(loanRequest);
        return ResponseEntity.ok().body(loanDTO);
    }

    @GetMapping("/loan/status/{id}")
    public ResponseEntity<LoanDTO> getLoan(@PathVariable Long id) {
        log.debug("REST request to get Loan : {}", id);
        Optional<LoanDTO> result = loanQueryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(result);
    }
}
