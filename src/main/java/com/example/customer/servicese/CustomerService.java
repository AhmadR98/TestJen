package com.example.customer.servicese;


import com.example.customer.dto.CustomerDto.DtoRequst;
import com.example.customer.dto.CustomerDto.DtoResponse;
import com.example.customer.entities.Customer;
import com.example.customer.records.FraudCheckResponse;
import com.example.customer.repositries.CustomerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    public DtoResponse create(DtoRequst dtoRequst) throws IllegalAccessException {
        //convert Respnse -> student
        Customer customer = mapper.map(dtoRequst, Customer.class);
        //save in db
        customer = customerRepo.save(customer);
        //convert student-> requst
        DtoResponse dtoResponse = mapper.map(customer, DtoResponse.class);

        FraudCheckResponse fraudCheckResponse = null;

        //restfull communication with fraud project
        try {
            fraudCheckResponse = restTemplate.
                    getForObject("http://localhost:8081/fraud/{id}",
                            FraudCheckResponse.class,
                            customer.getId());
        } catch (Exception en) {
            en.printStackTrace();
        }

        if (fraudCheckResponse != null && fraudCheckResponse.isFraudster())
            throw new IllegalAccessException("cant creat customer, the customer is a fraud");
        return dtoResponse;
    }
}
