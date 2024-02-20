package priv.samera2022.module.gadgets.gpt;

import priv.samera2022.module.Mixture;
import priv.samera2022.module.gadgets.gpt.config.Config;
import priv.samera2022.module.gadgets.gpt.config.ConfigHandler;
import priv.samera2022.module.gadgets.gpt.request.Body;
import priv.samera2022.module.gadgets.gpt.request.Header;
import priv.samera2022.module.gadgets.gpt.request.Message;
import priv.samera2022.module.gadgets.gpt.response.Response;
import priv.samera2022.module.mainFrame;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Connection {
    public static Response question(String content) {
        Config config = ConfigHandler.read("default");
        Body body = new Body();
        body.setModel(config.getModel());
        if (config.getFrequencyPenalty()!=null) body.setFrequencyPenalty(config.getFrequencyPenalty());
        if (config.getLogitBias()!=null) body.setLogitBias(config.getLogitBias());
        if (config.getMaxTokens()!=null) body.setMaxTokens(config.getMaxTokens());
        if (config.getN()!=null) body.setN(config.getN());
        if (config.getPresencePenalty()!=null) body.setPresencePenalty(config.getPresencePenalty());
        if (config.getStop()!=null) body.setStop(config.getStop());
        if (config.getStream()!=null) body.setStream(config.getStream());
        if (config.getTemperature()!=null) body.setTemperature(config.getTemperature());
        if (config.getTopP()!=null) body.setTopP(config.getTopP());
        if (config.getUser()!=null) body.setUser(config.getUser());

        Message message = new Message("user",content);
        body.setMessage(message);

        Mixture<Integer,String> response = post("https://api.chatanywhere.com.cn/v1/chat/completions",new Header(config.getAuthorization()),body);
        mainFrame.logger.debug("Response Code: "+response.getKey());
        mainFrame.logger.info("Receive Response: "+response.getValue());
        return Convert.convert(response.getValue());
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
