package ru.folkland.searchsof;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class searchController {

    private final String apiUrl = "http://api.stackexchange.com/2.2/search?order=desc&sort=activity&site=stackoverflow&intitle=%s";

    private final CloseableHttpClient client = HttpClientBuilder.create().build();

    @RequestMapping("/searchingSOF")
    public String getData(@RequestParam(value="title") String title) throws IOException {
        String url = String.format(apiUrl, title);
        HttpGet httpGet = new HttpGet(url);
        String result = null;
        try (CloseableHttpResponse response = client.execute(httpGet)) {
            System.out.println("Response: " + response.getStatusLine().getStatusCode());
            result = EntityUtils.toString(response.getEntity());
        }
        return result;
    }
}
