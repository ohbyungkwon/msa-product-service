package com.msa.productmicroservice.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseComDto {
    private String resultMsg;
    private Object resultObj;
}
