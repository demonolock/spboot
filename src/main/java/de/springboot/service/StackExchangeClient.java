package de.springboot.service;

import de.springboot.model.Item;
import de.springboot.model.SiteDto;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * User: victoria.shepard
 * Date: 18/11/2018
 * Time: 14:53
 */
@Component
public class StackExchangeClient {

    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
    private RestTemplate restTemplate = new RestTemplate(requestFactory);

    public List<Item> getSites() {
        String url = "https://api.stackexchange.com/2.2/sites?page=1&pagesize=9999&filter=!)Qpa1bGM8FLTo3Ij*9LwcfFm";
        try {
            SiteDto response = restTemplate.getForObject(new URI(url), SiteDto.class);
            return response.getItems();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

}
