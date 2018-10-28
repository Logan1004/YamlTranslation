package com.company.test;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Process {

    public static Boolean Deal(String keyName, Map<String, Object> map, HashMap hashMap) {
        //System.out.println(map);
        Iterator iter = map.entrySet().iterator();
        String curKeyName = keyName;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            keyName = curKeyName;
            if (!keyName.equals(""))
                keyName = keyName + "-" + (String) entry.getKey();
            else keyName = keyName + (String) entry.getKey();
            boolean flag = true;
            Map<String, Object> map1;
            try {
                map1 = (Map<String, Object>) entry.getValue();
                Deal(keyName, map1, hashMap);
            } catch (ClassCastException e) {
                flag = false;
            }
            if (!flag) {
                boolean flag2 = true;
                String val = "";
                int value = -999;
                try {
                    try {
                        val = (String) entry.getValue();
                    } catch (ClassCastException e) {
                        value = (int) entry.getValue();
                    }
                } catch (ClassCastException e) {
                    flag2 = false;
                }
                if (flag2 && value == -999) {
                    //System.out.println(keyName + "  " + val);
                    hashMap.put(keyName, val);
                } else if (flag2 && value != -999) {
                    //System.out.println(keyName + "  " + value);
                    hashMap.put(keyName, value);
                } else {
                    try {
                        ArrayList arrayList = (ArrayList) entry.getValue();
                        try {
                            for (int i = 0; i < arrayList.size(); i++) {
                                Deal(keyName, (Map<String, Object>) arrayList.get(i), hashMap);
                            }
                        } catch (ClassCastException e) {
                            hashMap.put(keyName, arrayList);
                        }
                    } catch (ClassCastException e) {
                        //System.out.println("sss");
                    }
                }

            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        Yaml yaml = new Yaml();
        File file = new File("/Users/logan/Desktop/JavaEE/untitled5/src/com/company/test/demo.yaml");
        //Object load = yaml.loadAll(new FileInputStream(file));
        Map<String, Object> map = null;
        //System.out.println(yaml.dump(load));
        ArrayList arrayList = new ArrayList();
        for (Object data : yaml.loadAll(new FileInputStream(file))) {
            HashMap hashMap = new HashMap();
            map = (Map<String, Object>) data;
            //System.out.println(map);
            Deal("", map, hashMap);
            arrayList.add(hashMap);
            //System.out.println(hashMap.size());
            //for (Object key : hashMap.keySet()) {
            //    System.out.println("Key: " + (String) key + " Value: " + hashMap.get(key));
                //hashMap.remove(key);
            //}
        }
        for (int i = 0; i < arrayList.size(); i++) {
            HashMap hashMap = (HashMap) arrayList.get(i);
            for (Object key : hashMap.keySet()) {
                System.out.println("Key: " + (String) key + " Value: " + hashMap.get(key));
            }
            System.out.println("---------------");
        }
    }
}