package ml.induction

import grails.transaction.Transactional
import grails.util.Environment
import groovy.json.JsonSlurper
import org.codehaus.groovy.grails.web.json.JSONObject

import java.nio.file.Files
import java.nio.file.Paths

@Transactional
class PlaceService {

    private final def urlApiNear = 'https://maps.googleapis.com/maps/api/place/nearbysearch/json?'
    private final def urlApiText = 'https://maps.googleapis.com/maps/api/place/textsearch/json?'
    private final def urlApiGeo = 'https://maps.googleapis.com/maps/api/geocode/json?'
    private final def key = 'AIzaSyDZAlOKVMj_BeZWwaXo2_yDuzdV1AtfrGU'


    def getPlacesNear(name,coordenadas,radius,types) {
        JsonSlurper json = new JsonSlurper()
        def places
        switch(Environment.current){

            case Environment.DEVELOPMENT: case Environment.TEST:
                places = getMock(
                        "/Users/fbackhaus/IdeaProjects/ML-Fraud-Induction/ML-Fraud-Induction/" +
                                "/grails-app/assets/mocks/places.json").results
                break

            case Environment.PRODUCTION:
                places = json.parse(conectar(urlApiNear+'location='+coordenadas+'&radius='+radius+'&types='+types+'&language=es'+'&key='+key, "GET").getInputStream())
                break

            default:
                break
        }

        def lugares = []
        places.results.each { p -> if (p.rating == null) {
                p.rating = 0
            }
            Place place = new Place(name:p.name, placeId:p.place_id, rating:p.rating, types:p.types, address:p.vicinity)
            lugares.add(place)
        }


        Address address = new Address(name:name, coordenadas:coordenadas, radio: radius, tipos:types, places: lugares)
        address.save()
        return address
    }


    def conectar(ruta,operacion){
        def url = new URL(ruta)
        def connection = (HttpURLConnection) url.openConnection()
        connection.setRequestMethod(operacion)
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozilla/5.0")
        return connection
    }

    def getPlacesByText(text) {
        JsonSlurper json = new JsonSlurper()
        def places = json.parse(conectar(urlApiText+'query='+text+'&key='+key, "GET").getInputStream())
        def lugares = []
        places.results.each { p ->
            Place place = new Place(name:p.name, placeId:p.place_id, rating:p.rating, types:p.types, address:p.formatted_address)
            lugares.add(place)
        }

        return lugares
    }

    def getCoordenadas(String direccion) {
        def address
        JsonSlurper json = new JsonSlurper()
        switch(Environment.current){

            case Environment.DEVELOPMENT: case Environment.TEST:
                address = getMock(
                        "/Users/fbackhaus/IdeaProjects/ML-Fraud-Induction/ML-Fraud-Induction/" +
                                "/grails-app/assets/mocks/coordinates.json").results
                break

            case Environment.PRODUCTION:
                address = json.parse(conectar(urlApiGeo+'address='+"'"+ direccion + "'" +'&key='+key, "GET").getInputStream()).results
                break

            default:
                break
        }
        def coordenadas = address.geometry.location.lat[0]+","+address.geometry.location.lng[0]
        def name = address.formatted_address[0]
        return [coordenadas,name]
    }

    def getMock(url) {
        String mockData = new String(Files.readAllBytes(Paths.get("${url}")))
        JsonSlurper json = new JsonSlurper()
        return json.parseText(mockData)
    }

}
