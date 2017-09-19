package baseClass;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import java.net.URL;

import org.yaml.snakeyaml.Yaml;

public class test {
public static void main(String[] args) {
        //dddd
        try {
            Yaml yaml = new Yaml();
            /* URL     url = test.class.getClassLoader().getResource("test.yaml");
                String urlPa=url.getFile();
            System.out.println(urlPa);
            System.out.println(url);
            System.out.println(url.getPath());*/

           URL url = test.class.getClassLoader().getResource("demo.yaml");
           
            System.out.println(url);
            if (url != null) {
                //获取test.yaml文件中的配置数据，然后转换为obj，
            	//
             //   Object obj =yaml.load(new FileInputStream(url.getFile()));
               // System.out.println(obj);
            	//adafdaf
                //也可以将值转换为Map
                HashMap<String, HashMap<String, String>> map =( HashMap<String, HashMap<String, String>>)yaml.load(new FileInputStream(url.getFile()));
                System.out.println(map.get("baidu_text").get("Type"));
                //通过map我们取值就可以了.
                
            }        

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
