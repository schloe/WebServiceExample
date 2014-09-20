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

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebFault;

/**
 * @author KMaji
 *
 */
@WebFault
public class NotAuthorizedException extends WebApplicationException{

  private static final long serialVersionUID = -1203116970226591712L;


  public NotAuthorizedException(String faultString) {
    super(Response.status(Response.Status.UNAUTHORIZED)
          .entity(faultString).type(MediaType.TEXT_PLAIN).build());
    System.out.println("################NotAuthorizedException(String faultString)######################");
  }

}
