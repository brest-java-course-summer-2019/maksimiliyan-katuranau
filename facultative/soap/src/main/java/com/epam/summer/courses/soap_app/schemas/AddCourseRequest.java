//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.08.27 at 09:44:30 AM MSK 
//


package com.epam.summer.courses.soap_app.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="course" type="{http://courses.summer.epam.com/soap_app/schemas}CourseType"/>
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
    "course"
})
@XmlRootElement(name = "AddCourseRequest")
public class AddCourseRequest {

    @XmlElement(required = true)
    protected CourseType course;

    /**
     * Gets the value of the course property.
     * 
     * @return
     *     possible object is
     *     {@link CourseType }
     *     
     */
    public CourseType getCourse() {
        return course;
    }

    /**
     * Sets the value of the course property.
     * 
     * @param value
     *     allowed object is
     *     {@link CourseType }
     *     
     */
    public void setCourse(CourseType value) {
        this.course = value;
    }

}
