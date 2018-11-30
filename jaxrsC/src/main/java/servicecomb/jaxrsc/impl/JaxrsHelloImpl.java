package servicecomb.jaxrsc.impl;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import servicecomb.demo.bean.Person;
import servicecomb.demo.bean.TestBeanParam;
import servicecomb.demo.common.Hello;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@RestSchema(schemaId = "jaxrsHello")
@Path("/jaxrsc")
public class JaxrsHelloImpl implements Hello {
    @Path("/sayhi")
    @GET
    @Override
    public String sayHi(String name) {
        return "hi";
    }

    @Path("/sayhello")
    @POST
    @Override
    public String sayHello(Person person) {
        return "hello";
    }

    @Path("/testBeanParam")
    @GET
    public String testBeanParam(@BeanParam TestBeanParam param) {
        return param.toString();
    }
}
