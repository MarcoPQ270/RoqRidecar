<?php 
    include_once("./BaseDatos.php");
    header('Content-type: application/json; charset=utf-8');
    $method=$_SERVER['REQUEST_METHOD'];
    $response = array();
    $result = consultaViajes();
    if (!empty($result)) {
        $response["success"] = "202";
        $response["message"] = "Viajes encontrados";

        $response["viaje"] = array();
        foreach ($result as $tupla)
        {
            array_push($response["viaje"], $tupla);
        }
    }
    else{
        $response["success"] = "204";
        $response["message"] = "Viajes no encontrados";
        header($_SERVER['SERVER_PROTOCOL'] . " 500  Error interno del servidor ");
    }
    echo json_encode($response);
?>