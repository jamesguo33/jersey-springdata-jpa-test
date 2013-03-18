package sample.op.eastblue.shellstown.rest;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.ContextLoaderListener;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerException;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import com.sun.jersey.test.framework.spi.container.grizzly.web.GrizzlyWebTestContainerFactory;


public abstract class BaseTestClient extends JerseyTest {
	
	public static final String CONTEXT_PATH = "/springhibernate";
	
	public BaseTestClient() {
		super(new WebAppDescriptor.Builder("com.augmentum.op.eastblue.shellstown.rest")
        .contextPath(CONTEXT_PATH)
        .contextParam("contextConfigLocation", "/JerseyTest-context.xml")
        .servletClass(SpringServlet.class)
        .contextListenerClass(ContextLoaderListener.class)
        .initParam(JSONConfiguration.FEATURE_POJO_MAPPING, "true")
        .build());
	}
	
	protected String getPath() {
		return super.getBaseURI().toASCIIString() + CONTEXT_PATH;
	}
	
	@Override
	protected TestContainerFactory getTestContainerFactory()
			throws TestContainerException {
		return new GrizzlyWebTestContainerFactory();
	}
	
	@Override
	protected URI getBaseURI() {
		
		return UriBuilder.fromUri("http://localhost/")
                .port(getPort(8080)).build();
	}
	
	@Override
	public WebResource resource() {
		
		return super.resource().path(getResourcePath());
	}
	
	protected abstract String getResourcePath();

	<T>T json2Obj(String result, Class<T> class1) {
		T obj = null;
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		try {
			obj = mapper.readValue(result, class1);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
