package servicecomb.springmvcserverc.impl;

import io.swagger.annotations.ResponseHeader;
import io.vertx.core.cli.annotations.DefaultValue;

import org.apache.servicecomb.core.Const;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.core.invocation.InvocationFactory;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.swagger.extend.annotations.ResponseHeaders;
import org.apache.servicecomb.swagger.invocation.context.ContextUtils;
import org.apache.servicecomb.swagger.invocation.context.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import servicecomb.demo.bean.JAXBPerson;
import servicecomb.demo.bean.LombokPerson;
import servicecomb.demo.bean.Person;
import servicecomb.demo.common.Hello;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestSchema(schemaId = "springmvcHello")
@RequestMapping(path = "/springmvchelloc", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpringMvcHelloImpl implements Hello {
  Logger LOGGER = LoggerFactory.getLogger(SpringMvcHelloImpl.class);

  @Override
  @RequestMapping(path = "/sayhi", method = RequestMethod.POST)
  public String sayHi(@RequestParam("name") String name) {
    System.out.println("instance1");
    return "instance1:" + name;
  }

  @RequestMapping(path = "/testList", method = RequestMethod.GET)
  public String testList(@RequestParam(name = "array") String[] array,
      @RequestParam(name = "list", defaultValue = "list", required = true) List<String> list,
      @RequestParam(name = "str", defaultValue = "str", required = true) String str,
      String string) {
    System.out.println("array:" + Arrays.toString(array));
    System.out.println("list:" + list);
    System.out.println("string:" + string);
    return Arrays.toString(array) + "***" + list + "***" + string;
  }

  @Override
  @RequestMapping(path = "/sayhello", method = RequestMethod.POST)
  public String sayHello(@RequestBody Person person) {
    return person.getName();
  }

  @RequestMapping(path = "/testHeader", method = RequestMethod.POST)
  public ResponseEntity<Person> testHeader(@RequestBody Person person,
      @RequestHeader(value = "requestHeader1", defaultValue = "default1") String requestHeader1,
      @RequestHeader(value = "requestHeader2", defaultValue = "default2") String[] requestHeader2,
      @RequestHeader(value = "requestHeader3", defaultValue = "default3") List<String> requestHeader3,
      HttpServletRequest request) {
    // get header from HttpServletRequest
    System.out.println("httpServletRequest1 = " + request.getHeader("httpServletRequest1"));
    System.out.println("httpServletRequest2 = " + Collections.list(request.getHeaders("httpServletRequest2")));
    // get header from requestHeader
    System.out.println("requestHeader1 = " + requestHeader1);
    System.out.println("requestHeader2 = " + Arrays.toString(requestHeader2));
    System.out.println("requestHeader3 = " + requestHeader3);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;")
        .header("custom", "sayhello")
        .body(person);
  }

  //  @ResponseHeaders({@ResponseHeader(name = "h1", response = String.class),
//  @ResponseHeader(name = "h2", response = String.class)})
  @RequestMapping(path = "/testHeaderResponse", method = RequestMethod.POST)
  public ResponseEntity<Date> responseEntity(InvocationContext c1, @RequestAttribute("date") Date date) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("h1", "h1v " + c1.getContext().get(Const.SRC_MICROSERVICE));
    InvocationContext c2 = ContextUtils.getInvocationContext();
    headers.add("h2", "h2v " + c2.getContext().get(Const.SRC_MICROSERVICE));
    return new ResponseEntity<>(date, headers, HttpStatus.ACCEPTED);
  }

  @RequestMapping(path = "/formdata", method = RequestMethod.POST)
  public String testFormData(@RequestPart(value = "part", required = false) List<String> part, @RequestAttribute("attr") String attr) {
    System.out.println("part = " + part);
    System.out.println("attr = " + attr);
    return "part:" + part + ",attr:" + attr;
  }

  @RequestMapping(path = "/cookies", method = RequestMethod.GET)
  public String testCookies(@CookieValue(name = "cook1", defaultValue = "default", required = true) String cook1,
      @CookieValue(name = "cook2", defaultValue = "defaultlist", required = true) List<String> cook2) {
    System.out.println("cook1:" + cook1);
    System.out.println("cook2:" + cook2);
    return "cook1:" + cook1 + ", cook2:" + cook2;
  }

  @RequestMapping(path = "/allparam/{name}", method = RequestMethod.POST)
  public String testAllParam(@PathVariable(name = "name") String name, @RequestParam("age") int age,
      @RequestHeader("header") String header, @RequestPart("formdata") int formdata,
      @RequestBody Person person,
      @CookieValue("cook") int cook) {
    String result = "name=" + name + "; age=" + age + "; header=" + header + "; formdata=" + formdata
        + "; cookie=" + cook;
    return result;
  }

  @RequestMapping(path = "/testSessionStick", method = RequestMethod.GET)
  public String testSessionStick(@RequestParam(name = "name") String name, @RequestParam("delaytime") int delaytime) {
    try {
      Thread.sleep(delaytime * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    String result = "visit success, result=" + name + ", delaytime=" + delaytime + ", this is instance1";
    System.out.println(result);
    return result;
  }

  @GetMapping(path = "/instanceisolation")
  public String testInstanceIsolation(@RequestParam(name = "delaytime") Integer delaytime) {

    try {
      Thread.sleep(delaytime * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    String returnresult = "**********visit success, this is instance 1, delaytime=" + delaytime;
    System.out.println(returnresult);
    return returnresult;
  }

  @RequestMapping(path = "/appXml", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
  public JAXBPerson appXml(@RequestBody JAXBPerson person) {
//    JAXBPerson person = new JAXBPerson("jake", 20, "developer", "60kg");
    return person;
  }

  @RequestMapping(path = "/lombok", method = RequestMethod.POST)
  public LombokPerson lombok(@RequestBody LombokPerson person) {
    return person;
  }
}
