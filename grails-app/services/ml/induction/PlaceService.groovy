package ml.induction

import grails.transaction.Transactional
import groovy.json.JsonSlurper

@Transactional
class PlaceService {

    private final def urlApiNear = 'https://maps.googleapis.com/maps/api/place/nearbysearch/json?'
    private final def urlApiText = 'https://maps.googleapis.com/maps/api/place/textsearch/json?'
    private final def key = 'AIzaSyDZAlOKVMj_BeZWwaXo2_yDuzdV1AtfrGU'

    def serviceMethod() {

    }


    def getPlacesNear(String location, String radius, String types) {
        JsonSlurper json = new JsonSlurper()
        def places = json.parse(conectar(urlApiNear+'location='+location+'&radius='+radius+'&types='+types+'&language=es'+'&key='+key, "GET").getInputStream())
        def lugares = []
        places.results.each { p ->
            def place = [name:p.name, placeId:p.place_id, rating:p.rating, types:p.types, vicinity:p.vicinity]
            println place
            lugares.add(place)
        }

        return lugares
    }


    def conectar(String ruta,String operacion){
        def url = new URL(ruta)
        println url.toString()
        def connection = (HttpURLConnection) url.openConnection()
        connection.setRequestMethod(operacion)
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozilla/5.0")
        return connection
    }

    def getPlacesByText(text) {
        JsonSlurper json = new JsonSlurper()
        def places = json.parse(conectar(urlApiText+'query='+text+'&key='+key, "GET").getInputStream())
        println places
        def lugares = []
        places.results.each { p ->
            def place = [name:p.name, placeId:p.place_id, rating:p.rating, types:p.types, address:p.formatted_address]
            println place
            lugares.add(place)
        }

        return lugares
    }
}
