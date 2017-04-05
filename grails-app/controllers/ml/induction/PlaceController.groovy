package ml.induction

import grails.converters.JSON
import groovy.json.JsonSlurper

class PlaceController {

    def index() { }

    def placeService

    def getPlacesNear() {
        def json = request.getJSON()
        def places = placeService.getPlacesNear(json.location,json.radius,json.types,json.name)
        places.results.each { place ->
            println place
        }
        render places as JSON
    }
}
