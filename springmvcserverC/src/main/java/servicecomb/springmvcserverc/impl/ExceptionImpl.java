package servicecomb.springmvcserverc.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.servicecomb.core.Const;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.swagger.invocation.context.ContextUtils;
import org.apache.servicecomb.swagger.invocation.context.InvocationContext;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import servicecomb.demo.bean.JAXBPerson;
import servicecomb.demo.bean.Person;
import servicecomb.demo.common.Hello;

@RestSchema(schemaId = "springmvcHello2")
@RequestMapping(path = "/springmvchelloc", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionImpl {
  Logger LOGGER = LoggerFactory.getLogger(ExceptionImpl.class);

  @RequestMapping(path = "/testException", method = RequestMethod.GET)
  public String testException(int code) {
    String strCode = String.valueOf(code);
    switch (code) {
      case 200:
        return strCode;
      case 456:
        throw new InvocationException(code, strCode, strCode + " error");
      case 556:
        throw new InvocationException(code, strCode, Arrays.asList(strCode + " error"));
      case 557:
        throw new InvocationException(code, strCode, Arrays.asList(Arrays.asList(strCode + " error")));
      default:
        break;
    }
    return "not expected";
  }
}
