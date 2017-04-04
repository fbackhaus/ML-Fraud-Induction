package ml.induction

import grails.transaction.Transactional
import groovy.json.JsonSlurper

@Transactional
class MapService {

    def serviceMethod() {

    }

    def getCoordenadas(String direccion) {
        def url = new URL('https://maps.googleapis.com/maps/api/geocode/json?address=' +"'"+ direccion +"'"+'&key=AIzaSyDZAlOKVMj_BeZWwaXo2_yDuzdV1AtfrGU')
        def coordenadas = getJson(url).results
        def address = [direccion:coordenadas.formatted_address, latitud: coordenadas.geometry.location.lat, longitud: coordenadas.geometry.location.lng]
        return address
    }

    def getJson(URL url) {
        def connection = (HttpURLConnection)url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozilla/5.0")
        JsonSlurper json = new JsonSlurper()
        return json.parse(connection.getInputStream())
    }

    def getDireccion(String coordenadas) {
        def url = new URL('https://maps.googleapis.com/maps/api/geocode/json?latlng=' + coordenadas + '&key=AIzaSyDZAlOKVMj_BeZWwaXo2_yDuzdV1AtfrGU')
        return getJson(url).results.formatted_address
    }
}
