package de.springboot.persistence;

import de.springboot.model.StackoverflowWebsite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StackoverflowWebsiteRepository extends MongoRepository<StackoverflowWebsite, String> {

/*    @Autowired
    private MongoTemplate mongoTemplate;

    public List<StackoverflowWebsite> findAll() {
        return mongoTemplate.findAll(StackoverflowWebsite.class);
    }*/

    List<StackoverflowWebsite> findByWebsite(String website); //sptingData
}
