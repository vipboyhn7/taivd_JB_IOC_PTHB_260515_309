package com.exam.controller;

import com.exam.common.reponse.ApiResponse;
import com.exam.dto.BuildingRequest;
import com.exam.dto.ChangeStatus;
import com.exam.entity.Building;
import com.exam.service.BuildingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buildings")
@RequiredArgsConstructor
public class BuildingController {
    private final BuildingService buildingService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Building>>> getAllBuildings(
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(ApiResponse.success(buildingService.getAllBuildings(pageable), "Lấy danh sách toà nhà thành công!", HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Building>> createBuilding(@Valid @RequestBody BuildingRequest buildingRequest) {
        Building building = buildingService.createBuilding(buildingRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(building, "Tạo toà nhà thành công!", HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Building>> updateBuilding(
            @PathVariable Long id,
            @Valid @RequestBody BuildingRequest buildingRequest) {
        Building building = buildingService.updateBuilding(id, buildingRequest);
        return ResponseEntity.ok(ApiResponse.success(building, "Cập nhật toà nhà thành công!", HttpStatus.OK));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Building>> changeStatus(@PathVariable Long id, @Valid @RequestBody ChangeStatus rq) {
        Building building = buildingService.changeStatus(id, rq.getStatus());
        return ResponseEntity.ok(ApiResponse.success(building, "Thay đổi trạng thái toà nhà thành công!", HttpStatus.OK));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<Building>>> searchBuilding(
            @RequestParam(required = false) String buildingName,
            @RequestParam(required = false) Short status,
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Building> buildings = buildingService.searchBuilding(buildingName, status, pageable);
        return ResponseEntity.ok(ApiResponse.success(buildings, "Tìm kiếm toà nhà thành công!", HttpStatus.OK));
    }
}
