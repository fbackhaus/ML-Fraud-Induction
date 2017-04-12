package ml.induction

import com.ml.exceptions.BadRequestException
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PlaceController)
class PlaceControllerSpec extends Specification {


    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }

    void "testGetCoordenadasWithNullJson"() {
        given:
            Exception exception
        when:
            request.JSON = ''
            request.method = 'POST'
        try {
            controller.getCoordenadas()
        }
        catch(Exception e) {
            exception = e
        }
        then:
        assertNotNull(exception)
        assertTrue(exception instanceof BadRequestException)
        assertEquals(400, exception.status)
        assertEquals("No data received", exception.message)
    }
    void "testGetCoordenadasWithoutAddress"() {
        given:
            Exception exception
        when:
        request.JSON = '{\n' +
                '\t"radius": 500,\n' +
                '\t"types": "food"\n' +
                '}'
        request.method = 'POST'
        try{
        controller.getCoordenadas()
        }
        catch(Exception e) {
            exception = e
        }
        then:
        assertNotNull(exception)
        assertTrue(exception instanceof BadRequestException)
        assertEquals(400, exception.status)
        assertEquals("Address field empty", exception.message)
    }
    void "testGetCoordenadasWithoutRadius"() {
        given:
        Exception exception
        when:
        request.JSON = '{\n' +
                '\t"address": "Evasio Garrone 6971",\n' +
                '\t"types": "food"\n' +
                '}'
        request.method = 'POST'
        try{
            controller.getCoordenadas()
        }
        catch(Exception e) {
            exception = e
        }
        then:
        assertNotNull(exception)
        assertTrue(exception instanceof BadRequestException)
        assertEquals(400, exception.status)
        assertEquals("Radius field empty", exception.message)
    }
    void "testGetCoordenadasWithoutTypes"() {
        given:
        Exception exception
        when:
        request.JSON = '{\n' +
                '\t"radius": 500,\n' +
                '\t"address": "Evasio Garrone 6971"\n' +
                '}'
        request.method = 'POST'
        try {
            controller.getCoordenadas()
        }
        catch(Exception e) {
            exception = e
        }
        then:
        assertNotNull(exception)
        assertTrue(exception instanceof BadRequestException)
        assertEquals(400, exception.status)
        assertEquals("Types field empty", exception.message)
    }
    void "testGetCoordenadasWithInvalidTypes"() {
        given:
        Exception exception
        when:
        request.JSON = '{\n' +
                '\t"address": "Evasio Garrone 6971",\n' +
                '\t"radius": 500,\n' +
                '\t"types": 100\n' +
                '}'
        request.method = 'POST'
        try {
            controller.getCoordenadas()
        }
        catch(Exception e) {
            exception = e
        }
        then:
        assertNotNull(exception)
        assertTrue(exception instanceof BadRequestException)
        assertEquals(400, exception.status)
        assertEquals("Invalid types", exception.message)
    }
    void "testGetCoordenadasWithInvalidAddress"() {
        given:
        Exception exception
        when:
        request.JSON = '{\n' +
                '\t"address": 250,\n' +
                '\t"radius": 500,\n' +
                '\t"types": "food"\n' +
                '}'
        request.method = 'POST'
        try {
            controller.getCoordenadas()
        }
        catch(Exception e) {
            exception = e
        }
        then:
        assertNotNull(exception)
        assertTrue(exception instanceof BadRequestException)
        assertEquals(400, exception.status)
        assertEquals("Invalid address", exception.message)
    }
    void "testGetCoordenadasWithInvalidRadius"() {
        given:
        Exception exception
        when:
        request.JSON = '{\n' +
                '\t"address": "Evasio Garrone 6971",\n' +
                '\t"radius": "Big",\n' +
                '\t"types": "food"\n' +
                '}'
        request.method = 'POST'
        try {
            controller.getCoordenadas()
        }
        catch(Exception e) {
            exception = e
        }
        then:
        assertNotNull(exception)
        assertTrue(exception instanceof BadRequestException)
        assertEquals(400, exception.status)
        assertEquals("Invalid radius", exception.message)
    }
}
