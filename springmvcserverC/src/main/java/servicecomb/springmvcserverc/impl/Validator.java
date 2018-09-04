package servicecomb.springmvcserverc.impl;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.swagger.invocation.context.ContextUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import servicecomb.demo.bean.ValidatorPerson;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.PathParam;

@RestSchema(schemaId = "validator")
@RequestMapping(path = "/validator", produces = MediaType.APPLICATION_JSON_VALUE)
public class Validator {
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public int add(@RequestPart("a") int a, @Min(20) @RequestPart("b") int b) {
        return a + b;
    }

    @RequestMapping(path = "/sayhi/{name}", method = RequestMethod.PUT)
    public String sayHi(@Length(min = 3) @PathParam("name") String name) {
        ContextUtils.getInvocationContext().setStatus(202);
        return name + " sayhi";
    }

    @RequestMapping(path = "/sayhello", method = RequestMethod.POST)
    public ValidatorPerson sayHello(@Valid ValidatorPerson person) {
        person.setName("hello " + person.getName());
        person.setAge(person.getAge());
        return person;
    }
}
