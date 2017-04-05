package ml.induction

import grails.converters.JSON
import java.util.ArrayList

class PlaceController {

    def index() { }

    def placeService

    def getPlacesNear() {
        def json = request.getJSON()
        def places = placeService.getPlacesNear(json.location,json.radius,json.types)

        render places as JSON
    }

    def getPlacesByText() {
        def json = request.getJSON()
        def text = json.text.replace(" ", "+")
        def places = placeService.getPlacesByText(text)

        render places as JSON
    }
}
