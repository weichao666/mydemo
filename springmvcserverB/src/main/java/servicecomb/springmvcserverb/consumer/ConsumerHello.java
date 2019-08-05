package servicecomb.springmvcserverb.consumer;

import org.apache.commons.io.FileUtils;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.provider.springmvc.reference.CseHttpEntity;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.apache.servicecomb.serviceregistry.RegistryUtils;
import org.apache.servicecomb.swagger.invocation.context.ContextUtils;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import servicecomb.demo.bean.JAXBJob;
import servicecomb.demo.bean.JAXBPerson;
import servicecomb.demo.bean.Person;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.*;

import com.google.common.base.Charsets;

@Component
public class ConsumerHello {
  public RestTemplate restTemplate = RestTemplateBuilder.create();

  public String sayHi(String name) {
    System.out.println("serverB try to visit serverC");
    String sayHiResult = "";
//     ContextUtils.getInvocationContext().addLocalContext("scb-endpoint", "rest://127.0.0.1:8090");
      String result = restTemplate.postForObject(
              "cse://springmvcc/springmvchelloc/sayhi?name=" + name, null, String.class);
//    HttpHeaders headers = new HttpHeaders();
//    HttpEntity entity = new HttpEntity(headers);
//    try {
//      ResponseEntity<String> response = restTemplate.postForEntity("cse://springmvcc/springmvchelloc/sayhi",
//          entity,
//          String.class);
//    } catch (Exception e) {
//      System.out.println("**************************");
//      e.printStackTrace();
//      System.out.println("#########################");
//      System.out.println("&&&&&&" + e.getMessage() + "$$$$$$$$$$$$$$$$");
//    }
    System.out.println("Result: " + result);
    return result;
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

  public ResponseEntity<Date> testHeaderResponse() {
    Map<String, Object> body = new HashMap<>();
    Date date = new Date();
    body.put("date", date);

    CseHttpEntity<Map<String, Object>> httpEntity = new CseHttpEntity<>(body);
    httpEntity.addContext("contextKey", "contextValue");

    String srcName = RegistryUtils.getMicroservice().getServiceName();

    ResponseEntity<Date> responseEntity =
        restTemplate.exchange("cse://springmvcc/springmvchelloc/testHeaderResponse",
            HttpMethod.POST, httpEntity, Date.class);
    Date date1 = responseEntity.getBody();
    System.out.println(date1);
    String h1Value = responseEntity.getHeaders().getFirst("h1");
    System.out.println("except: h1v " + srcName + " real: " + h1Value);
    String h2Value = responseEntity.getHeaders().getFirst("h2");
    System.out.println("except: h2v " + srcName + " real: " + h2Value);
    HttpStatus status = responseEntity.getStatusCode();
    System.out.println("exceptStatus: 202" + " realStatus: " + status.toString());

    return responseEntity;
  }

  public String testCookies() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity entity = new HttpEntity(headers);
    ResponseEntity<String> responseEntity = restTemplate.exchange("cse://springmvcc/springmvchelloc/cookies",
        HttpMethod.GET,
        entity,
        String.class
    );
    System.out.println(responseEntity.getBody());
    headers.add(HttpHeaders.COOKIE, "cook1=cook1");
    entity = new HttpEntity(headers);
    responseEntity = restTemplate.exchange("cse://springmvcc/springmvchelloc/cookies",
        HttpMethod.GET,
        entity,
        String.class
    );
    System.out.println(responseEntity.getBody());
    return responseEntity.getBody();
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

  public String testException(int code) {
    System.out.println("start");
    String result = restTemplate.getForObject("cse://springmvcc/springmvchelloc/testException?code=200", String.class);
    System.out.println("success result:" + result);
    try {
      restTemplate.getForObject("cse://springmvcc/springmvchelloc/testException?code=456", String.class);
    } catch (InvocationException e) {
      System.out.println("456 error body:" + e.getErrorData());
    }
    try {
      restTemplate.getForObject("cse://springmvcc/springmvchelloc/testException?code=556", String.class);
    } catch (InvocationException e) {
      System.out.println("[556 error] body:" + e.getErrorData());
    }
    try {
      restTemplate.getForObject("cse://springmvcc/springmvchelloc/testException?code=557", String.class);
    } catch (InvocationException e) {
      System.out.println("[557 error] body:" + e.getErrorData());
    }
    return "done";
  }

  public String fromPart_rt() {
    String file1Content = "default Hello World";
    String file2Content = "fromValue Hello World";
    String file3Content = "fromName Hello World";
    File file1 = null;
    File file2 = null;
    File file3 = null;
    try {
      file1 = File.createTempFile("upload1", ".txt");
      file2 = File.createTempFile("upload2", "txt");
      file3 = File.createTempFile("upload3", "txt");
      FileUtils.writeStringToFile(file1, file1Content, Charsets.UTF_8);
      FileUtils.writeStringToFile(file2, file2Content, Charsets.UTF_8);
      FileUtils.writeStringToFile(file3, file3Content, Charsets.UTF_8);
    } catch (IOException e) {
      System.out.println("there's no exception here, " + "here's the exception");
    }

    MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
    map.add("input", new FileSystemResource(file1));
    map.add("input2", new FileSystemResource(file2));
    map.add("input3", new FileSystemResource(file3));

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    String result = restTemplate.postForObject("cse://springmvcc/springmvchelloc/fromPart",
            new HttpEntity<>(map, headers),
            String.class);
    Assert.hasText("default Hello World,fromValue Hello World,fromName Hello World", result);
    return result;
  }
}
