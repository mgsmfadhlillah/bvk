package com.bvk.springjwt.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
  @NotBlank
  private Integer status;
  @NotBlank
  private String message;
  @NotBlank
  private Map<String, Object> result;
}
