package servicecomb.springmvcserverb.impl;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import servicecomb.demo.bean.Person;
import servicecomb.demo.common.Hello;
import servicecomb.springmvcserverb.consumer.ConsumerHello;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestSchema(schemaId = "springmvcHello")
@RequestMapping(path = "/springmvchellob", produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloImpl implements Hello{
  @Autowired
  public ConsumerHello consumerHello;

  @Override
  @RequestMapping(path = "/sayhi", method = RequestMethod.POST)
  public String sayHi(@RequestParam(name = "name") String name) {
    String result = consumerHello.sayHi(name);
    return result;
  }

  @RequestMapping(path = "/testList", method = RequestMethod.GET)
  public String testList(@RequestParam("name") String name) {
    String result = consumerHello.testMap(name);
    return result;
  }

  @Override
  @RequestMapping(path = "/sayhello", method = RequestMethod.POST)
  public String sayHello(@RequestBody Person person) {
    String result = consumerHello.sayHello(person);
    return result;
  }

  @RequestMapping(path = "/testHeader", method = RequestMethod.POST)
  public ResponseEntity<Person> testHeader(@RequestBody Person person) {
    ResponseEntity<Person> resEntity = consumerHello.testHeader(person);
    return resEntity;
  }

  @RequestMapping(path = "/allparam/{name}", method = RequestMethod.POST)
  public String testAllParam(@PathVariable(name = "name") String name, @RequestParam("age") int age,
                             @RequestHeader("header") String header, @RequestPart("formdata") int formdata,
                             @CookieValue("cook") int cook,
                             HttpServletRequest request) {
    String result = "name=" + name + "; age=" + age + "; header=" + header + "; formdata=" + formdata
            + "; cookie=" + cook;
    System.out.println(result);
    String results = consumerHello.testAllParam(request);
    return results;
  }

  @RequestMapping(path = "/testSessionStick", method = RequestMethod.GET)
  public String testSessionStick(@RequestParam(name = "name") String name, @RequestParam("delaytime") int delaytime) {
    String result = consumerHello.testSessionStick(name, delaytime);
    return result;
  }

  @GetMapping(path = "/testinstanceisolation")
  public String testInstanceIsolation(@RequestParam(name = "delaytime") Integer delaytime, 
      @RequestParam(name = "percent") Integer percent, @RequestParam(name = "time") Integer time) {
    String result = "";

    for (int i = 0; i < percent; i++) {
      try {
        result = consumerHello.testInstanceIsolation(delaytime);
        System.out.println(result);
      } catch (Exception e) {
        System.out.println("error: time-out and fallback disabled.");
      }
      
    }
    for (int i = 0; i < time-percent; i++) {
      result = consumerHello.testInstanceIsolation(0);
      System.out.println(result);
    }
    return "invoke finished";
  }
  
  @GetMapping(path = "/appXml")
  public String testXmlProcessor() {
    String result = consumerHello.testXmlProduceProcessor();
    return result;
  }
}
