package JSON;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.xml.bind.SchemaOutputResolver;
import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class readJSON {
    public static void main(String[] args) throws IOException {
//        File file = new File("/home/meet/IdeaProjects/Practice/out/production/Practice/JSON/x");
//        Scanner sc = new Scanner(file);
//
//        while (sc.hasNextLine())
//            System.out.println(sc.nextLine());

        File file = new File("/home/meet/IdeaProjects/Practice/out/production/Practice/JSON/x");
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String st,data[],date="";
        JSONObject main = new JSONObject(),temp;
        ArrayList<JSONObject> day = new JSONArray();

        while ((st = bf.readLine()) != null){
            data = st.split(" ");
            if(!date.equals(data[0])){
                if(day.size() == 0){
                    date = data[0];
                    continue;
                }
                main.put(date.replace("/","-"),day);
                date = data[0];
                day.clear();
            }
//            temp.clear();
            temp = new JSONObject();

            temp.put("time",data[1]);
            temp.put("cpu",data[2]);
            temp.put("mem",data[3]);
            temp.put("total mem",data[4]);
            day.add(temp);
         }
        main.put(date.replace("/","-"),day);

        System.out.println(day);
        FileWriter writer = new FileWriter("/home/meet/IdeaProjects/Practice/src/JSON/final.json");
        BufferedWriter bw = new BufferedWriter(writer);
        bw.write(main.toJSONString());
        bw.close();
    }
}
