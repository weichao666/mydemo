package servicecomb.springmvcserverb.consumer;

import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.apache.servicecomb.swagger.invocation.context.ContextUtils;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import servicecomb.demo.bean.JAXBJob;
import servicecomb.demo.bean.JAXBPerson;
import servicecomb.demo.bean.Person;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

@Component
public class ConsumerHello {
  public RestTemplate restTemplate = RestTemplateBuilder.create();
  
  public String sayHi(String name) {
//    System.out.println("serverB try to visit serverC");
    String sayHiResult;
      sayHiResult = restTemplate.postForObject(
              "cse://springmvcc/springmvchelloc/sayhi?name=" + name, null, String.class);
    System.out.println("serverB sayHiResult: " + sayHiResult);
    return sayHiResult;
  }
  
  public String sayHello(Person person) {
    System.out.println("serverB try to visit serverC");
    String sayHelloResult = restTemplate.postForObject(
        "cse://springmvcc/springmvchelloc/sayhello", person, String.class);
    System.out.println("serverB sayHelloResult: " + sayHelloResult);
    return sayHelloResult;
  }

  public ResponseEntity<Person> testHeader(Person person) {
    HttpHeaders header = new HttpHeaders();
    header.add("fromRequestHeader", "requestHeader");
    header.add("fromHttpServletRequest", "httpServletRequest");
    HttpEntity<Person> requestEntity = new HttpEntity<>(person, header);
    ResponseEntity<Person> resEntity = restTemplate.exchange("cse://springmvcc/springmvchelloc/testHeader",
            HttpMethod.POST,
            requestEntity,
            Person.class);
    HttpHeaders headers = resEntity.getHeaders();
    System.out.println("content-type: " + headers.getContentType());
    System.out.println("content-disposition: " + headers.getFirst(HttpHeaders.CONTENT_DISPOSITION));
    System.out.println("custom: " + headers.getFirst("custom"));
    System.out.println(resEntity.getBody());
    return resEntity;
  }

  public String testAllParam(HttpServletRequest request) {
    System.out.println("pathinfo: " + request.getPathInfo());
    String path = request.getPathInfo().replace("springmvchellob", "springmvchelloc");
    System.out.println(request.getQueryString());
    HttpHeaders headers = new HttpHeaders();
    headers.add("header", "21");
    headers.add(HttpHeaders.COOKIE, "cook=23");

    Map<String, Integer> map = new HashMap<>();
    map.put("formdata", 22);
    HttpEntity entity = new HttpEntity(map, headers);
    ResponseEntity<String> resEntity = restTemplate.exchange("cse://springmvcc" + path + "?" + request.getQueryString(),
            HttpMethod.POST,
            entity,
            String.class);
    return resEntity.getBody();
  }

  public String testSessionStick(String name, int delaytime) {
    String result = restTemplate.getForObject(
            "cse://springmvcc/springmvchelloc/testSessionStick?name=" + name + "&delaytime=" + delaytime, String.class);
    System.out.println(result);
    return result;
  }

  public String testInstanceIsolation(Integer delaytime) {
    String result = restTemplate.getForObject(
        "cse://springmvcc/springmvchelloc/instanceisolation?delaytime=" + delaytime, String.class);
    return result;
    
  }
  
  public String testXmlProduceProcessor() {
    JAXBPerson person = new JAXBPerson("jake", 22, "it", "60kg");
    person.setJob(new JAXBJob("developer", "coding"));
    HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
    headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    HttpEntity<JAXBPerson> requestEntity = new HttpEntity<>(person, headers);
    ResponseEntity<JAXBPerson> resEntity = restTemplate.exchange("cse://springmvcc/springmvchelloc/appXml",
        HttpMethod.POST,
        requestEntity,
        JAXBPerson.class);
    return resEntity.getBody().toString();
  }

  public String testMap(String name) {
    List list = new ArrayList();
//    list.add(name);
//    list.add("tom");
    List result = restTemplate.getForObject(
            "cse://springmvcc/springmvchelloc/testList", List.class);
    return result.toString();
  }
}
