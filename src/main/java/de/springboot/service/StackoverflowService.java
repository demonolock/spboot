package de.springboot.service;

import com.google.common.collect.ImmutableList;
import de.springboot.model.Item;
import de.springboot.model.StackoverflowWebsite;
import de.springboot.persistence.StackoverflowWebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
public class StackoverflowService {

    @Autowired private StackoverflowWebsiteRepository repository;
    @Autowired private StackExchangeClient stackExchangeClient;

    public List<StackoverflowWebsite> findAll() {
        return stackExchangeClient.getSites().stream()
                .map(this::toStackoverflowWebsite)
                .filter(this::ignoreMeta)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private boolean ignoreMeta(StackoverflowWebsite stackoverflowWebsite) {
        return !stackoverflowWebsite.getId().contains("meta.");
    }

    private StackoverflowWebsite toStackoverflowWebsite(@NotNull Item input) {
        return new StackoverflowWebsite(
                input.getSite_url().substring("https://".length(), input.getSite_url().length() - ".com".length()),
                input.getSite_url(),
                input.getFavicon_url(),
                input.getName(),
                input.getAudience());
    }

    //private static List<StackoverflowWebsite> items = new ArrayList<>();

    //Hardcore
  /*  static {
        items.add(new StackoverflowWebsite("12345", "http://stackoverflow.com",
                "http://cdn.static.net/Sites/stackoverflow/img/favicon.ico", "Stack overflow (StackExchange)",
                "for professional"));
        items.add(new StackoverflowWebsite("11111", "http://stackoverflow.com",
                "http://cdn.static.net/Sites/stackoverflow/img/favicon.ico", "Stack overflow (StackExchange)",
                "for professional"));
    }*/

/*    @PostConstruct
    public void init(){
        repository.saveAll(items);
    }*/
//Mongo db
  /*  public List<StackoverflowWebsite> findAll() {
        return repository.findAll();
    }*/
}
