import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorMoneda {

//    String url = "https://v6.exchangerate-api.com/v6/a6ebc38dba1f71deba6aac3f/pair/"+moneda1+"/"+moneda2+"/";

    public Moneda convertirMoneda(URI direccion){

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (Exception e) {
            System.out.println("Error al convertir moneda");
        }

        return null;
    }
}
