package lk.ijse.backend.service;

import lk.ijse.backend.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    void save(InventoryDTO inventoryDTO);
    List<InventoryDTO> getAll();
    void update(InventoryDTO inventoryDTO);
    void delete(int id);
    InventoryDTO getById(int id);
}
