package com.mysql.generator;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Set;

public class MultiValueMapTest {

    public static void main(String[] args) {


        MultiValueMap<String, String> stringMultiValueMap = new LinkedMultiValueMap<>();

        stringMultiValueMap.add("早班 9:00-11:00", "周一");
        stringMultiValueMap.add("早班 9:00-11:00", "周二");
        stringMultiValueMap.add("中班 13:00-16:00", "周三");
        stringMultiValueMap.add("早班 9:00-11:00", "周四");
        stringMultiValueMap.add("测试1天2次 09:00 - 12:00", "周五");
        stringMultiValueMap.add("测试1天2次 09:00 - 12:00", "周六");
        stringMultiValueMap.add("中班 13:00-16:00", "周日");
        //打印所有值
        Set<String> keySet = stringMultiValueMap.keySet();
        for (String key : keySet) {
            List<String> values = stringMultiValueMap.get(key);
            System.out.println(StringUtils.join(values.toArray()," ")+":"+key);

        }

    }


}
