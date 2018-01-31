package com.cxc.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * 按行写数据
     *
     * @param list
     * @param filePath
     */
    public static void writeFile(List<String> list, String filePath) {
        FileWriter writer = null;
        BufferedWriter bw = null;
        try {
            writer = new FileWriter(filePath);
            bw = new BufferedWriter(writer);
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i));
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("write file exception");
        } finally {
            try {
                bw.close();
                writer.close();
            } catch (IOException e) {
                System.out
                    .println("fileWriter or bufferedReader close failed.");
            }
        }
    }

    /**
     * 按行读取文件
     *
     * @param filePath
     * @return
     */
    public static List<String> readByLine(String filePath) {
        final List<String> list = new ArrayList<String>();
        final File file = new File(filePath);
        BufferedReader bw = null;
        if (!file.exists()) {
            return list;
        }
        try {
            bw = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = bw.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {}
        }
        return list;
    }

    /**
     * 按行读取文件
     *
     * @param filePath
     * @return
     */
    public static String readText(String filePath) {
        StringBuffer sb = new StringBuffer();
        File file = new File(filePath);
        BufferedReader bw = null;
        if (!file.exists()) {
            return sb.toString();
        }
        try {
            bw = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = bw.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {}
        }
        return sb.toString();
    }
}
