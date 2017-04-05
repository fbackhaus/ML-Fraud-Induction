package ml.induction

import grails.transaction.Transactional
import groovy.json.JsonSlurper

@Transactional
class PlaceService {

    private final def urlApi = 'https://maps.googleapis.com/maps/api/place/nearbysearch/json?'
    private final def key = 'AIzaSyDZAlOKVMj_BeZWwaXo2_yDuzdV1AtfrGU'

    def serviceMethod() {

    }

    def getJson(URL url) {
        def connection = (HttpURLConnection)url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozilla/5.0")
        JsonSlurper json = new JsonSlurper()
        return json.parse(connection.getInputStream())
    }

    def getPlacesNear(String location, String radius, String types, String name) {
        JsonSlurper json = new JsonSlurper()
        return json.parse(conectar(urlApi+'location='+location+'&radius='+radius+'&types='+types+'&name='+name+'&language=es'+'&key='+key, "GET").getInputStream())
    }


    def conectar(String ruta,String operacion){
        def url = new URL(ruta)
        def connection = (HttpURLConnection) url.openConnection()
        connection.setRequestMethod(operacion)
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozilla/5.0")
        return connection
    }
}
