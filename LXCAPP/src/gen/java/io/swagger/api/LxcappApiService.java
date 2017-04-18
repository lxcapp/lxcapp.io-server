package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.wso2.msf4j.formparam.FormDataParam;
import org.wso2.msf4j.formparam.FileInfo;

import java.io.File;
import io.swagger.model.ResponseBO;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-04-18T02:51:42.765Z")
public abstract class LxcappApiService {
    public abstract Response lxcappContrinerContrineridConfigGet(String contrinerid
 ) throws NotFoundException;
    public abstract Response lxcappContrinerContrineridConfigPost(String jsonconf
 ,String contrinerid
 ) throws NotFoundException;
    public abstract Response lxcappContrinerContrineridDelete(String contrinerid
 ) throws NotFoundException;
    public abstract Response lxcappContrinerListGet() throws NotFoundException;
    public abstract Response lxcappContrinerListPost(String jsonconf
 ,InputStream fileInputStream, FileInfo fileDetail
 ) throws NotFoundException;
}
