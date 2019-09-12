package com.zhl.io.serial.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @program codeX
 * @description:介绍jackson tree model
 * @author: meilong
 * @create: 2019/09/11 09:32
 */
public class JacksonTreeModel {

    private static final String TREE_MODEL_BINDING = "" +
            "{\n" +
            "\"treekey1\":\"treevalue1\",\n" +
            "\"treekey2\":\"treevalue2\",\n" +
            "\"children\":[\n" +
            "   {\n" +
            "       \"childkey1\":\"childkey1\"\n" +
            "   }\n" +
            " ]\n" +
            "}";

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(TREE_MODEL_BINDING);

        String treeKey1 = rootNode.path("treekey1").textValue();
        System.out.println(treeKey1);

        JsonNode childrenNode = rootNode.path("children");
        String childKey1 = childrenNode.get(0).path("childkey1").textValue();
        System.out.println(childKey1);
    }
}
