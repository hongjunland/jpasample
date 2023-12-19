package com.example.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class ApiResponse {
    protected final int status;
    protected final String message;
}
