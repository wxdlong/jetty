package com.wxdlong.jersey;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class MyBean implements Serializable {


    public String name;
    public int age;

    public MyBean() {
    } // JAXB needs this

    public MyBean(String name, int age) {
        this.name = name;
        this.age = age;
    }


}
