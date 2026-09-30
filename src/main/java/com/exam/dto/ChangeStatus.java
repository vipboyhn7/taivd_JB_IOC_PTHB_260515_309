package com.exam.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeStatus {
    @Min(value = 0, message = "Trang thái chỉ có 0, 1, 2!")
    @Max(value = 2, message = "Trạng thái chỉ có 0, 1, 2!")
    private Short status;
}
