import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorDeMoedas {

    private final Gson gson = new Gson();

    public Conversor buscaMoeda(String moedaOrigem) {
        URI endereco = URI.create("https://v6.exchangerate-api.com/v6/0aff3993f5e5d9336c8fb0b3/latest/" + moedaOrigem);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return gson.fromJson(response.body(), Conversor.class);
        } catch (Exception e) {
            throw new RuntimeException("Não consegui converter a moeda.", e);
        }
    }

    public double buscaTaxaDeConversao(String moedaOrigem, String moedaDestino) {
        Conversor conversor = buscaMoeda(moedaOrigem);
        JsonObject conversionRates = conversor.conversion_rates();
        return conversionRates.get(moedaDestino).getAsDouble();
    }
}
