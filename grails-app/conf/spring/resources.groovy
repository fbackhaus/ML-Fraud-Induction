// Place your Spring DSL code here
import grails.plugins.rest.client.RestBuilder
import grails.util.Environment
import ml.induction.MockRESTPlacesClient
import ml.induction.RESTPlacesClient

beans = {
    restClient(RestBuilder) { bean ->
        bean.scope = 'singleton'
    }


    Environment.executeForCurrentEnvironment {
        production {
            placesClient(RESTPlacesClient) { bean ->
                bean.scope = 'singleton'
                restClient = ref("restClient")
            }
        }
        development {
            placesClient(MockRESTPlacesClient) { bean ->
                bean.scope = 'singleton'
            }
        }
        test {
            placesClient(MockRESTPlacesClient) { bean ->
                bean.scope = 'singleton'
            }
        }

    }
}
