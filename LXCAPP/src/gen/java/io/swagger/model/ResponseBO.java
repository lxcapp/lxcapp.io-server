package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ResponseBO
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-04-18T02:51:42.765Z")
public class ResponseBO   {
  @JsonProperty("CompleteCode")
  private Integer completeCode = null;

  @JsonProperty("ReasonCode")
  private String reasonCode = null;

  @JsonProperty("ReasonMessage")
  private String reasonMessage = null;

  @JsonProperty("Data")
  private Object data = null;

  public ResponseBO completeCode(Integer completeCode) {
    this.completeCode = completeCode;
    return this;
  }

   /**
   * 完成码，0表示请求过程正常，-1表示请求过程异常
   * @return completeCode
  **/
  @ApiModelProperty(value = "完成码，0表示请求过程正常，-1表示请求过程异常")
  public Integer getCompleteCode() {
    return completeCode;
  }

  public void setCompleteCode(Integer completeCode) {
    this.completeCode = completeCode;
  }

  public ResponseBO reasonCode(String reasonCode) {
    this.reasonCode = reasonCode;
    return this;
  }

   /**
   * 异常原因的代码
   * @return reasonCode
  **/
  @ApiModelProperty(value = "异常原因的代码")
  public String getReasonCode() {
    return reasonCode;
  }

  public void setReasonCode(String reasonCode) {
    this.reasonCode = reasonCode;
  }

  public ResponseBO reasonMessage(String reasonMessage) {
    this.reasonMessage = reasonMessage;
    return this;
  }

   /**
   * 异常原因具体信息
   * @return reasonMessage
  **/
  @ApiModelProperty(value = "异常原因具体信息")
  public String getReasonMessage() {
    return reasonMessage;
  }

  public void setReasonMessage(String reasonMessage) {
    this.reasonMessage = reasonMessage;
  }

  public ResponseBO data(Object data) {
    this.data = data;
    return this;
  }

   /**
   * 返回值的具体信息
   * @return data
  **/
  @ApiModelProperty(value = "返回值的具体信息")
  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseBO responseBO = (ResponseBO) o;
    return Objects.equals(this.completeCode, responseBO.completeCode) &&
        Objects.equals(this.reasonCode, responseBO.reasonCode) &&
        Objects.equals(this.reasonMessage, responseBO.reasonMessage) &&
        Objects.equals(this.data, responseBO.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(completeCode, reasonCode, reasonMessage, data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseBO {\n");
    
    sb.append("    completeCode: ").append(toIndentedString(completeCode)).append("\n");
    sb.append("    reasonCode: ").append(toIndentedString(reasonCode)).append("\n");
    sb.append("    reasonMessage: ").append(toIndentedString(reasonMessage)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

