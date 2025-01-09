package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GetDataUtil {
    public static List<String> getData(String dataPath) {
        ArrayList<String> list = new ArrayList<String>();
        try (FileInputStream fileInputStream = new FileInputStream(new File(dataPath));
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            String content = "";
            while (bufferedReader.ready()){
                content = bufferedReader.readLine();
                //System.out.println(content);
                list.add(content);
            }
            list.remove(0);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }
}