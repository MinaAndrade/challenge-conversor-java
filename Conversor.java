import com.google.gson.JsonObject;

public record Conversor(String base_code, JsonObject conversion_rates) {
}
