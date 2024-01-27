package priv.samera2022.module.gadgets.web;

import priv.samera2022.module.Mixture;
import priv.samera2022.module.gadgets.web.request.Body;
import priv.samera2022.module.gadgets.web.request.Header;
import priv.samera2022.module.gadgets.web.request.Message;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpURLConnection {
    public static void main(String[] args) {
        System.out.println("\"content\": \"".length());
    }
    public static String question(String content) {
        Body body = new Body();
        body.setModel("gpt-3.5-turbo");
        body.setTemperature(0.7);
        Message message = new Message("user",content);
        body.setMessage(message);
        Mixture<Integer,String> response = post("https://api.chatanywhere.com.cn/v1/chat/completions",new Header("sk-CBoy5cjtjy6kyWl22c4SYK4WuyCkeIw4f6Qh13BrWJE4pbYN"),body);
        String replyContent = response.getValue();
        String reply = replyContent.substring(replyContent.indexOf("\",\"content\":\"")+13,replyContent.indexOf('}')-1);
        System.out.println(reply);
        System.out.println(response.getValue());
        return reply;
    }

    public static Mixture<Integer,String> post(String Url, Header header, Body body) {
        Mixture<Integer,String> result = new Mixture<>(0,null);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Url)) // 替换为你的目标URL
                .header("Authorization", "Bearer " + header.getAuthorization()) // 设置其他请求头，例如认证信息
                .header("User-Agent",header.getUserAgent())
                .header("Content-Type", header.getContentType()) // 设置请求头
                .POST(HttpRequest.BodyPublishers.ofString(body.toString())) // 设置请求体
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            result.setKey(response.statusCode());
            result.setValue(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
