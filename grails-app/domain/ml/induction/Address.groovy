package ml.induction

class Address {

    String name
    int radio
    String tipos
    String coordenadas

    static mapWith = "mongo"

    static hasMany = [places:Place]

    static constraints = {
    }

    static mapping = {
        database "Induction"
        collection "addresses"
    }
}
