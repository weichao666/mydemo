package servicecomb.springmvcserverb.consumer;

import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import servicecomb.demo.bean.JAXBJob;
import servicecomb.demo.bean.JAXBPerson;
import servicecomb.demo.bean.Person;

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
}
