package ml.induction

import grails.converters.JSON

import java.util.ArrayList

class PlaceController {

    def index() { }

    def placeService

    def redisService

    def getPlacesNear() {

        def apiResponse = redisService.getApiResponse(params.coordenadas, params.radio, params.tipos)
        if(apiResponse == null) {
            def address = placeService.getPlacesNear(params.name, params.coordenadas, params.radio, params.tipos)
            JSON.use('deep') {
                redisService.setAddress(address)
                render address as JSON
            }
        }
        else {
            render(text:apiResponse,contentType: "application/json")
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
        redirect(controller: "Place",action: "getPlacesNear", params: [name:direccion[1],coordenadas: direccion[0],radio:radio,tipos:tipos])
    }
}
