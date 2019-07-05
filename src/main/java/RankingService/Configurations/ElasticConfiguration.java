package RankingService.Configurations;



import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;



public class ElasticConfiguration {
    public JestClient jestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(
                new HttpClientConfig.Builder("https://vpc-dev-search-common-es-flf4udvth6vfldieqvmuricqpm.ap-southeast-1.es.amazonaws.com")
                        .multiThreaded(true)
                        .defaultMaxTotalConnectionPerRoute(2)
                        .maxTotalConnection(10)
                        .build());
        return factory.getObject();
    }
}
