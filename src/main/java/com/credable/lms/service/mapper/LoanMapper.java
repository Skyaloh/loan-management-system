package com.credable.lms.service.mapper;

import com.credable.lms.domain.Loan;
import com.credable.lms.service.dto.LoanDTO;
import org.mapstruct.Mapper;


/**
 * Mapper for the entity Loan and its DTO LoanDTO.
 */
@Mapper(componentModel = "spring", uses = { })
public interface LoanMapper extends EntityMapper<LoanDTO, Loan> {

    LoanDTO toDto(Loan loan);

    Loan toEntity(LoanDTO loanDTO);

    default Loan fromId(Long id) {
        if (id == null) {
            return null;
        }
        Loan loan = new Loan();
        loan.setId(id);
        return loan;
    }
}
