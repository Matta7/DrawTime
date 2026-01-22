package models.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import  static models.constants.project.GitHubURLConstants.*;

public class APIRequestService {

    private final JSONService jsonService;

    public APIRequestService() {
        jsonService = new JSONService();
    }

    /**
     * Call an API and get a response from a URL
     *
     * @param stringUrl URL to call
     * @return Response of the API call
     */
    private String getResponse(String stringUrl) throws IOException {
        URL url = URI.create(stringUrl).toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Accept", "application/vnd.github+json");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }

    /**
     * Retrieve from GitHub repository the latest release version
     * @return Latest release version
     */
    public String getLatestReleaseVersion() throws IOException {
        String response = getResponse(GITHUB_API_DRAWTIME_LATEST_RELEASE_URL);
        jsonService.loadJSON(response);
        return jsonService.getFieldValueAsString("tag_name");
    }
}
