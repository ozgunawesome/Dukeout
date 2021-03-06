package services.ozzy.dukeout;

import org.springframework.web.client.AsyncRestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Özgün Ayaz on 2017-03-12.
 * License: CC BY-SA 4.0
 * For more info visit https://creativecommons.org/licenses/by-sa/4.0/
 */
abstract class SearchEngine {

    final Properties properties;
    final AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();

    SearchEngine() {
        try (InputStream input = SearchEngine.class.getClassLoader().getResourceAsStream("secrets.properties")) {
            this.properties = new Properties();
            this.properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    abstract CompletableFuture<SearchResult> search(String searchTerm);

    abstract String getName();

    abstract SearchEngines getType();

}
