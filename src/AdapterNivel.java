import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class AdapterNivel {
    public String citesteNivel(String filepath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filepath)));
    }
}
