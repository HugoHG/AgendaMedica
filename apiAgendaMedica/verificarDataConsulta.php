<?php
include "conexao.php";
$sql = "SELECT * FROM agenda_medica.consulta where idMedico = ".$_GET['idMedico']." and data = '".$_GET['data']."' and horario = '".$_GET['horario']."';";
$result = mysqli_query($conexao, $sql);
if (mysqli_num_rows($result) == 0){
    echo("sucesso");
} else{
    echo("erro");
}
//SELECT * FROM agenda_medica.consulta where idMedico = 1 and data = '2018-12-15' and horario = '15:20:00';
//verificarDataConsulta.php?idMedico=1&data=2018-12-15&horario=15:20:00
//SELECT * FROM periodo WHERE horaInicio < '08:00:00' AND horaFim > '08:00:00';
?>