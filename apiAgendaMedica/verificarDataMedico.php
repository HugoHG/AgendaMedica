<?php
include "conexao.php";
$sql = "SELECT * FROM periodo WHERE horaInicio < '".$_GET['hora']."' AND horaFim > '".$_GET['hora']."' AND idMedico = ".$_GET['idMedico']." AND diaSemana = ".$_GET['diaSemana'].";";
$result = mysqli_query($conexao, $sql);
if (mysqli_num_rows($result) == 0){
    echo("erro");
} else{
    echo("sucesso");
}
//verificarDataMedico.php?hora=08:08:00&idMedico=1&diaSemana=0
?>