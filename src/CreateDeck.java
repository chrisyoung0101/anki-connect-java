

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class CreateDeck {

    public static void main(String[] args) throws Exception {
        // Set up the HTTP request
        URL url = new URL("http://localhost:8765");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        // Write the JSON request body to the output stream
        String requestBody = "{\"action\": \"createDeck\", \"params\": {\"deck\": \"My Deck\"}}";

        // Naming the deck example : this one is name My Deck
        //String requestBody = "{\"action\": \"createDeck\", \"params\": {\"deck\": \"My Deck\"}}";

        // Print the request body to the console
        System.out.println(requestBody);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Read the JSON response from the input stream
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }


}
