package lk.ijse.backend.service.impl;

import lk.ijse.backend.dto.InventoryDTO;
import lk.ijse.backend.entity.Inventory;
import lk.ijse.backend.repo.InventoryRepo;
import lk.ijse.backend.service.InventoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(InventoryDTO inventoryDTO) {
        if(inventoryRepo.existsById(inventoryDTO.getId())){
            throw new RuntimeException("Inventory item already exists");
        }
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
    }

    @Override
    public List<InventoryDTO> getAll() {
        return modelMapper.map(inventoryRepo.findAll(), new TypeToken<List<InventoryDTO>>(){}.getType());
    }

    @Override
    public InventoryDTO getById(int id) {
        Optional<Inventory> optionalInventory = inventoryRepo.findById(id);
        if (optionalInventory.isPresent()) {
            return modelMapper.map(optionalInventory.get(), InventoryDTO.class);
        }
        throw new RuntimeException("Inventory item not found");
    }

    @Override
    public void update(InventoryDTO inventoryDTO) {
        if (inventoryRepo.existsById(inventoryDTO.getId())) {
            inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        }
        throw new RuntimeException("Inventory item does not exist");
    }

    @Override
    public void delete(int id) {
        if (!inventoryRepo.existsById(id)) {
            throw new RuntimeException("Inventory item with ID " + id + " does not exist.");
        }
        inventoryRepo.deleteById(id);
    }
}
