package com.samplecode.chapter2;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chapter2 {
    public static List<String> results = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        final Pattern pattern = Pattern.compile("\"GET /index.php\\?id=(.*) HTTP/1\\.1\"");
        final Map<String, Object> hashmap = new HashMap<>();
        final StringBuilder buf = new StringBuilder();
        new Thread(new ThreadReader("C:\\Users\\我爱毛毛\\Desktop\\20231128\\access.log", new ThreadReadable() {
            @Override
            public void readLine(String line) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    try {
                        String thisLine = URLDecoder.decode(matcher.group(1), "GBK");
                        Pattern p1 = Pattern.compile("\\(\\(select flag from sqli.flag\\),([0-9])+,[0-9]+\\) = '(.*)'");
                        Matcher m1 = p1.matcher(thisLine);
                        if (!m1.find())
                            return;
                        if (!hashmap.containsKey("lst_i")) {
                            hashmap.put("lst_i", m1.group(1));
                            hashmap.put("lst_c", m1.group(2));
                            return;
                        }
                        if (m1.group(1).equals((String) hashmap.get("lst_i")) == false) {
                            buf.append(hashmap.get("lst_c"));
                        }
                        hashmap.put("lst_i", m1.group(1));
                        hashmap.put("lst_c", m1.group(2));
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void end() {
                System.out.println(buf.toString());
            }
        })).start();
    }
}
