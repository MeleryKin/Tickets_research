import exceptions.FileParserException;

import java.io.*;

public class FileWork {

    public String getFile(String fileDataName) throws FileParserException {
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(fileDataName);
            var x = getClass().getClassLoader().getResource(fileDataName);
            System.out.println(x);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }
        catch (Exception exception) {
            throw new FileParserException("Ошибка при чтении файла!");
        }
    }
}
