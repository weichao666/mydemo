package servicecomb.springmvcserverc.impl;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import servicecomb.demo.bean.JAXBPerson;
import servicecomb.demo.bean.Person;
import servicecomb.demo.common.Hello;

@RestSchema(schemaId = "springmvcHello")
@RequestMapping(path = "/springmvchelloc", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpringMvcHelloImpl implements Hello {
  Logger LOGGER = LoggerFactory.getLogger(SpringMvcHelloImpl.class);

  @Override
  @RequestMapping(path = "/sayhi", method = RequestMethod.POST)
  public String sayHi(@RequestParam(name = "name") String name) {
    String result = "**********visit success, this is instance 1";
    return result;
  }

  @Override
  @RequestMapping(path = "/sayhello", method = RequestMethod.POST)
  public String sayHello(@RequestBody Person person) {
    return person.getName();
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
  public JAXBPerson appXml(@RequestBody String str) {
    JAXBPerson person = new JAXBPerson("jake", 20, "developer", "60kg");
    return person;
  }
}
