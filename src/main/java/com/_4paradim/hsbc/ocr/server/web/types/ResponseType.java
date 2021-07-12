package com._4paradim.hsbc.ocr.server.web.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import okhttp3.Response;

@AllArgsConstructor
public enum ResponseType {

    SUCCESS(201,"Successful","JSON String of process item.Refer to following sample response"),
    BAD_REQUEST(400,"Failed,Bad Request","Error message"),
    UNAUTHORIZED(401,"Failed,Unauthorized Access","Error message"),
    PLATFROM_ERROR(418,"Failed,Common platfrom error","Error message"),
    SERVER_ERRROR(500,"Failed,Internal Server Error","Error,message");


    @Getter
    private int code;
    @Getter
    private String message;
    @Getter
    private String desc;
}
