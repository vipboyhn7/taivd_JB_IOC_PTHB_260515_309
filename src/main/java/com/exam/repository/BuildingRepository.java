package com.exam.repository;

import com.exam.entity.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    boolean existsByBuildingName(String buildingName);

    boolean existsByBuildingNameAndIdNot(String buildingName, Long id);

    Page<Building> findByBuildingNameContainingIgnoreCase(String buildingName, Pageable pageable);

    Page<Building> findByStatus(Short status, Pageable pageable);

    Page<Building> findByStatusAndBuildingNameContainingIgnoreCase(Short status, String buildingName, Pageable pageable);

    @Query("""
            SELECT b FROM Building b
            WHERE (LOWER(:buildingName) IS NULL OR LOWER(b.buildingName) LIKE LOWER(CONCAT('%', :buildingName, '%'))) 
            AND (:status IS NULL OR b.status = :status)""")
    Page<Building> searchBuilding(String buildingName, Short status, Pageable pageable);
}
