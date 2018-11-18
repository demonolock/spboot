package de.springboot.web;

import com.google.common.collect.ImmutableList;
import de.springboot.Application;
import de.springboot.model.StackoverflowWebsite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;


/**
 * User: victoria.shepard
 * Date: 18/11/2018
 * Time: 12:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class StackoverflowControllerIntegration {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public  void prepare(){
        mongoTemplate.dropCollection(StackoverflowWebsite.class);
        mongoTemplate.save(new StackoverflowWebsite("website1", "name", "title", "description", "url"));
        mongoTemplate.save(new StackoverflowWebsite("website2", "name", "title", "description", "url"));
    }

    @Test
    public void testGetListOfProveders() throws Exception {
        //test
        ResponseEntity<List<StackoverflowWebsite>> responceEntity = restTemplate.exchange(
                "http://localhost:8090/api/stackoverflow", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<StackoverflowWebsite>>() {
                });
        List<StackoverflowWebsite> actualList = responceEntity.getBody();
        //validate
        assertThat(actualList.size(), is(2));
        List<String> actualIds = actualList.stream()
                .map(StackoverflowWebsite::getId)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
        assertThat(actualIds, containsInAnyOrder("website1", "website2"));
    }

}