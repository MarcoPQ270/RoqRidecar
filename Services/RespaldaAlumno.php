<?php 
    include_once("./BaseDatos.php");
    header('Content-type: application/json; charset=utf-8');
    $method=$_SERVER['REQUEST_METHOD'];

    $obj = json_decode( file_get_contents('php://input') );   
    $objArr = (array)$obj;
	if (empty($objArr))
    {
		$response["success"] = 422;  //No encontro información 
        $response["message"] = "Error: checar json entrada";
        header($_SERVER['SERVER_PROTOCOL']." 422  Error: faltan parametros de entrada json ");		
    }
    else
    {
        $response = array();
        $usr =$objArr['usr'];//debrian enviarse encriptda
        $cont =$objArr['pwd'];//debrian enviarse encriptda

        $alu = $objArr['alumno']; //Arreglo de JSON
        $res = obj2array($alu);  // Convierte JSON en un Array

        //Validar que el usuario tiene permisos para actualizar alumnos
        //
        $result =0;
        foreach ($res as $value){
            $result = InsActAlumno($value);
        }
              
        if ($result == 1) {
            $response["success"] = "201";
            $response["message"] = "Se Respaldaron los Alumnos";
        }
        else{
            $response["success"] = "409";
            $response["message"] = "Alumnos no se Respaldaron";
            header($_SERVER['SERVER_PROTOCOL'] . " 409  Conflicto al Insertar ");
        }
        
    }
    echo json_encode($response);
?>