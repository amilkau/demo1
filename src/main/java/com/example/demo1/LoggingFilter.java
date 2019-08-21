package com.example.demo1;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingFilter implements Filter {
	private final Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Enumeration<String> headerNames = httpRequest.getHeaderNames();

		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				LOG.info("Header: " + httpRequest.getHeader(headerNames.nextElement()));
			}
		}
		/*
		 * String marshalledXml =
		 * org.apache.commons.io.IOUtils.toString(request.getInputStream(),
		 * Charset.defaultCharset()); LOG.info("Body:{}", marshalledXml);
		 */
		chain.doFilter(request, response);
	}

}
