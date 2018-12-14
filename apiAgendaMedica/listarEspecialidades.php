<?php
    include "conexao.php";
    $sql = "SELECT * FROM especialidade";
    $resultado = mysqli_query($conexao, $sql);
    $numLinhas = mysqli_num_rows($resultado);
    $controleLinhas = 0;
    $json = '';
    $retorno = '[';
    while($var = mysqli_fetch_assoc($resultado)){
        $controleLinhas = $controleLinhas + 1;
        $retorno = $retorno.'{"id":"'.$var['idEspecialidade'].'","nome":"'.$var['nomeEspecialidade'].'"}';
        if($controleLinhas < $numLinhas){
            $retorno = $retorno.',';
        }
    }
    $retorno = $retorno.']';
    echo($retorno);
?>