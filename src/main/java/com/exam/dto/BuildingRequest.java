package com.exam.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingRequest {
    @NotBlank(message = "Tên tòa nhà không được để trống!")
    private String buildingName;

    @NotNull(message = "Diện tích tòa nhà không được để trống!")
    private Double buildingArea;

    @NotBlank(message = "Đơn vị diện tích không được để trống!")
    private String areaUnit;

    @NotNull(message = "Ngày bắt đầu không được để trống!")
    private LocalDate startDate;

    @NotNull(message = "Thời gian không được để trống!")

    private Integer time;

    @NotBlank(message = "Đơn vị thời gian không được để trống!")
    private String timeUnit;

    private String design;

    @NotBlank(message = "Nội dung không được để trống!")
    private String content;

    @Min(value = 0, message = "Trang thái chỉ có 0, 1, 2!")
    @Max(value = 2, message = "Trạng thái chỉ có 0, 1, 2!")
    private Short status;
}
