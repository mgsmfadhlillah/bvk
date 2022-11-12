package com.bvk.springjwt.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class GeneralRequest {

  @Column(name = "email")
  private String email;

  @NotEmpty
  @NotNull
  @Column(name = "data_type")
  @JsonProperty(value = "data_type")
  private String data_type;

  @NotNull
  @Column(name = "data_request")
  @JsonProperty(value = "data_request")
  private String data_request;
}
