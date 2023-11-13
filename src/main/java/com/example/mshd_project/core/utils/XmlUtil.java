package com.example.mshd_project.core.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.List;

/**
 * xml工具类
 * @author XmlUtil
 * @date 2022/06/17
 */
public class XmlUtil {

    /**将xml转换为json
     * @param root xml根节点
     * @return json字符串
     */
    public static String xmlToJson(Element root){
        JSONObject jsonObject = xmlToJsonObject(root);
        String json = JSON.toJSONString(jsonObject,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        return json;
    }

    /**构建json对象，将根节点下的解析后的数据放在根节点下
     * @param element 文档的根节点.
     * @return json对象
     */
    public static JSONObject xmlToJsonObject(Element element){
        JSONObject supJson = new JSONObject();
        JSONObject json = new JSONObject();
        dom4j2Json(element, json);
        supJson.put(element.getName(), json);
        return supJson;
    }

    /**解析当前节点下的所有内容并放入JSONObject中
     * @param element 节点
     * @param json 需要放入的jsonObject
     */
    private static void dom4j2Json(Element element, JSONObject json) {
        // 如果是属性
        for (Object o : element.attributes()) {
            Attribute attr = (Attribute) o;
            if (!isEmpty(attr.getValue())) {
                json.put("-" + attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl = element.elements();
        // 如果没有子元素,只有一个值
        if (chdEl.isEmpty() && !isEmpty(element.getText())) {
            json.put(element.getName(), element.getText());
        }
        // 有子元素
        for (Element e : chdEl) {
            // 子元素也有子元素
            if (!e.elements().isEmpty()) {
                JSONObject chdjson = new JSONObject();
                dom4j2Json(e, chdjson);
                Object o = json.get(e.getName());
                if (o != null) {
                    JSONArray jsona = null;
                    // 如果此元素已存在,则转为jsonArray
                    if (o instanceof JSONObject) {
                        JSONObject jsono = (JSONObject)o;
                        json.remove(e.getName());
                        jsona = new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if (o instanceof JSONArray) {
                        jsona = (JSONArray)o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                } else {
                    if (!chdjson.isEmpty()) {
                        json.put(e.getName(), chdjson);
                    }
                }
            } else {    // 子元素没有子元素
                for (Object o : element.attributes()) {
                    Attribute attr = (Attribute)o;
                    if (!isEmpty(attr.getValue())) {
                        json.put("-" + attr.getName(), attr.getValue());
                    }
                }
                if (!e.getText().isEmpty()) {
                    json.put(e.getName(), e.getText());
                }
                // 最后一层且有属性
                if (e.getText().isEmpty() && !e.attributes().isEmpty()) {
                    Object oe = json.get(e.getName());
                    if (oe == null) {
                        JSONObject jsonObject = new JSONObject();
                        for (Object o : e.attributes()) {
                            Attribute attr = (Attribute) o;
                            jsonObject.put("-" + attr.getName(), attr.getValue());
                        }
                        json.put(e.getName(), jsonObject);
                    } else {
                        JSONArray jsonArray = new JSONArray();
                        if (oe instanceof JSONObject) {
                            JSONObject oeJsonObject = (JSONObject)oe;
                            jsonArray.add(oeJsonObject);
                        }
                        if (oe instanceof JSONArray) {
                            jsonArray = (JSONArray) oe;
                        }
                        JSONObject jsonObject = new JSONObject();
                        for (Object o : e.attributes()) {
                            Attribute attr = (Attribute) o;
                            if (!isEmpty(attr.getValue())) {
                                jsonObject.put("-" + attr.getName(), attr.getValue());
                            }
                        }
                        jsonArray.add(jsonObject);
                        json.put(e.getName(), jsonArray);
                    }
                }
            }
        }
    }

    /**判断字符串是否为空
     * @param value 字符串
     * @return 布尔
     */
    private static boolean isEmpty(String value) {
        return value == null || value.length() == 0 || "null".equals(value);
    }

    /**将xml内容写入本地
     * @param document xml
     * @param path 存储路径
     * @param format xml格式
     * @return 布尔
     */
    public static boolean writeXml(Document document, String path, String format){
        File file = new File(path);
        OutputFormat of = OutputFormat.createPrettyPrint();
        // 设置编码格式
        of.setEncoding(format);
        try {
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), of);
            writer.write(document);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**json字符串转换成xml字符串
     * @param jsonStr json字符串
     * @return xml
     */
    public static Document jsonToXml(String jsonStr){
        JSONObject json = JSON.parseObject(jsonStr);
        Document document = DocumentHelper.createDocument();
        // root对象只能有一个
        for (String rootKey : json.keySet()) {
            Element root = jsonToElement(json.getJSONObject(rootKey), rootKey);
            document.add(root);
            break;
        }
        return document;
    }

    /**
     * @param json json对象
     * @param nodeName 根节点名称
     * @return xml结构
     */
    private static Element jsonToElement(JSONObject json, String nodeName) {
        Element node = DocumentHelper.createElement(nodeName);
        for (String key : json.keySet()) {
            //当前key是属性
            if(key.charAt(0) == '-'){
                String k = key.substring(1, key.length());
                String value = json.get(key).toString();
                node.addAttribute(k, value);
            }else{
                Object child = json.get(key);
                if (child instanceof JSONObject) {
                    node.add(jsonToElement(json.getJSONObject(key), key));
                }else {
                    JSONArray childs = (JSONArray) child;
                    for (int i = 0; i < childs.size(); i++) {
                        JSONObject jsonObject = (JSONObject) childs.get(i);
                        node.add(jsonToElement(jsonObject, key));
                    }
                }
            }
        }
        return node;
    }
}

