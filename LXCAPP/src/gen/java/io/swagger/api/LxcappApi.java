package io.swagger.api;

import io.swagger.api.factories.LxcappApiServiceFactory;

import io.swagger.annotations.ApiParam;

import io.swagger.model.ResponseBO;

import org.wso2.msf4j.formparam.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/lxcapp")
@Consumes({ "application/json" })
@Produces({ "application/json", "application/text", "application/pdf", "text/plain; charset=utf-8" })
@io.swagger.annotations.Api(description = "the lxcapp API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-04-21T05:52:25.698Z")
public class LxcappApi  {
   private final LxcappApiService delegate = LxcappApiServiceFactory.getLxcappApi();

    @GET
    @Path("/Contriner/{Contrinerid}/config")
    @Consumes({ "application/json" })
    @Produces({ "application/json", "application/text", "application/pdf", "text/plain; charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "查询APPStroe", notes = "默认没有参数", response = ResponseBO.class, tags={ "application", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = ResponseBO.class) })
    public Response lxcappContrinerContrineridConfigGet(@ApiParam(value = "Contrinerid",required=true) @PathParam("Contrinerid") String contrinerid
)
    throws NotFoundException {
        return delegate.lxcappContrinerContrineridConfigGet(contrinerid);
    }
    @POST
    @Path("/Contriner/{Contrinerid}/config")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json", "application/text", "application/pdf", "text/plain; charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "upload package", notes = "Pakage app Load up down;", response = ResponseBO.class, tags={ "application", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = ResponseBO.class) })
    public Response lxcappContrinerContrineridConfigPost(@ApiParam(value = "参数", required=true)@FormDataParam("jsonconf")  String jsonconf
,@ApiParam(value = "Contrinerid",required=true) @PathParam("Contrinerid") String contrinerid
)
    throws NotFoundException {
        return delegate.lxcappContrinerContrineridConfigPost(jsonconf,contrinerid);
    }
    @DELETE
    @Path("/Contriner/{Contrinerid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json", "application/text", "application/pdf", "text/plain; charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "del apppackage", notes = "根据App_ID, 进行卸载,AppPackage", response = ResponseBO.class, tags={ "application", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = ResponseBO.class) })
    public Response lxcappContrinerContrineridDelete(@ApiParam(value = "通过已经安装或运行的packid来卸载应用",required=true) @PathParam("Contrinerid") String contrinerid
)
    throws NotFoundException {
        return delegate.lxcappContrinerContrineridDelete(contrinerid);
    }
    @GET
    @Path("/Contriner/list")
    @Consumes({ "application/json" })
    @Produces({ "application/json", "application/text", "application/pdf", "text/plain; charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "search packages", notes = "默认没有参数", response = ResponseBO.class, tags={ "application", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = ResponseBO.class) })
    public Response lxcappContrinerListGet()
    throws NotFoundException {
        return delegate.lxcappContrinerListGet();
    }
    @POST
    @Path("/Contriner/list")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json", "application/text", "application/pdf", "text/plain; charset=utf-8" })
    @io.swagger.annotations.ApiOperation(value = "upload package", notes = "Pakage app Load up down;", response = ResponseBO.class, tags={ "application", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = ResponseBO.class) })
    public Response lxcappContrinerListPost(@ApiParam(value = "参数", required=true)@FormDataParam("jsonconf")  String jsonconf
)
    throws NotFoundException {
        return delegate.lxcappContrinerListPost(jsonconf);
    }
}
