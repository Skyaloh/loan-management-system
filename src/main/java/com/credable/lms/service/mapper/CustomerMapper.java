package com.credable.lms.service.mapper;

import com.credable.lms.domain.Customer;
import com.credable.lms.service.dto.CustomerDTO;
import org.mapstruct.Mapper;


/**
 * Mapper for the entity Customer and its DTO CustomerDTO.
 */
@Mapper(componentModel = "spring", uses = { })
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {

    CustomerDTO toDto(Customer customer);

    Customer toEntity(CustomerDTO customerDTO);

    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
