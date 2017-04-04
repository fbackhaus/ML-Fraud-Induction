package ml.induction

import grails.converters.JSON

class MapController {

    def index() { }

    def mapService

    def getCoordenadas() {
        def direccion = params.direccion.toString().replace(" ", "+")
        def coordenadas = mapService.getCoordenadas(direccion)
        render coordenadas as JSON
    }

    def getDireccion() {
        def coordenadas = params.coordenadas.toString()
        def direccion = mapService.getDireccion(coordenadas)
        render direccion[0]
    }
}
