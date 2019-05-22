package com.ruoyi.common.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.domain.R;

@RestController
public class GlobalErrorController implements ErrorController
{
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public R handleError()
    {
        return R.error(404, "request resource not found.");
    }

    @Override
    public String getErrorPath()
    {
        return ERROR_PATH;
    }
}