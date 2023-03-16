import exceptions.FileParserException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main {

    public static FileWork fileWork = new FileWork();
    public static CountWork countWork = new CountWork();
    public static Parser parser = new Parser();

    public static final String fileName = "tickets.json";
    public static final Double percentile = 90.0;

    public static void main(String[] args){
        try {
            String fileData = fileWork.getFile(fileName);
            JSONObject mainObject = new JSONObject(fileData);
            ArrayList<Long> flightTime = parser.getFlightTimes(mainObject);

            long averageTime = countWork.getAverageFlightTime(flightTime) / 60;
            long percentileTime = (long) countWork.getPercentileFlightTime(
                    new ArrayList<>(flightTime.stream().map(d -> (double) d).toList()), percentile);

            System.out.printf("Среднее время полета: %dч %dм\n", averageTime / 60, averageTime % 60);
            System.out.printf("90-й процентиль: %dч %dм\n", percentileTime / 60, percentileTime % 60);
        }
        catch (FileParserException fileParserException) {
            System.out.println(fileParserException.getMessage());
        }
        catch (JSONException jsonException) {
            System.out.println("Ошибка в содержании файла!");
        }
    }
}
