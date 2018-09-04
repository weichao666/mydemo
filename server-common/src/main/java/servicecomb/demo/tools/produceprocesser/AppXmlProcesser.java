package servicecomb.demo.tools.produceprocesser;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.servicecomb.common.rest.codec.produce.ProduceProcessor;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.JavaType;

import servicecomb.demo.tools.JAXBUtils;

public class AppXmlProcesser implements ProduceProcessor {

  @Override
  public Object doDecodeResponse(InputStream input, JavaType type) throws Exception {
    System.out.println("&&&&&&&&doDecodeResponse&&&&&&&&");
    return JAXBUtils.convertToJavaBean(input, type);
  }

  @Override
  public void doEncodeResponse(OutputStream output, Object result) throws Exception {
    System.out.println("*********doEncodeResponse**********");
    output.write(JAXBUtils.convertToXml(result).getBytes());

  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return MediaType.APPLICATION_XML_VALUE;
  }

//  @Override
  public int getOrder() {
    // TODO Auto-generated method stub
    return 0;
  }

}
