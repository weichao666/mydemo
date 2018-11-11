package servicecomb.springmvcserverc.impl;

import io.swagger.annotations.ResponseHeader;
import org.apache.servicecomb.core.Const;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.core.invocation.InvocationFactory;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.swagger.extend.annotations.ResponseHeaders;
import org.apache.servicecomb.swagger.invocation.context.ContextUtils;
import org.apache.servicecomb.swagger.invocation.context.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import servicecomb.demo.bean.JAXBPerson;
import servicecomb.demo.bean.Person;
import servicecomb.demo.common.Hello;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestSchema(schemaId = "springmvcHello")
@RequestMapping(path = "/springmvchelloc", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpringMvcHelloImpl implements Hello {
  Logger LOGGER = LoggerFactory.getLogger(SpringMvcHelloImpl.class);

  @Override
  @RequestMapping(path = "/sayhi", method = RequestMethod.POST)
  public String sayHi(String name) {
    System.out.println("instance1");
    return "instance1:" + name;
  }

  @RequestMapping(path = "/testList", method = RequestMethod.GET)
  public List<String> testList(@RequestParam(name = "name") List<String> name) {
    return name;
  }

  @Override
  @RequestMapping(path = "/sayhello", method = RequestMethod.POST)
  public String sayHello(@RequestBody Person person) {
    return person.getName();
  }

  @RequestMapping(path = "/testHeader", method = RequestMethod.POST)
  public ResponseEntity<Person> testHeader(@RequestBody Person person,
                                           @RequestHeader(value = "requestHeader") String fromRequestHeader,
                                           HttpServletRequest request) {
    // get header from header
    System.out.println("fromRequestHeader = " + request.getHeader("fromHttpServletRequest"));
    // get header from HttpServletRequest
    System.out.println("fromRequestHeader = " + fromRequestHeader);
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

  @RequestMapping(path = "/allparam/{name}", method = RequestMethod.POST)
  public String testAllParam(@PathVariable(name = "name") String name, @RequestParam("age") int age,
                             @RequestHeader("header") String header, @RequestPart("formdata") int formdata,
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
    String result = "visit success, result=" + name+ ", delaytime=" + delaytime + ", this is instance1";
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
}
