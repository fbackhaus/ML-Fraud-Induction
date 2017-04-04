<%--
  Created by IntelliJ IDEA.
  User: fbackhaus
  Date: 4/4/17
  Time: 12:16
--%>

<!DOCTYPE html>
<html>
<head>
    <script
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
          crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <title>Maps</title>
</head>
<body bgcolor="#ffffff">
<div class="row">
    <div class="col-sm-6 col-sm-offset-3">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <span class="glyphicon glyphicon-filter"></span>Filtros
            </div>
            <div class="panel-body">
                <label for="direccion" class="col-sm-4">Direccion o Lugar</label> <input
                    type="text" class="col-sm-8" id="direccion"
                    placeholder="Ingrese una direccion" value="">
                <label for="radio" class="col-sm-4">Radio (mts)</label> <input
                    type="text" class="col-sm-8" id="radio"
                    placeholder="Ingrese el radio en metros" value="0">
            </div>
            <div class="panel-footer">
                <button class="btn btn-primary" onclick="obtenerMiUbicacion()">
                    <span class="glyphicon glyphicon-map-marker"></span>Usar mi
                ubicación!
                </button>
                <button class="btn btn-primary" onclick="geolocalizarDireccion()">
                    <span class="glyphicon glyphicon-map-marker"></span>Usar direccion
                </button>
            </div>
        </div>
    </div>
</div>

<div class="col-sm-6 col-sm-offset-3" id="pnlMsj">
    <div class="progress col-sm-8 col-sm-offset-2">
        <div
                class="progress-bar progress-bar-success progress-bar-striped active"
                role="progressbar" aria-valuenow="100" aria-valuemin="0"
                aria-valuemax="100" style="width: 100%"></div>
    </div>
    <center>
        <h2 id="msj">Buscando ...</h2>
    </center>
</div>
<div id="map" style="width:50%;height:475px;" class="col-sm-offset-3" hidden="true"></div>
<script type="text/javascript">
    function mostrarCargando(){
        $("#pnlMsj").show();
    }
    function ocultarCargando(){
        $("#pnlMsj").hide();
    }
    function setMensajeCargando(msj){
        $("#msj").text(msj);
    }

    function obtenerMiUbicacion(){
        if(navigator.geolocation){
            setMensajeCargando("Obteniendo su geolocalización.");
            mostrarCargando();
            navigator.geolocation.getCurrentPosition(funcionSi, funcionNo);
        }
        else{
            console.log("No soporta geolocalización");
            ocultarCargando();
        }
    }


    function funcionSi(resultado) {
        console.log(resultado);
        var latitud = resultado.coords.latitude;
        var longitud = resultado.coords.longitude;
        var coordenadas = latitud.toString() + "," + longitud.toString();
        console.log(coordenadas)
        obtenerDireccion(coordenadas, latitud, longitud)
    }

    function funcionNo(err) {
        console.log("error");
        console.log(err);
    }

    function obtenerDireccion(coordenadas, latitud, longitud) {
        <g:remoteFunction action="getDireccion"
    controller="map"
    params="\'coordenadas=\'+ coordenadas"
    update="direccion"
    onLoading="mostrarCargando()"
    onSuccess="funcionExitoDireccion(data,latitud, longitud)"
    onFailure="funcionNo()" />
    }

    function funcionExitoDireccion(resultado, latitud, longitud) {
        ocultarCargando();
        console.log(resultado);
        document.getElementById("direccion").value = resultado;
        initMap(latitud, longitud)
        document.getElementById("map").hidden = false;
    }

    function initMap(latitud, longitud) {
        // Create a map object and specify the DOM element for display.
        var latlong = {lat: latitud, lng: longitud}
        var map = new google.maps.Map(document.getElementById('map'), {
            center: latlong,
            scrollwheel: true,
            zoom: 11
        });

        var marker = new google.maps.Marker({
            position: latlong,
            map: map
        });
    }
    function geolocalizarDireccion(){
        var direccion = $("#direccion").val();
        <g:remoteFunction action="getCoordenadas"
    controller="map"
    params= "\'direccion=\'+ direccion"
    update="map"
    onLoading="funcionCargandoCoordenadas()"
    onSuccess="funcionExitoCoordenadas(data)"
    onFailure="funcionNo()"/>
    }
    function funcionExitoCoordenadas(resultado){
        console.log(resultado.latitud)
        console.log(resultado.longitud)
        ocultarCargando();
        var radio = $("#radio").val();
        initMap(resultado.latitud[0], resultado.longitud[0])
        document.getElementById("map").hidden = false;
    }
    function funcionCargandoCoordenadas(){
        ocultarCargando();
        setMensajeCargando("Geolocalizando dirección.");
        mostrarCargando();
    }

    ocultarCargando();

</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAo5DeDx7a-WvUcU69RWwFTXqI5XmO3Fh4"
        async defer></script>
</body>
</html>