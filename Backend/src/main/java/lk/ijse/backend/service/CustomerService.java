package lk.ijse.backend.service;



import lk.ijse.backend.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void save(CustomerDTO customerDTO);
    List<CustomerDTO> getAll();
    void update(CustomerDTO customerDTO);
    void delete(int id);
    CustomerDTO getById(int id);
}
