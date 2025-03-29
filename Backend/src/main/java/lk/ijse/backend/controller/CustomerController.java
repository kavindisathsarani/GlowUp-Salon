package lk.ijse.backend.controller;

import jakarta.persistence.GeneratedValue;

import lk.ijse.backend.dto.CustomerDTO;
import lk.ijse.backend.service.impl.CustomerServiceImpl;
import lk.ijse.backend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    //testing
    @GeneratedValue
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @PostMapping(path = "save")
    public ResponseUtil getCustomer(@RequestBody CustomerDTO customerDTO) {
        customerServiceImpl.save(customerDTO);
        return new ResponseUtil(200, "Customer is saved", null);
    }

    @GetMapping(path = "getAll")
    public ResponseUtil getCustomers() {
        return new ResponseUtil(200, "Success", customerServiceImpl.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUtil> getCustomerById(@PathVariable int id) {
        CustomerDTO customerDTO = customerServiceImpl.getById(id);
        return ResponseEntity.ok(new ResponseUtil(200, "Success", customerDTO));
    }

    @PutMapping(path = "update")
    public ResponseUtil updateCustomer(@RequestBody CustomerDTO customerDTO) {
        customerServiceImpl.update(customerDTO);
        return new ResponseUtil(200, "Customer Updated Successfully", null);
    }


    @DeleteMapping(path = "delete/{id}")
    public ResponseUtil deleteCustomer(@PathVariable int id) {
         customerServiceImpl.delete(id);
            return new ResponseUtil(200, "Customer is deleted", null);
    }
}
