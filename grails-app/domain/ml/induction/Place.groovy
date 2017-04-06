package ml.induction

class Place {

    String name
    String placeId
    Float rating
    ArrayList<String> types
    String address

    static mapWith = "mongo"

    static belongsTo = [address: Address]

    static constraints = {
    }

    static mapping = {
        database "Induction"
        collection "places"
    }
}