import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;



public class PlayerApiClient {

    private static final String URL = "http://localhost:8080/player";
    private final HttpClient client = HttpClient.newHttpClient();

    public void save(String name,int score,int coins) throws Exception {
        String json = "{\"name\":\"" + name + "\",\"score\":" + score + ",\"coins\":" + coins + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        client.send(request,HttpResponse.BodyHandlers.ofString());
    }

    public List<PlayerScore> topPlayer () throws Exception{

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL+"/top"))
                .GET()
                .build();

        String json = client.send(request,HttpResponse.BodyHandlers.ofString()).body();

        PlayerScore[] players = new Gson().fromJson(json,PlayerScore[].class);

        return List.of(players);

    }

    public List<String> topPlayersLine() throws Exception {

        List<String> line = new ArrayList<>();

        for (PlayerScore player : topPlayer()) {
            line.add(player.getName() + " - " + player.getScore());
        }

        return line;


    }
}
