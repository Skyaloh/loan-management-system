package com.credable.lms.resource;


import com.credable.lms.IntegrationTest;
import com.credable.lms.domain.Loan;
import com.credable.lms.domain.enumeration.LoanStatus;
import com.credable.lms.repository.LoanRepository;
import com.credable.lms.resource.errors.ExceptionTranslator;
import com.credable.lms.service.LoanManagementService;
import com.credable.lms.service.LoanQueryService;
import com.credable.lms.service.dto.LoanRequestDTO;
import com.credable.lms.util.TestUtil;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;


;
import java.math.BigDecimal;
import java.util.List;

import static com.credable.lms.util.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LoanResource REST controller.
 *
 * @see LoanResource
 */
@IntegrationTest
public class LoanResourceIntTest {

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(10);

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLoanMockMvc;

    @Autowired
    private LoanManagementService loanManagementService;

    @Autowired
    private LoanQueryService loanQueryService;

    private Loan loan;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        final LoanResource loanResource = new LoanResource(loanManagementService,loanQueryService);
        this.restLoanMockMvc =
            MockMvcBuilders
                .standaloneSetup(loanResource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setControllerAdvice(exceptionTranslator)
                .setConversionService(createFormattingConversionService())
                .setMessageConverters(jacksonMessageConverter)
                .build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Loan createEntity(EntityManager em) {
        Loan loan = new Loan();
        loan.setLoanStatus(LoanStatus.PENDING);
        loan.setAmount(DEFAULT_AMOUNT);
        return loan;
    }

    @BeforeEach
    public void initTest() {
        loan = createEntity(em);
    }

    @Test
    @Transactional
    public void requestLoan() throws Exception {
        int databaseSizeBeforeCreate = loanRepository.findAll().size();

        // Create the Loan
        restLoanMockMvc
            .perform(
                post("/api/loan/request")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(TestUtil.convertObjectToJsonBytes(loan))
            )
            .andExpect(status().isCreated());

        // Validate the Loan in the database
        List<Loan> loanList = loanRepository.findAll();
        assertThat(loanList).hasSize(databaseSizeBeforeCreate + 1);
        Loan testLoan = loanList.get(loanList.size() - 1);
        assertThat(testLoan.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }


    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = loanRepository.findAll().size();
        // set the field null
        LoanRequestDTO loanRequestDTO = new LoanRequestDTO();

        // Create the Loan, which fails.

        restLoanMockMvc
            .perform(
                post("/api/loan/request")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(TestUtil.convertObjectToJsonBytes(loanRequestDTO))
            )
            .andExpect(status().isBadRequest());

        List<Loan> loanList = loanRepository.findAll();
        assertThat(loanList).hasSize(databaseSizeBeforeTest);
    }


    @Test
    @Transactional
    public void getLoanStatus() throws Exception {
        // Initialize the database
        loanRepository.saveAndFlush(loan);

        // Get the loan
        restLoanMockMvc
            .perform(get("/api/loan/status/{id}", loan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(loan.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLoanStatus() throws Exception {
        // Get the loan
        restLoanMockMvc.perform(get("/api/loan/status/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }



    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Loan.class);
        Loan loan1 = new Loan();
        loan1.setId(1L);
        Loan loan2 = new Loan();
        loan2.setId(loan1.getId());
        assertThat(loan1).isEqualTo(loan2);
        loan2.setId(2L);
        assertThat(loan1).isNotEqualTo(loan2);
        loan1.setId(null);
        assertThat(loan1).isNotEqualTo(loan2);
    }
}
