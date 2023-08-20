package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransferDto(@JsonProperty(value = "id_user_from") String idFrom,
                          @JsonProperty(value = "id_user_to") String idTo,
                          BigDecimal amount) {


}
