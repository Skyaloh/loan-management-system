package com.credable.lms.resource;


import com.credable.lms.service.SubscriptionService;
import com.credable.lms.service.dto.CustomerDTO;
import com.credable.lms.service.dto.SubscriptionDTO;
import com.credable.lms.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;



@RestController
@RequestMapping("/api")
public class SubscriptionResource {
    private final Logger log = LoggerFactory.getLogger(SubscriptionResource.class);

    private final SubscriptionService subscriptionService;

    public SubscriptionResource(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/customer/subscription")
    public ResponseEntity<CustomerDTO> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        log.debug("REST request to save customerDTO : {}", subscriptionDTO);
        CustomerDTO result = subscriptionService.subscribe(subscriptionDTO.getCustomerNumber());
        return ResponseUtil.wrapOrNotFound(Optional.of(result));
    }
}
