package com.guye.sun;


import org.springframework.util.StreamUtils;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by suneee on 2018/8/20.
 */
public class HttpURLConnectionTest {

    public static void main(String[] args) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("info","你是男还是女的");
        params.put("loc","上海市徐汇区");
        params.put("userid","232");
        params.put("appkey","d6c2babb51dfb55eca5ab82f0808b73a");
        String url = "https://way.jd.com/turing/turing";
        String resStr = HttpURLConnectionTest.httpUtil(params,url);
        System.out.println(resStr);
    }

    public static String httpUtil(Map<String,Object> params,String url){
        //1.需要访问的地址
        try {
            URL urll = new URL(url);
            //2.打开连接
            HttpURLConnection conn = (HttpURLConnection) urll.openConnection();
            //3.设置请求方式
            conn.setRequestMethod("POST");
            //4.需要输出参数
            conn.setDoOutput(true);
            //5.传递参数
            if (params != null && params.size() > 0){
                StringBuilder builder = new StringBuilder();
                for (Map.Entry<String,Object> entry : params.entrySet()){
                    builder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
                conn.getOutputStream().write(builder.substring(1).toString().getBytes("UTF-8"));
            }
//            builder.append("info=").append("你是男还是女的")
//                    .append("&loc=").append("上海市静安区")
//                    .append("&userid=").append("222")
//                    .append("&appkey=").append("d6c2babb51dfb55eca5ab82f0808b73a");
            //6.提交请求
            conn.connect();
            //7.接受对方响应的信息
            String respStr = StreamUtils.copyToString(conn.getInputStream(),Charset.forName("UTF-8"));
            return respStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
