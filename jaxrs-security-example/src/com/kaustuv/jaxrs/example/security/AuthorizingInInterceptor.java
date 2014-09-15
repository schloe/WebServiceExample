/*---------------------------------------------------------------------------*\
|
| Copyright (c) Orga Systems GmbH and/or its affiliates, 2013
|
| All Rights Reserved.
|
| The software contained on this media is proprietary to and
| embodies the confidential technology of the copyright holder.
| Possession, use, duplication or dissemination of the software
| and media is authorized only pursuant to a valid written license
| from the copyright holder.
|
| This copyright notice must appear in all copies of this software.
|
\*---------------------------------------------------------------------------*/
package com.kaustuv.jaxrs.example.security;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Map;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.context.SecurityContextHolder;

public class AuthorizingInInterceptor extends AbstractPhaseInterceptor<org.apache.cxf.message.Message> {

	AuthorizationPolicy policy = null;

	protected static final Logger log = LoggerFactory.getLogger(AuthorizingInInterceptor.class);

	public AuthorizingInInterceptor() {
		super(Phase.PRE_INVOKE);
	}

	private AuthorizationPolicy getAuthorizationPolicy(Message message) {
		policy = message.get(AuthorizationPolicy.class);
		return policy;
	}

	public void handleMessage(Message message) throws Fault {
		// SecurityContextHolder.clearContext();
		try {
			policy = getAuthorizationPolicy(message);
			if (policy == null) {
				sendErrorResponse(message, HttpURLConnection.HTTP_UNAUTHORIZED);
			} else if (policy.getUserName().isEmpty() || policy.getUserName() == null) {
				log.info("@@@@" + policy.getUserName());
				sendErrorResponse(message, HttpURLConnection.HTTP_UNAUTHORIZED);
			} else if (policy.getPassword().isEmpty() || policy.getPassword() == null) {
				log.info("@@@@" + policy.getPassword());
				sendErrorResponse(message, HttpURLConnection.HTTP_UNAUTHORIZED);
			} else if ((!policy.getUserName().equalsIgnoreCase("kaustuv")) || (!policy.getPassword().equalsIgnoreCase("kaustuv"))) {
				log.info("#### " + policy.getUserName() + " ###### " + policy.getPassword());
				sendErrorResponse(message, HttpURLConnection.HTTP_UNAUTHORIZED);
			} else {
				log.info(policy.getUserName() + " is Authorized for the service");
			}
			message.getInterceptorChain().abort();
			// SecurityContextHolder.setStrategyName(policy.getUserName());
		} catch (Exception exception) {
			throw new Fault(exception);
		}
	}

	private void sendErrorResponse(Message message, int responseCode) {
		Message outMessage = getOutMessage(message);
		outMessage.put(Message.RESPONSE_CODE, responseCode);
		// Set the response headers
		Map<String, Object> responseHeaders = (Map<String, Object>) message.get(Message.PROTOCOL_HEADERS);
		System.out.println("##" + responseHeaders);
		if (responseHeaders != null) {
			responseHeaders.put("WWW-Authenticate", Arrays.asList(new String[]{"Basic realm=realm"}));
			// responseHeaders.put(Message.CONTENT_TYPE, "text/plain");
			// responseHeaders.put("Content-length", Arrays.asList(new String[]
			// { "10" }));
		}

		try {
			getConduit(message).prepare(outMessage);
			close(outMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Message getOutMessage(Message inMessage) {
		Exchange exchange = inMessage.getExchange();
		Message outMessage = exchange.getOutMessage();
		if (outMessage == null) {
			Endpoint endpoint = exchange.get(Endpoint.class);
			outMessage = endpoint.getBinding().createMessage();
			exchange.setOutMessage(outMessage);
		}
		outMessage.putAll(inMessage);
		return outMessage;
	}

	private Conduit getConduit(Message inMessage) throws IOException {
		Exchange exchange = inMessage.getExchange();
		// EndpointReferenceType target =
		// exchange.get(EndpointReferenceType.class);
		// Conduit conduit = exchange.getDestination().getBackChannel(inMessage,
		// null, target);
		Conduit conduit = exchange.getDestination().getBackChannel(inMessage);
		exchange.setConduit(conduit);
		return conduit;
	}

	private void close(Message outMessage) throws IOException {
		OutputStream os = outMessage.getContent(OutputStream.class);
		os.flush();
		os.close();
	}

}
