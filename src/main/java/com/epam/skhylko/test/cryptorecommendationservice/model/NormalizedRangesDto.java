package com.epam.skhylko.test.cryptorecommendationservice.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class NormalizedRangesDto {
    @ApiModelProperty(value = "List of normalized price range")
    private List<NormalizedRangeDto> normalizedRangesDto = new ArrayList<>();

    public List<NormalizedRangeDto> getNormalizedRangesDto() {
        return normalizedRangesDto;
    }

    public void setNormalizedRangesDto(List<NormalizedRangeDto> normalizedRangesDto) {
        this.normalizedRangesDto = normalizedRangesDto;
    }
}
