package ml.induction

import grails.converters.JSON
import grails.transaction.Transactional
import redis.clients.jedis.Jedis

@Transactional
class RedisService {

    def jedis

    def serviceMethod() {

    }

    def getApiResponse(coordenadas, radio, tipos) {
        jedis = new Jedis("localhost")
        def key = coordenadas+"|"+radio+"|"+tipos
        if(jedis.exists(key)) {
            return jedis.get(key)
        }
        else {
            return null
        }
    }

    def setAddress(address) {
        def key = address.coordenadas+"|"+address.radio+"|"+address.tipos
        def json = address as JSON
        jedis.set(key,json.toString())
    }

}
