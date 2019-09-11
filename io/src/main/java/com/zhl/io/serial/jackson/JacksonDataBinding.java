package com.zhl.io.serial.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

/**
 * @program codeX
 * @description:介绍jackson data binding
 * @author: meilong
 * @create: 2019/09/10 19:52
 */
public class JacksonDataBinding {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setName("alan");
        person.setAddress("A街道");
        person.setMobile("990");

        // serializer object to json  依赖Person getter
        ObjectMapper objectMapper = new ObjectMapper();
        String personJson = objectMapper.writeValueAsString(person);
        System.out.println("serializer object to json :" + personJson);

        // deserialize json to object
        Person object = objectMapper.readValue(personJson, Person.class);
        System.out.println("deserialize json to object :" + object.getAddress());

        // 在默认情况下（即不对ObjectMapper做任何额外配置，也不对Java对象加任何Annotation），ObjectMapper依赖于Java对象的
        // 默认的无参构造函数进行反序列化，并且严格地通过getter和setter的命名规约进行序列化和反序列化

        // row data binding
        HashMap map = objectMapper.readValue(personJson, HashMap.class);
        System.out.println("row data binding :" + map.get("name"));

        // generic data binding
        String genericBinding = "{\"key1\":{\"name\":\"alan\",\"address\":\"A街道\",\"mobile\":\"990\",\"abc\":\"abc\"}}";
        HashMap<String, Person> personMap = objectMapper
                .readValue(genericBinding, new TypeReference<HashMap<String, Person>>() {
                });
        Person key1 = personMap.get("key1");
        System.out.println("generic data binding :" + key1.getName());
    }
}