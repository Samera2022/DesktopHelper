package priv.samera2022.module.gadgets.mc.dependencies;

import priv.samera2022.module.Mixture;
import priv.samera2022.module.config.ConfigHandler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Resolve {
    public static void main(String[] args) {
        System.out.println(get(0).getValue());
    }
    private static Mixture<Integer,String> get(int gameId) {
        Mixture<Integer,String> result = new Mixture<>(0,null);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.curseforge.com/v1/mods/search?gameId="+gameId)) // 替换为你的目标URL
                .header("x-api-key", ConfigHandler.CONFIG.getCf_api_key()) // 设置请求头
                .GET() // 设置请求体
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
