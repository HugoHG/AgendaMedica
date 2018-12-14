<?php
include "conexao.php";
$sql = "INSERT INTO consulta (nomePaciente, rgPaciente, idMedico, data, horario) 
VALUES ('".$_GET['nome']."', '".$_GET['rg']."', ".$_GET['idMedico'].", '".$_GET['data']."', '".$_GET['horario']."');";
if (mysqli_query($conexao, $sql)){
    echo("sucesso");
} else{
    echo("erro");
}

//cadastrarConsulta.php?nome=Evandro&rg=020202&idMedico=2&data=2018-12-15&horario=15:20:00
?>