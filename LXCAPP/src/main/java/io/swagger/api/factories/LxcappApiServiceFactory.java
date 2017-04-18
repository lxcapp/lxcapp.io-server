package io.swagger.api.factories;

import io.swagger.api.LxcappApiService;
import io.swagger.api.impl.LxcappApiServiceImpl;

public class LxcappApiServiceFactory {
    private final static LxcappApiService service = new LxcappApiServiceImpl();

    public static LxcappApiService getLxcappApi() {
        return service;
    }
}
