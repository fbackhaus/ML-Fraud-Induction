class UrlMappings {

	static mappings = {

		"400"(controller:'error', action:'treatExceptions')
		"401"(controller:'error', action:'treatExceptions')
		"403"(controller:'error', action:'treatExceptions')
		"404"(controller:'error', action:'treatExceptions')
		"500"(controller:'error', action:'treatExceptions')

		"/place/getCoordenadas"(controller: "place") {
			action = [
					GET: "methodNotAllowed",
					POST:"getCoordenadas",
					PUT:"methodNotAllowed",
					DELETE:"methodNotAllowed"
			]
		}
		"/place/getPlacesNear"(controller: "place") {
			action = [
			        GET: "methodNotAllowed",
					POST: "methodNotAllowed",
					PUT: "methodNotAllowed",
					DELETE: "methodNotAllowed"
			]
		}
	}
}
