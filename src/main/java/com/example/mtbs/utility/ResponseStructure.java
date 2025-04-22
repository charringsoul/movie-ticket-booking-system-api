package com.example.mtbs.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@AllArgsConstructor
@Data
@Builder
public class ResponseStructure<T>{

    private int status;
    private T data;
    private String message;


}
