package ml.induction

import grails.converters.JSON

import java.util.ArrayList

class PlaceController {

    def index() { }

    def placeService

    def getPlacesNear() {

        def address = placeService.getPlacesNear(params.name, params.coordenadas, params.radio,params.tipos)
        JSON.use('deep') {
            render address as JSON
        }

    }

    def getPlacesByText() {
        def json = request.getJSON()
        def text = json.text.replace(" ", "+")
        def places = placeService.getPlacesByText(text)
        render places as JSON
    }

    def getCoordenadas() {
        def json = request.getJSON()
        def dir = json.direccion.replace(" ","+")
        def direccion = placeService.getCoordenadas(dir)
        def radio = json.radio
        def tipos = json.tipos
        Address address = new Address(name:direccion[1], coordenadas: direccion[0], radio:  radio,tipos:  tipos)
        redirect(controller: "Place",action: "getPlacesNear", params: [name:direccion[1],coordenadas: direccion[0],radio:radio,tipos:tipos])
    }
}
