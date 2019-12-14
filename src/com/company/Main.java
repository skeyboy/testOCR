package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static List<String> executeLinuxCmd(String cmd) {
        Runtime run = Runtime.getRuntime();
        try {
//            Process process = run.exec(cmd);
            Process process = run.exec(new String[]{"/bin/sh", "-c", cmd});
            InputStream in = process.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
            List<String> list = new ArrayList<String>();
            String result = null;
            while ((result = bs.readLine()) != null) {
                list.add(result);
            }
            in.close();
            process.waitFor();
            process.destroy();
            return list;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> factory(String pngPath, String resultPath) {
        String commad = "tesseract " + pngPath + " " + resultPath + " -l chi_sim";
        List<String> result = executeLinuxCmd(commad);

        /* 读取数据 */
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(result+".txt")),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {//数据以逗号分隔


            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }
        return result;
    }

    public static void main(String[] args) {
//        executeLinuxCmd("tesseract ~/Desktop/171575595790_.pic_hd.png "+"/Users/liyulong/resut -l chi_sim+eng");
        Date date = new Date();
//        List<String> result = factory("~/Desktop/171575595790_.pic_hd.png", "/Users/liyulong/resut" + date.getTime());
        List<String> result = factory("/root/8dea492c-5fac-4653-9f81-e6c108a22f4b.jpg", "/root/resut" + date.getTime());

    }
}
