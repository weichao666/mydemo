package servicecomb.springmvcserverc.impl;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import servicecomb.demo.bean.JAXBPerson;
import servicecomb.demo.bean.Person;
import servicecomb.demo.common.Hello;

import javax.servlet.http.HttpServletRequest;

@RestSchema(schemaId = "springmvcHello")
@RequestMapping(path = "/springmvchelloc", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpringMvcHelloImpl implements Hello {
  Logger LOGGER = LoggerFactory.getLogger(SpringMvcHelloImpl.class);

  @Override
  @RequestMapping(path = "/sayhi", method = RequestMethod.POST)
  public String sayHi(String name) {
    return "name:" + name;
  }

  @Override
  @RequestMapping(path = "/sayhello", method = RequestMethod.POST)
  public String sayHello(@RequestBody Person person) {
    return person.getName();
  }

  @RequestMapping(path = "/testHeader", method = RequestMethod.POST)
  public ResponseEntity<Person> testHeader(@RequestBody Person person, @RequestHeader("fromRequestHeader") String fromRequestHeader, HttpServletRequest request) {
    // get header from header
    System.out.println("fromRequestHeader = " + request.getHeader("fromHttpServletRequest"));
    // get header from HttpServletRequest
    System.out.println("fromRequestHeader = " + fromRequestHeader);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;")
            .header("custom", "sayhello")
            .body(person);
  }

  @RequestMapping(path = "/allparam/{name}", method = RequestMethod.POST)
  public String testAllParam(@PathVariable(name = "name") String name, @RequestParam("age") int age,
                             @RequestHeader("header") int header, @RequestPart("formdata") int formdata,
                             @CookieValue("cook") int cook) {
    String result = "name=" + name + "; age=" + age + "; header=" + header + "; formdata=" + formdata
            + "; cookie=" + cook;
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
