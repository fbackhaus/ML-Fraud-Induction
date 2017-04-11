package ml.induction

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PlaceController)
@Mock(Place)
class PlaceControllerSpec extends Specification {

    def populateValidParams(params) {
        if(params != null) {
            request.setParameter("json","{\n" +
                    "\t\"direccion\" : \"Evasio Garrone 6971\",\n" +
                    "\t\"radio\" : 500,\n" +
                    "\t\"tipos\" : \"food\"\n" +
                    "}")
        }
    }

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    void "test redirect"() {
        when:
            populateValidParams(params)
            controller.getCoordenadas()
        then:
            response.redirectedUrl == "/place/getPlacesNear"
    }
}
