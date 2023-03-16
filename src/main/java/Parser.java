import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

public class Parser {

    public ArrayList<Long> getFlightTimes(JSONObject jsonObject) {
        JSONArray tickets = jsonObject.getJSONArray("tickets");
        ArrayList<Long> flightTime = new ArrayList<>();
        for (Object o : tickets) {
            JSONObject ticket = (JSONObject) o;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yy H:mm");

            String departure = ticket.get("departure_date") + " " + ticket.get("departure_time");
            LocalDateTime departureDate = LocalDateTime.parse(departure, formatter);

            String arrival = ticket.get("arrival_date") + " " + ticket.get("arrival_time");
            LocalDateTime arrivalDate = LocalDateTime.parse(arrival, formatter);

            long time = arrivalDate.getLong(ChronoField.SECOND_OF_DAY)
                    - departureDate.getLong(ChronoField.SECOND_OF_DAY);
            flightTime.add(time);
        }
        return flightTime;
    }
}
