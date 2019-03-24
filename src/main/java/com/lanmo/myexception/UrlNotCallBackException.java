package com.lanmo.myexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "网络原因")
public class UrlNotCallBackException extends RuntimeException {
}
