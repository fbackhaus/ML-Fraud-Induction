package ml.induction

import grails.transaction.Transactional
class PlaceService {
    def placesClient

    @Transactional
    def getPlacesNear(name,coordenadas,radius,types) {
        def places = placesClient.getPlacesNear(name,coordenadas,radius,types)
        def lugares = []
        places.each { p -> if (p.rating == null) {
                p.rating = 0
            }
            Place place = new Place(name:p.name, placeId:p.place_id, rating:p.rating, types:p.types, address:p.vicinity)
            lugares.add(place)
        }


        Address address = new Address(name:name, coordenadas:coordenadas, radio: radius, tipos:types, places: lugares)
        address.save()
        return address
    }

    def getCoordenadas(String direccion) {
        def address = placesClient.getCoordenadas(direccion)
        def coordenadas = address.geometry.location.lat[0]+","+address.geometry.location.lng[0]
        def name = address.formatted_address
        return [coordenadas,name]
    }

}
