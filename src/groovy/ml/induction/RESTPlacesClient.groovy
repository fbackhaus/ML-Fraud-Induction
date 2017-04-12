package ml.induction

import grails.util.Holders
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.codehaus.groovy.grails.web.json.JSONObject

/**
 * Created by fbackhaus on 10/4/17.
 */
class RESTPlacesClient {

    def restClient
    def grailsApplication = new DefaultGrailsApplication()
    def key = grailsApplication.config.key
    def url = grailsApplication.config.baseURL

    def getPlacesNear(name,coordenadas,radius,types) {
        def uri = 'place/nearbysearch/json?location='+coordenadas+'&radius='+radius+'&key='+key
        def dir = url.toString()+uri.toString()
        def resp = restClient.get(dir) {
            accept JSONObject, 'application/json'
        }
        return resp.responseEntity.body.results
    }

    def getCoordenadas(direccion) {
        def uri = 'geocode/json?address='+direccion+'&key='+key
        def dir = url.toString()+uri.toString()
        def resp = restClient.get(dir) {
            accept JSONObject, 'application/json'
        }
        return resp.responseEntity.body.results
    }
}
