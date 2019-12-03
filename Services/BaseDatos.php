<?php
try{
        //$Cn = new PDO('pgsql:host=localhost;port=5432;dbname=ProyectoX;user=postgres;password=hola');
        $Cn = new PDO('mysql:host=mysql.webcindario.com; dbname=servicesroqride','servicesroqride','cisco38210');
        $Cn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $Cn->exec("SET CHARACTER SET utf8");
        //$Cn->exec("SET CLIENT_ENCODING TO 'UTF8';");
}catch(Exception $e){
    die("Error: " . $e->GetMessage());
}

// Función para ejecutar consultas SELECT
function Consulta($query)
{
    global $Cn;
    try{    
        $result =$Cn->query($query);
        $resultado = $result->fetchAll(PDO::FETCH_ASSOC); 
        $result->closeCursor();
        return $resultado;
    }catch(Exception $e){
        die("Error en la LIN: " . $e->getLine() . ", MSG: " . $e->GetMessage());
    }
}

// Función que recibe un insert y regresa el consecutivo que le genero
function EjecutaConsecutivo($sentencia){
    global $Cn;
    try {
        $result = $Cn->query($sentencia);
        $resultado = $result->fetchAll(PDO::FETCH_ASSOC);
        $result->closeCursor();
        return $resultado[0]['idcurso'];
    } catch (Exception $e) {
        die("Error en la linea: " + $e->getLine() + 
        " MSG: " + $e->GetMessage());
        return 0;
    }
}

function Ejecuta ($sentencia){
    global $Cn;
    try {
        $result = $Cn->query($sentencia);
        $result->closeCursor();
        return 1; // Exito  
    } catch (Exception $e) {
        //die("Error en la linea: " + $e->getLine() + " MSG: " + $e->GetMessage());
        return 0; // Fallo
    }
}

function consultaAlumnos(){
    $query = 'SELECT nocontrol,nomest, correo, nip, carrera, semestre FROM usuarios ORDER BY nomest';   
    return Consulta($query);
}

function consultaAlumno($nocon){
    $query = 'SELECT nocontrol,nip FROM usuarios WHERE ' . " nocontrol = '$nocon'";
    return Consulta($query);
}

function insertaAlumno($post){
    $no = $post['nocontrol'];
    $nom= $post['nomest'];
    $corr= $post['correo'];
    $nip = $post['nip'];
    $carr = $post['carrera'];
    $sem = $post['semestre'];

    $sentencia = 'INSERT INTO usuarios(nocontrol,nomest, correo, nip, carrera, semestre)'. 
                 "values('$no','$nom','$corr','$nip','$carr','$sem')";
     
    return Ejecuta($sentencia);
}

function actualizarAlumno($post){
    $no = $post['nocontrol'];
    $carr = $post['carrera'];
    $nom = $post['nombre'];
    $tel= $post['telefono'];
    $sentencia = 'UPDATE alumno SET ' . 
                 "carrera='$carr',nombre='$nom',
                 telefono='$tel' WHERE nocontrol='$no'"; 
    
    return Ejecuta($sentencia);
}

function borrarAlumno($post){
    $no = $post['nocontrol'];

    $sentencia = 'DELETE FROM alumno ' . 
                 " WHERE nocontrol='$no'"; 
    
    return Ejecuta($sentencia);
}

function InsActAlumno($post){
    if (insertaAlumno($post)!=1)
        return actualizarAlumno($post);
    else
    return 1;
}

function obj2array($obj) {
    $out = array();
    foreach ($obj as $key => $val) {
      switch(true) {
          case is_object($val):
           $out[$key] = obj2array($val);
           break;
        case is_array($val):
           $out[$key] = obj2array($val);
           break;
        default:
          $out[$key] = $val;
      }
    }
    return $out;
  } 

  /**********************VIAJES*****************************/
  

  function consultaViajes(){
    $query = 'SELECT iddestino,destino, horas, nota, nocontrol, nomest FROM viajes ORDER BY destino';   
    return Consulta($query);
}
function insertaViaje($post){
    $des = $post['destino'];
    $horasal= $post['horas'];
    $not= $post['nota'];
    $control = $post['nocontrol'];
    $nom=$post['nomest'];

    $sentencia = 'INSERT INTO viajes(destino,horas, nota, nocontrol, nomest)'. 
                 "values('$des','$horasal','$not','$control','$nom')";
     
    return Ejecuta($sentencia);
}

function eliminarViaje($post){
    $iddes = $post['iddestino'];

    $sentencia = 'DELETE FROM viajes ' . 
                 " WHERE iddestino='$iddes'"; 
    
    return Ejecuta($sentencia);
}
//---------------------PETICIONES----------------------------------
function insertaPeticion($post){
    $no = $post['nocontrol'];
    $nom= $post['nombre'];
    $carr = $post['carrera'];
    $sem = $post['semestre'];
    $conchof = $post['controlchofer'];

    $sentencia = 'INSERT INTO peticion(nocontrol,nombre, carrera, semestre,controlchofer )'. 
                 "values('$no','$nom','$carr','$sem','$conchof')";
     
    return Ejecuta($sentencia);
}

function consultaPeticiones(){
    $query = 'SELECT nocontrol,nombre, carrera, semestre, controlchofer FROM peticion ORDER BY nombre';   
    return Consulta($query);
}

function eliminarPeticion($post){
    $no = $post['nocontrol'];

    $sentencia = 'DELETE FROM peticion ' . 
                 " WHERE nocontrol='$no'"; 
    
    return Ejecuta($sentencia);
}