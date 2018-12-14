<?php
    include "conexao.php";
    $sql = "SELECT medico.idMedico, medico.nomeMedico FROM especialidade_medico INNER JOIN medico ON
especialidade_medico.idMedico = medico.idMedico WHERE
especialidade_medico.idEspecialidade = ".$_GET['idEspecialidade'].";";
    $resultado = mysqli_query($conexao, $sql);
    $numLinhas = mysqli_num_rows($resultado);
    $controleLinhas = 0;
    $json = '';
    $json .= '[';
    while($var = mysqli_fetch_assoc($resultado)){
        $controleLinhas = $controleLinhas + 1;
        $json .= '{"id":"'.$var['idMedico'].'","nome":"'.$var['nomeMedico'].'"}';
        if($controleLinhas < $numLinhas){
            $json .= ',';
        }
    }
    $json .= ']';
    echo($json);
?>