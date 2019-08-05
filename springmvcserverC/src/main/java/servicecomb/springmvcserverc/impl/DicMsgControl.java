package servicecomb.springmvcserverc.impl;

import java.util.concurrent.CompletableFuture;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestSchema(schemaId = "DicMsgControl")
@RequestMapping(path = "/rest")
public class DicMsgControl {
  @RequestMapping(value = {"/sms/test2"}, method = RequestMethod.GET)
  public CompletableFuture<ResponseEntity<Object>> sendSms3() throws Exception {

    CompletableFuture<ResponseEntity<Object>> completableFuture = new CompletableFuture<>();

    completableFuture.complete(ResponseEntity.badRequest().body("Error"));

    return completableFuture;

  }
}
