package ml.induction

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.web.json.JSONObject
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PlaceService)
@Mock(Address)
class PlaceServiceSpec extends Specification {

    void setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    void "test getCoordenadas mock"() {
        given:
        service.placesClient = new MockRESTPlacesClient()
        when:
        def resp = service.getCoordenadas("Evasio Garrone 6971")
        then:
        assertEquals(["-31.3891093,-64.27409710000001", ["Evasio Garrone 6971, Córdoba, Argentina"]],resp)
    }

    void "test getCoordenadas"() {
        given:
        service.setPlacesClient(new RESTPlacesClient())
        service.placesClient.restClient = new RestBuilder()
        service.placesClient.grailsApplication = grailsApplication
        service.placesClient.key = 'AIzaSyDZAlOKVMj_BeZWwaXo2_yDuzdV1AtfrGU'
        service.placesClient.url = 'https://maps.googleapis.com/maps/api/'
        when:
        def resp = service.getCoordenadas("Evasio+Garrone+6971")
        then:
        assertEquals(["-31.3891093,-64.2740971", ["Evasio Garrone 6971, Córdoba, Argentina"]],resp)
    }
//    void "test getPlacesNear mock"() {
//        given:
//        service.placesClient = new MockRESTPlacesClient()
//        when:
//        def resp = service.getPlacesNear("Evasio Garrone 6971, Córdoba, Argentina","-31.3891093,-64.2740971",500,"food")
//        def ad = new JSONObject("{\n" +
//                "  \"class\": \"ml.induction.Address\",\n" +
//                "  \"id\": 8,\n" +
//                "  \"coordenadas\": \"-31.3891093,-64.2740971\",\n" +
//                "  \"name\": \"Evasio Garrone 6971, Córdoba, Argentina\",\n" +
//                "  \"places\": [\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 141,\n" +
//                "      \"address\": \"Avenida Ardizone 7009, Córdoba\",\n" +
//                "      \"name\": \"Orgonitos\",\n" +
//                "      \"placeId\": \"ChIJIzdbD3yYMpQRd5BC7Rd3RcU\",\n" +
//                "      \"rating\": 0,\n" +
//                "      \"types\": [\n" +
//                "        \"store\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 142,\n" +
//                "      \"address\": \"Gandhi 71, Don Bosco, Córdoba\",\n" +
//                "      \"name\": \"Kusi Lubricentro\",\n" +
//                "      \"placeId\": \"ChIJWd4lVh6fMpQRWkLyvC32gis\",\n" +
//                "      \"rating\": 3.5,\n" +
//                "      \"types\": [\n" +
//                "        \"car_repair\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 143,\n" +
//                "      \"address\": \"Esteban Pagliere 6983, Córdoba\",\n" +
//                "      \"name\": \"Estudio de Arquitectura\",\n" +
//                "      \"placeId\": \"ChIJob5xXjCfMpQRpJrerOUHxxE\",\n" +
//                "      \"rating\": 0,\n" +
//                "      \"types\": [\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 144,\n" +
//                "      \"address\": \"Aquiles Pedrolini 6934, Córdoba\",\n" +
//                "      \"name\": \"Grafica MatPRO\",\n" +
//                "      \"placeId\": \"ChIJzXqnUeKeMpQRWL8oBN4JUIk\",\n" +
//                "      \"rating\": 4,\n" +
//                "      \"types\": [\n" +
//                "        \"store\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 145,\n" +
//                "      \"address\": \"Córdoba\",\n" +
//                "      \"name\": \"Córdoba\",\n" +
//                "      \"placeId\": \"ChIJaVuPR1-YMpQRkrBmU5pPorA\",\n" +
//                "      \"rating\": 0,\n" +
//                "      \"types\": [\n" +
//                "        \"locality\",\n" +
//                "        \"political\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 146,\n" +
//                "      \"address\": \"Fransisco Sebastiani 6962, Don Bosco, Córdoba\",\n" +
//                "      \"name\": \"Grido Helado\",\n" +
//                "      \"placeId\": \"ChIJlXO2vx2fMpQRBBuBGpugrvY\",\n" +
//                "      \"rating\": 3.9,\n" +
//                "      \"types\": [\n" +
//                "        \"food\",\n" +
//                "        \"store\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 147,\n" +
//                "      \"address\": \"305,, José Fagnano, Córdoba\",\n" +
//                "      \"name\": \"Mona Queda - Impresiones Serigráficas\",\n" +
//                "      \"placeId\": \"ChIJ26DUiR2fMpQRcambVNJ_j0w\",\n" +
//                "      \"rating\": 0,\n" +
//                "      \"types\": [\n" +
//                "        \"store\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 148,\n" +
//                "      \"address\": \"Juan Cagliero 142, Don Bosco, Córdoba\",\n" +
//                "      \"name\": \"Peluquería Unisex Valeria\",\n" +
//                "      \"placeId\": \"ChIJmS4ld-KeMpQRmRVhQXdbkmc\",\n" +
//                "      \"rating\": 4,\n" +
//                "      \"types\": [\n" +
//                "        \"hair_care\",\n" +
//                "        \"health\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 149,\n" +
//                "      \"address\": \"Francisco Sebastiani 6862, Don Bosco, Córdoba\",\n" +
//                "      \"name\": \"Mandinga\",\n" +
//                "      \"placeId\": \"ChIJPRSFlR2fMpQRmCqjjdAa0hc\",\n" +
//                "      \"rating\": 4.6,\n" +
//                "      \"types\": [\n" +
//                "        \"restaurant\",\n" +
//                "        \"food\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 150,\n" +
//                "      \"address\": \"Juan Cagliero 148, Don Bosco, Córdoba\",\n" +
//                "      \"name\": \"Lotería de Córdoba Agencia Oficial 2356\",\n" +
//                "      \"placeId\": \"ChIJvUwpd-KeMpQR9vbtDc-U0eI\",\n" +
//                "      \"rating\": 0,\n" +
//                "      \"types\": [\n" +
//                "        \"store\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 151,\n" +
//                "      \"address\": \"Esteban Pagliere 7004 Local 4, Don Bosco, Córdoba\",\n" +
//                "      \"name\": \"Lomitos 2x1\",\n" +
//                "      \"placeId\": \"ChIJZxf4fx2fMpQRU75cmHeC7Vo\",\n" +
//                "      \"rating\": 3.6,\n" +
//                "      \"types\": [\n" +
//                "        \"meal_takeaway\",\n" +
//                "        \"restaurant\",\n" +
//                "        \"food\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 152,\n" +
//                "      \"address\": \"Av. Santiago Costamagna, Don Bosco, Córdoba\",\n" +
//                "      \"name\": \"Centro de Atención Primaria de La Salud Barrio Don Bosco No. 77\",\n" +
//                "      \"placeId\": \"ChIJxY4sN-KeMpQRxUyfJxZf8Q0\",\n" +
//                "      \"rating\": 3,\n" +
//                "      \"types\": [\n" +
//                "        \"hospital\",\n" +
//                "        \"local_government_office\",\n" +
//                "        \"health\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 153,\n" +
//                "      \"address\": \"Mario Migone 7034, Córdoba\",\n" +
//                "      \"name\": \"Educar in Company\",\n" +
//                "      \"placeId\": \"ChIJl0RT7eGeMpQRy8ldnO3Ur_I\",\n" +
//                "      \"rating\": 0,\n" +
//                "      \"types\": [\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 154,\n" +
//                "      \"address\": \"Argentina\",\n" +
//                "      \"name\": \"Costamagna 7000\",\n" +
//                "      \"placeId\": \"ChIJrT4sMuKeMpQRamyRkt7CQwc\",\n" +
//                "      \"rating\": 0,\n" +
//                "      \"types\": [\n" +
//                "        \"bus_station\",\n" +
//                "        \"transit_station\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 155,\n" +
//                "      \"address\": \"Mario Mignone, Don Bosco, Córdoba\",\n" +
//                "      \"name\": \"Rec Insumos & Dvd Club\",\n" +
//                "      \"placeId\": \"ChIJb1cyTOKeMpQRwbbTZreXpb8\",\n" +
//                "      \"rating\": 0,\n" +
//                "      \"types\": [\n" +
//                "        \"electronics_store\",\n" +
//                "        \"store\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 156,\n" +
//                "      \"address\": \"Mahatma Gandhi 93 Local 3, Don Bosco, Córdoba\",\n" +
//                "      \"name\": \"Surcolor Pinturerías\",\n" +
//                "      \"placeId\": \"ChIJubRVRR6fMpQRmtWAEMBVneo\",\n" +
//                "      \"rating\": 0,\n" +
//                "      \"types\": [\n" +
//                "        \"home_goods_store\",\n" +
//                "        \"store\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 157,\n" +
//                "      \"address\": \"89,, Mahatma Gandhi, Córdoba\",\n" +
//                "      \"name\": \"El Dogo Crossfit\",\n" +
//                "      \"placeId\": \"ChIJ4_r5UB6fMpQRBHX6oeXzc3s\",\n" +
//                "      \"rating\": 5,\n" +
//                "      \"types\": [\n" +
//                "        \"gym\",\n" +
//                "        \"health\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 158,\n" +
//                "      \"address\": \"6924, Francisco Sebastiani, Córdoba\",\n" +
//                "      \"name\": \"T-Tech Soluciones Informáticas\",\n" +
//                "      \"placeId\": \"ChIJserrzR2fMpQRq2AxKa4gSjk\",\n" +
//                "      \"rating\": 0,\n" +
//                "      \"types\": [\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 159,\n" +
//                "      \"address\": \"José Fagnano, Córdoba\",\n" +
//                "      \"name\": \"Aromas\",\n" +
//                "      \"placeId\": \"ChIJ81jb7-GeMpQRINTSpwP1ZoA\",\n" +
//                "      \"rating\": 5,\n" +
//                "      \"types\": [\n" +
//                "        \"home_goods_store\",\n" +
//                "        \"store\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"class\": \"ml.induction.Place\",\n" +
//                "      \"id\": 160,\n" +
//                "      \"address\": \"Cardenal Cagliero 109, Don Bosco, Córdoba\",\n" +
//                "      \"name\": \"Ligur 1279\",\n" +
//                "      \"placeId\": \"ChIJeYGphh2fMpQRCQAcs0Z93a4\",\n" +
//                "      \"rating\": 4.1,\n" +
//                "      \"types\": [\n" +
//                "        \"restaurant\",\n" +
//                "        \"food\",\n" +
//                "        \"point_of_interest\",\n" +
//                "        \"establishment\"\n" +
//                "      ]\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"radio\": 500,\n" +
//                "  \"tipos\": \"food\"\n" +
//                "}")
//        then:
//        assertEquals(ad, resp)
//    }
//

}
