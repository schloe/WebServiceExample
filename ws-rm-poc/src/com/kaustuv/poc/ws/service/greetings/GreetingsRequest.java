
package com.kaustuv.poc.ws.service.greetings;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.jaxb.JAXBToStringStyle;
import org.xml.sax.Locator;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name"
})
@XmlRootElement(name = "GreetingsRequest")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2013-04-02T03:12:36+05:30", comments = "JAXB RI v2.1.4-10/27/2009 06:09 PM(mockbuild)-fcs")
public class GreetingsRequest
    implements Locatable
{

    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2013-04-02T03:12:36+05:30", comments = "JAXB RI v2.1.4-10/27/2009 06:09 PM(mockbuild)-fcs")
    protected String name;
    @XmlLocation
    @XmlTransient
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2013-04-02T03:12:36+05:30", comments = "JAXB RI v2.1.4-10/27/2009 06:09 PM(mockbuild)-fcs")
    protected Locator locator;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2013-04-02T03:12:36+05:30", comments = "JAXB RI v2.1.4-10/27/2009 06:09 PM(mockbuild)-fcs")
    public synchronized String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2013-04-02T03:12:36+05:30", comments = "JAXB RI v2.1.4-10/27/2009 06:09 PM(mockbuild)-fcs")
    public synchronized void setName(String value) {
        this.name = value;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2013-04-02T03:12:36+05:30", comments = "JAXB RI v2.1.4-10/27/2009 06:09 PM(mockbuild)-fcs")
    public synchronized String toString() {
        return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.SIMPLE_STYLE);
    }

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2013-04-02T03:12:36+05:30", comments = "JAXB RI v2.1.4-10/27/2009 06:09 PM(mockbuild)-fcs")
    public synchronized Locator sourceLocation() {
        return locator;
    }

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2013-04-02T03:12:36+05:30", comments = "JAXB RI v2.1.4-10/27/2009 06:09 PM(mockbuild)-fcs")
    public synchronized void setSourceLocation(Locator newLocator) {
        locator = newLocator;
    }

}
