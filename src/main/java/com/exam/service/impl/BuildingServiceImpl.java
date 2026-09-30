package com.exam.service.impl;

import com.exam.dto.BuildingRequest;
import com.exam.entity.Building;
import com.exam.exception.AppException;
import com.exam.repository.BuildingRepository;
import com.exam.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;

    private String fileUploadDir;

    @Override
    @Transactional(readOnly = true)
    public Page<Building> getAllBuildings(Pageable pageable) {
        return buildingRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Building createBuilding(BuildingRequest request) {

        if (buildingRepository.existsByBuildingName(request.getBuildingName())) {
            throw new AppException("Tên tòa nhà đã tồn tại!", HttpStatus.BAD_REQUEST);
        }

        Building building = Building.builder().buildingName(request.getBuildingName()).buildingArea(request.getBuildingArea()).areaUnit(request.getAreaUnit()).startDate(request.getStartDate()).time(request.getTime()).timeUnit(request.getTimeUnit()).design(request.getDesign()).content(request.getContent()).status((short) 1).build();

        if (building.getDesign() == null) {
            building.setDesign("default-design.png");
        }
        return buildingRepository.save(building);
    }

    @Override
    @Transactional
    public Building updateBuilding(Long id, BuildingRequest request) {
        Building building = buildingRepository.findById(id).orElseThrow(() -> new AppException("Không tìm thấy tòa nhà với id: " + id, HttpStatus.NOT_FOUND));

        if (buildingRepository.existsByBuildingNameAndIdNot(request.getBuildingName(), id)) {
            throw new AppException("Tên tòa nhà đã bị trùng với 1 tòa khác!", HttpStatus.BAD_REQUEST);
        }

        building.setBuildingName(request.getBuildingName());
        building.setBuildingArea(request.getBuildingArea());
        building.setAreaUnit(request.getAreaUnit());
        building.setStartDate(request.getStartDate());
        building.setTime(request.getTime());
        building.setTimeUnit(request.getTimeUnit());
        building.setDesign(request.getDesign());
        building.setContent(request.getContent());
        building.setStatus(request.getStatus());


        return buildingRepository.save(building);
    }

    @Override
    public Building changeStatus(Long id, Short status) {
        Building building = buildingRepository.findById(id).orElseThrow(
                () -> new AppException("Không tìm thấy tòa nhà với id: " + id, HttpStatus.NOT_FOUND)
        );

        if (building.getStatus() != 2) {
            building.setStatus(status);
        } else {
            throw new AppException("Tòa nhà đã hoàn thành. Không thể thay đổi trạng thái!", HttpStatus.BAD_REQUEST);
        }

        return buildingRepository.save(building);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Building> searchBuilding(String buildingName, Short status, Pageable pageable) {
        return buildingRepository.searchBuilding(buildingName, status, pageable);
    }
}
