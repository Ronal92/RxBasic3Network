package com.jinwoo.android.rxbasic3network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by JINWOO on 2017-03-20.
 */

public class Remote {

    public static String getUrlByGet(String siteUrl){

        String result = "";
        try {
            if (!siteUrl.startsWith("http")) {
                siteUrl = "http://" + siteUrl;
            }
            URL url = new URL(siteUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 데이터가 정상적으로 들어왔느냐??
                // 4.1 서버연결로부터 스트림을 얻는다.
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                // 4.2 반복문을 돌면서 버퍼의 데이터를 읽어온다.
                StringBuilder temp = new StringBuilder(); // 스트링 빌더는 스트링 연산을 빠르게 해준다.
                String lineOfData = "";

                while ((lineOfData = br.readLine()) != null) {
                    temp.append(lineOfData);
                }
                return result = temp.toString();

            } else {
                result = "Error Code : " + responseCode;
            }
        }catch(Exception e){
            result = "Exception : " + e.getMessage();
        }

        return result;
    }

}
