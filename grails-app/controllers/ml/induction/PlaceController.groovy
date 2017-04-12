package ml.induction

import grails.converters.JSON

import com.ml.exceptions.BadRequestException
import com.ml.exceptions.MethodNotAllowedException

import javax.servlet.http.HttpServletResponse

class PlaceController {
    def index() { }

    def placeService

    def redisService

    def getPlacesNear(name,coordenadas,radio,tipos) {
        def apiResponse = redisService.getApiResponse(coordenadas, radio, tipos)
        if(apiResponse == null) {
            def address = placeService.getPlacesNear(name, coordenadas, radio, tipos)
            JSON.use('deep') {
                redisService.setAddress(address)
                render address as JSON
            }
        }
        else {
            render(text:apiResponse,contentType: "application/json")
        }


    }
    def getCoordenadas() {
        def json = request.JSON
        if(validateJSON(json)) {
        def dir = json.address.replace(" ","+")
        def direccion = placeService.getCoordenadas(dir)
        def radio = json.radius
        def tipos = json.types
        return getPlacesNear(direccion[1],direccion[0],radio,tipos)
        }
    }

    def validateJSON(json) {
        if(!json) {
            throw new BadRequestException ("No data received")
        }
        if(!json.address) {
            throw new BadRequestException ("Address field empty")
        }
        if(!json.radius) {
            throw new BadRequestException ("Radius field empty")
        }
        if(!json.types) {
            throw new BadRequestException ("Types field empty")
        }
        if(!(json.address instanceof String)) {
            throw new BadRequestException ("Invalid address")
        }
        if(!(json.radius instanceof Number)) {
            throw new BadRequestException ("Invalid radius")
        }
        if(!(json.types instanceof String)) {
            throw new BadRequestException ("Invalid types")
        }
        return true

    }

    def methodNotAllowed = {
        throw new MethodNotAllowedException("Method not allowed")
    }
        }

