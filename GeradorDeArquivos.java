import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GeradorDeArquivos {

    public void salvaJson(Conversor conversor) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter escrita = new FileWriter(conversor.base_code() + ".json");
        escrita.write(gson.toJson(conversor));
        escrita.close();
    }
}
