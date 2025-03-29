package lk.ijse.backend.service.impl;


import lk.ijse.backend.dto.CustomerDTO;
import lk.ijse.backend.entity.Customer;
import lk.ijse.backend.repo.CustomerRepo;
import lk.ijse.backend.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(CustomerDTO customerDTO) {

        if(customerRepo.existsById(customerDTO.getId())){
            throw new RuntimeException("Customer already exists");
        }
        customerRepo.save(modelMapper.map(customerDTO, Customer.class));
    }

    @Override
    public List<CustomerDTO> getAll() {
        return modelMapper.map(customerRepo.findAll(), new TypeToken<List<CustomerDTO>>(){}.getType());
    }

    @Override
    public CustomerDTO getById(int id) {
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        if (optionalCustomer.isPresent()) {
            return modelMapper.map(optionalCustomer.get(), CustomerDTO.class);
        }
        throw new RuntimeException("Customer not found");
    }

    @Override
    public void update(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getId())) {
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
        }
        throw new RuntimeException("Customer does not exist");
    }

    @Override
    public void delete(int id) {
        if (!customerRepo.existsById(id)) {
            throw new RuntimeException("Customer with ID " + id + " does not exist.");
        }
        customerRepo.deleteById(id);
    }
}
