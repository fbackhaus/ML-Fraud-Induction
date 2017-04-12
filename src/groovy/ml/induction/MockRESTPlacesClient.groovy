package ml.induction

import groovy.json.JsonSlurper
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by fbackhaus on 11/4/17.
 */
class MockRESTPlacesClient {

    def getPlacesNear(name, coordenadas, radius, types) {
        return getMock(
                "/Users/fbackhaus/IdeaProjects/ML-Fraud-Induction/ML-Fraud-Induction/" +
                        "/grails-app/assets/mocks/places.json").results
    }

    def getCoordenadas(direccion) {
        return getMock(
                        "/Users/fbackhaus/IdeaProjects/ML-Fraud-Induction/ML-Fraud-Induction/" +
                               "/grails-app/assets/mocks/coordinates.json").results
    }

    def getMock(url) {
        String mockData = new String(Files.readAllBytes(Paths.get("${url}")))
        JsonSlurper json = new JsonSlurper()
        return json.parseText(mockData)
    }

}
