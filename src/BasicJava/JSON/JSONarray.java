//package BasicJava.JSON;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//import java.io.*;
//import java.util.ArrayList;
//
//public class JSONarray {
//    public static void main(String[] args) throws IOException {
//        File file = new File("/home/meet/IdeaProjects/Practice/out/production/Practice/BasicJava.JSON/x");
//        BufferedReader bf = new BufferedReader(new FileReader(file));
//        String st,data[];
//        JSONObject main = new JSONObject(),temp;
//        ArrayList<JSONObject> day = new JSONArray();
//
//        while ((st = bf.readLine()) != null){
//            data = st.split(" ");
//            temp = new JSONObject();
//            temp.put("date",data[0].replace('/',' '));
//            temp.put("time",data[1]);
//            temp.put("cpu",data[2]);
//            temp.put("mem",data[3]);
//            temp.put("total mem",data[4]);
//            day.add(temp);
//        }
//        main.put("data",day);
//
//        FileWriter writer = new FileWriter("/home/meet/IdeaProjects/Practice/src/BasicJava.JSON/final.json");
//        BufferedWriter bw = new BufferedWriter(writer);
//        bw.write(main.toJSONString());
//        bw.close();
//    }
//}
