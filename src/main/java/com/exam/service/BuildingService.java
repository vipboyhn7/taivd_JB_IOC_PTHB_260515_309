package com.exam.service;

import com.exam.dto.BuildingRequest;
import com.exam.entity.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuildingService {
    Page<Building> getAllBuildings(Pageable pageable);

    Building createBuilding(BuildingRequest request);

    Building updateBuilding(Long id, BuildingRequest request);

    Building changeStatus(Long id, Short status);

    Page<Building> searchBuilding(String buildingName, Short status, Pageable pageable);
}
