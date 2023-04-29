import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddNote {
    public static void main(String[] args) throws IOException {

        URL url = new URL("http://localhost:8765");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        String requestBody = "{\"action\": \"addNote\", \"params\": {\"note\": {\"deckName\": \"My Deck\", \"modelName\": \"Basic\", \"fields\": {\"Front\": \"jjljk\", \"Back\": \"dlkjjad!\"}}}}";

        // Print the request body to the console
        System.out.println(requestBody);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("this is the response : " + response.toString());
        }
    }
}
