package com.two.shopping.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Configuration
public class StartUp{
    private static String projectPath = System.getProperty("user.dir") + "\\shopping-gateway\\src\\main\\resources\\application.yml";



    public static String read() {
        try {
            System.err.println(projectPath);
            File file = new File(projectPath);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            String content = "";
            String read;
            while ((read = bufferedReader.readLine()) != null) {
                if (read.contains("static-locations")) {
                    String[] split = read.split(":");
                    content += split[0] + ":" + " " + "file:" + System.getProperty("user.dir") + "\\img\\" + "\n";
                } else if (read.contains("port:")) {
                    content += " port: 1007\n";
                } else {
                    content += read + "\n";
                }
            }
            bufferedReader.close();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void write() {
        try {
            String read = read();
            OutputStreamWriter outStream = new OutputStreamWriter(new FileOutputStream(projectPath));
            BufferedWriter bufferedWriter = new BufferedWriter(outStream);

            bufferedWriter.write(read);
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
