package me.testprj;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class Soup {
    public static void main(String[] args) {
        Soup soup = new Soup();
        try {
            soup.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl(String line){
        return "https://translate.google.com/?client=t&sl=mn&tl=ru&hl=ru&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&ie=UTF-8&oe=UTF-8&q="
                +line;
    }

    public void run() throws IOException {
        for (int i = 0; i < 200; i++) {
            
        }
    }
    
    public String translate(String inpline) throws IOException {
        String urlStr = this.getUrl(inpline);

        URL url = new URL(urlStr);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.111.0.1", 3128)); // or whatever your proxy is
        HttpURLConnection uc = (HttpURLConnection)url.openConnection(proxy);
        uc.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        uc.connect();

        String line = null;
        StringBuffer tmp = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        while ((line = in.readLine()) != null) {
            tmp.append(line);
        }

        Document doc = Jsoup.parse(String.valueOf(tmp));
//
//
        Element e = doc.getElementById("result_box").child(0);
        return e.text();
    }
}
