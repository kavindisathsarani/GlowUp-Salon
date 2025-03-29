package lk.ijse.backend.controller;

import jakarta.persistence.GeneratedValue;
import lk.ijse.backend.dto.InventoryDTO;
import lk.ijse.backend.service.impl.InventoryServiceImpl;
import lk.ijse.backend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {
    @GeneratedValue
    @Autowired
    private InventoryServiceImpl inventoryServiceImpl;

    @PostMapping(path = "save")
    public ResponseUtil saveInventory(@RequestBody InventoryDTO inventoryDTO) {
        inventoryServiceImpl.save(inventoryDTO);
        return new ResponseUtil(200, "Inventory item is saved", null);
    }

    @GetMapping(path = "getAll")
    public ResponseUtil getAllInventory() {
        return new ResponseUtil(200, "Success", inventoryServiceImpl.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUtil> getInventoryById(@PathVariable int id) {
        InventoryDTO inventoryDTO = inventoryServiceImpl.getById(id);
        return ResponseEntity.ok(new ResponseUtil(200, "Success", inventoryDTO));
    }

    @PutMapping(path = "update")
    public ResponseUtil updateInventory(@RequestBody InventoryDTO inventoryDTO) {
        inventoryServiceImpl.update(inventoryDTO);
        return new ResponseUtil(200, "Inventory item updated successfully", null);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseUtil deleteInventory(@PathVariable int id) {
        inventoryServiceImpl.delete(id);
        return new ResponseUtil(200, "Inventory item is deleted", null);
    }
}
