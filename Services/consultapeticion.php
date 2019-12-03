<?php 
    include_once("./BaseDatos.php");
    header('Content-type: application/json; charset=utf-8');
    $method=$_SERVER['REQUEST_METHOD'];
    $response = array();
    $result = consultaPeticiones();
    if (!empty($result)) {
        $response["success"] = "202";
        $response["message"] = "Peticiones encontrados";

        $response["peticion"] = array();
        foreach ($result as $tupla)
        {
            array_push($response["peticion"], $tupla);
        }
    }
    else{
        $response["success"] = "204";
        $response["message"] = "Peticiones no encontradas";
        header($_SERVER['SERVER_PROTOCOL'] . " 500  Error interno del servidor ");
    }
    echo json_encode($response);
?>