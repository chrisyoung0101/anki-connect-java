import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AddManyNotes {

    // Probably not efficient but gets the job done.
    // This makes as many separate calls to the Anki Connect API as there are cards to create.

    // this is a print of the payload that you send to the Anki Connect API at http://localhost:8765/
    // {"action": "addNote", "params": {"note": {"deckName": "My Deck", "modelName": "Basic", "fields": {"Front": "Question 1", "Back": "Answer 1"}}}}
    // this is a confirmation that the card was created
    //1682729660334

    public static void main(String[] args) throws Exception {
        String[] jsonObjects = new String[] {
                "{\"deckName\": \"My Deck\", \"modelName\": \"Basic\", \"fields\": {\"Front\": \"Question 1sslk\", \"Back\": \"Answer 1\"}}",
                "{\"deckName\": \"My Deck\", \"modelName\": \"Basic\", \"fields\": {\"Front\": \"Question 2aa\", \"Back\": \"Answer 2\"}}",
                "{\"deckName\": \"My Deck\", \"modelName\": \"Basic\", \"fields\": {\"Front\": \"Question 3klk\", \"Back\": \"Answer 3\"}}",
                "{\"deckName\": \"My Deck\", \"modelName\": \"Basic\", \"fields\": {\"Front\": \"Question 4klk\", \"Back\": \"Answer 4\"}}",
                "{\"deckName\": \"My Deck\", \"modelName\": \"Basic\", \"fields\": {\"Front\": \"Question lk\", \"Back\": \"Answer 5\"}}",
                "{\"deckName\": \"My Deck\", \"modelName\": \"Basic\", \"fields\": {\"Front\": \"Question 6lk\", \"Back\": \"Answer 6\"}}",
                "{\"deckName\": \"My Deck\", \"modelName\": \"Basic\", \"fields\": {\"Front\": \"Question klk7\", \"Back\": \"Answer 7\"}}",
                "{\"deckName\": \"My Deck\", \"modelName\": \"Basic\", \"fields\": {\"Front\": \"Question 8lkl\", \"Back\": \"Answer 8\"}}",
                "{\"deckName\": \"My Deck\", \"modelName\": \"Basic\", \"fields\": {\"Front\": \"Question 9klk\", \"Back\": \"Answer 9\"}}",
                "{\"deckName\": \"My Deck\", \"modelName\": \"Basic\", \"fields\": {\"Front\": \"Question 10kl\", \"Back\": \"Answer 10\"}}"
        };


        for (String jsonObj : jsonObjects) {
            // Set up the HTTP request
            URL url = new URL("http://localhost:8765");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Construct the request body
            String requestBody = "{\"action\": \"addNote\", \"params\": {\"note\": " + jsonObj + "}}";

            // Print the request body to the console
            System.out.println(requestBody);

            // Write the request body to the output stream
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read the JSON response from the input stream
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

            // Disconnect the connection
            con.disconnect();
        }
    }
}





