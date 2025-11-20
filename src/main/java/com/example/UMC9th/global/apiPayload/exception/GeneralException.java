package com.example.UMC9th.global.apiPayload.exception;

import com.example.UMC9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class GeneralException extends RuntimeException {

    public final BaseErrorCode code;
}
