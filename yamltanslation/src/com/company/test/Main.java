package com.company.test;

import com.company.test.Entity.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static boolean ServiceDeal(Map<String,String> map){
        Service service = new Service();
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            if (key.equals("apiVersion")) {
                String val = (String) entry.getValue();
                service.setApiVersion(val);
            } else if (key.equals("kind")) {
            } else if (key.equals("metadata")) {
                Map<String, Object> map1 = (Map<String, Object>) entry.getValue();
                for (String key1 : map1.keySet()) {
                    //将LinkedHashMap的value再构建一个Map从而能取到最里面的值
                    if (key1.equals("name")) {
                        service.setDataName((String) map1.get(key1));
                    }
                    if (key1.equals("namespace")) {
                        service.setDataNameSpace((String) map1.get(key1));
                    }
                    if (key1.equals("labels")) {
                        Map<String, String> map2 = (Map<String, String>) map1.get(key1);
                        for (String key2 : map2.keySet()) {
                            service.setLabelsName((String) map2.get(key2));
                        }
                    }
                }
            }else if (key.equals("spec")){
                Map<String, Object> map1 = (Map<String, Object>) entry.getValue();
                for (String key1 : map1.keySet()) {
                    if (key1.equals("ports")) {
                        ArrayList arrayList = (ArrayList)map1.get(key1);
                        Map<String, Integer> map2 = (Map<String, Integer>) arrayList.get(0);
                        service.setPort(map2.get("port"));
                        map2 = (Map<String, Integer>) arrayList.get(0);
                        service.setTargetPort(map2.get("targetPort"));
                    }
                    if (key1.equals("selector")) {
                        Map<String, String> map2 = (Map<String, String>) map1.get(key1);
                        for (String key2 : map2.keySet()) {
                            service.setSelectorName((String) map2.get(key2));
                        }
                    }
                }
            }
        }
        System.out.println(service.getApiVersion());
        System.out.println(service.getDataName());
        System.out.println(service.getLabelsName());
        System.out.println(service.getDataNameSpace());
        System.out.println(service.getPort());
        System.out.println(service.getTargetPort());
        System.out.println(service.getSelectorName());
        return true;
    }
    public static boolean DeploymentDeal(Map<String,String> map){

        return true;
    }

    public static void main(String[] args) throws Exception {
        Yaml yaml = new Yaml();
        File file = new File("/Users/logan/Desktop/JavaEE/untitled5/src/com/company/test/demo.yaml");
        Object load = yaml.loadAll(new FileInputStream(file));

        //System.out.println(yaml.dump(load));
        Map<String, String> map = (Map<String, String>) yaml.load(new FileInputStream(file));
        System.out.println(map);
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            if (key.equals("kind")) {
                String val = (String) entry.getValue();
                if (val.equals("Service")) {
                    ServiceDeal(map);
                    break;
                }
                else if (val.equals("Deployment")){
                    DeploymentDeal(map);
                    break;
                }
            }
        }
    }
}
