package com.example.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("demo1")
@SpringBootApplication
public class Demo1Application extends SpringBootServletInitializer implements ApplicationRunner {
	private final Logger LOG = LoggerFactory.getLogger(Demo1Application.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Book myBook;

	@Value("${spring.application.name:default_app_name}")
	private String applicationName;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		// return super.configure(builder);
		return builder.sources(Demo1Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

	@RequestMapping(value = "/")
	public String hello() {
		return "Hello World. This is my first spring boot web application. The name of the applicaton is "
				+ applicationName + ".";
	}

	@RequestMapping(value = "/products")
	public ResponseEntity<Object> getProducts() {
		ResponseEntity<Object> entity = new ResponseEntity<Object>(HttpStatus.OK);
		LOG.info("get products");
		return (entity);

	}

	// @RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces =
	// MediaType.APPLICATION_XML_VALUE)
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Book getBook(@PathVariable int id) {
		LOG.info("get book. ID:{}", id);
		LOG.info("book:{}", myBook);
		return myBook;
	}

	// @RequestMapping(value = "/book}", method = RequestMethod.POST, consumes =
	// MediaType.APPLICATION_XML_VALUE)
	@RequestMapping(value = "/book", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Book postBook(@RequestBody Book book) {
		LOG.info("add book.");
		LOG.info("book:{}", book);

		return (myBook);
	}

	@RequestMapping(value = "/title", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	public void postTitle(@RequestBody(required = false) String title) {
		LOG.info("post title.");
		LOG.info("title:{}", title);
	}

	@RequestMapping(value = "/requestparam")
	public void getRequestParam(@RequestParam(name = "id") String id,
			@RequestParam(name = "text", required = false, defaultValue = "No value") String text) {
		LOG.info("request param. id:{}, text:{}", id, text);
	}

	@RequestMapping(value = "/headerparam")
	public void getHeaderParam(
			@RequestHeader(name = "Content-Type", defaultValue = "no content type") String contentType,
			@RequestHeader(name = "Accept-Language", defaultValue = "no language") String accepteLanguage) {
		LOG.info("Header param. Content-Type:{}, Accept-Language:{}", contentType, accepteLanguage);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOG.info("Hello World from Application Runner. The name of the applicaton is {}.", applicationName);
	}
}
