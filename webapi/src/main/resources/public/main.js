$(document).ready(function () {
    $('#SelLancamentoRemove').click(function () {
        $('select[name=SelLancamento]').empty()
        var urlLancamenos = "./lancamentos"; //pode ser o endereço relativo

        $.ajax(
            {
                type: 'GET', // metodo usado
                url: urlLancamenos, // url 
                dataType: 'json', // tipo do retorno

                success: function (data)  // função executada ao receber o conteúdo da API, sendo que 'data' é o conteúdo de retorno
                {
                   
                    data.forEach(item => {
                        var linha ="'<option value="+item.id+">"+ item.descricao+" "+item.valor+ "</option>";
                        $('select[name=SelLancamento]').append(linha);
                    });
                }
            });
    })  
  
    $('#SelContaRemove').click(function () {
        $('select[name=SelCont]').empty()
        var urlLancamenos = "./contas"; //pode ser o endereço relativo
        $.ajax(
            {
                type: 'GET', // metodo usado
                url: urlLancamenos, // url 
                dataType: 'json', // tipo do retorno

                success: function (data)  // função executada ao receber o conteúdo da API, sendo que 'data' é o conteúdo de retorno
                {
                   
                    data.forEach(item => {
                        var linha ="'<option value="+item.id+">"+ item.descricao+ "</option>";
                        $('select[name=SelCont]').append(linha);
                    });
                }
            });
    })   
   
    $('#RemoveConta').click(function () {
        var urlLancamenos = "./contas/"+$('select[name=SelCont]').val(); //pode ser o endereço relativo

        $.ajax(
            {
                type: 'DELETE', // metodo usado
                url: urlLancamenos, // url 
                dataType: 'json', // tipo do retorno

                success: function (data)  // função executada ao receber o conteúdo da API, sendo que 'data' é o conteúdo de retorno
                {
                    alert("Conta removida");               
                }
            });
    })

    $('#RemoveLancamento').click(function () {
        var urlLancamenos = "./lancamentos/"+$('select[name=SelLancamento]').val();
        $.ajax(
            {
                type: 'DELETE', // metodo usado
                url: urlLancamenos, // url 
                dataType: 'json', // tipo do retorno

                success: function (data)  // função executada ao receber o conteúdo da API, sendo que 'data' é o conteúdo de retorno
                {
                    alert("Lancamento removido");               
                }
            });
    })
    
    $('#SelContaID').click(function () {
        $('select[name=SelConta]').empty()
        var urlLancamenos = "./contas"; //pode ser o endereço relativo

        $.ajax(
            {
                type: 'GET', // metodo usado
                url: urlLancamenos, // url 
                dataType: 'json', // tipo do retorno

                success: function (data)  // função executada ao receber o conteúdo da API, sendo que 'data' é o conteúdo de retorno
                {
                   
                    data.forEach(item => {
                        var linha ="'<option value="+item.id+">"+ item.descricao+ "</option>";
                        $('select[name=SelConta]').append(linha);
                    });
                }
            });
    })
    $('#SelContaAttID').click(function () {
        $('select[name=SelContAtt]').empty()
        var urlLancamenos = "./contas"; //pode ser o endereço relativo
        $.ajax(
            {
                type: 'GET', // metodo usado
                url: urlLancamenos, // url 
                dataType: 'json', // tipo do retorno

                success: function (data)  // função executada ao receber o conteúdo da API, sendo que 'data' é o conteúdo de retorno
                {
                   
                    data.forEach(item => {
                        var linha ="'<option value="+item.id+">"+ item.descricao+ "</option>";
                        $('select[name=SelContAtt]').append(linha);
                    });
                }
            });
    })
    $('#btnAtualizarConta').click(function () {
        var urlLancamenos = "./contas/"+$('select[name=SelContAtt]').val();
        //var teste = $('input[name=descricaoAtualizadaConta]').val() +"<-descricao valor nobo->"+ $('input[name=valorAtualizadoConta]').val();
        //alert(teste);
        var conta = {
            'descricao': $('input[name=descricaoAtualizadaConta]').val(),
            'saldo': $('input[name=valorAtualizadoConta]').val()
        };
        var jsonString = JSON.stringify(conta);
        $.ajax(
            {
                type: 'PUT',
                url: urlLancamenos,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8', //tipo de dados de envio
                data: jsonString, // conteúdo a ser enviado à api
                success: function (data)  // executado ao receber o retorno da api. Sendo que 'data' é o que veio da api, nesse caso um objeto 'Lancamento'
                {
                    alert("Lancamento adicionado com o código " + data.id);
                }
            });
    });

    $('#btnSaldoContas').click(function () {
        $('p[name=teste]').empty();
        var urlLancamenos = "./contas";
        var soma= 0;
        //alert("e");
        $.ajax(
            {
                type: 'GET', // metodo usado
                url: urlLancamenos, // url 
                dataType: 'json', // tipo do retorno

                success: function (data)  // função executada ao receber o conteúdo da API, sendo que 'data' é o conteúdo de retorno
                {
                   
                    data.forEach(item => {
                        soma = soma + item.saldo;
                    });
                    $('p[name=teste]').append("R$ "+soma);
                }
            });

    });


    $('#btnAtualizarLancamento').click(function () {
        var urlLancamenos = "./lancamentos/"+$('select[name=SelLancamentoAttLan]').val();
        var teste = $('input[name=descricaoAtualizada]').val() +"<-descricao valor nobo->"+ $('input[name=valorAtualizado]').val();
        alert(teste);
        var lancamento = {
            'descricao': $('input[name=descricaoAtualizada]').val(),
            'valor': $('input[name=valorAtualizado]').val(),
            'id_conta': $('select[name=SelContaAttLan]').val()
        };
        var jsonString = JSON.stringify(lancamento);
        $.ajax(
            {
                type: 'PUT',
                url: urlLancamenos,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8', //tipo de dados de envio
                data: jsonString, // conteúdo a ser enviado à api
                success: function (data)  // executado ao receber o retorno da api. Sendo que 'data' é o que veio da api, nesse caso um objeto 'Lancamento'
                {
                    alert("Lancamento adicionado com o código " + data.id);
                }
            });
    });

    $('#SelContaAttLanID').click(function () {
        $('select[name=SelContaAttLan]').empty()
        var urlLancamenos = "./contas"; //pode ser o endereço relativo
        $.ajax(
            {
                type: 'GET', // metodo usado
                url: urlLancamenos, // url 
                dataType: 'json', // tipo do retorno

                success: function (data)  // função executada ao receber o conteúdo da API, sendo que 'data' é o conteúdo de retorno
                {
                   
                    data.forEach(item => {
                        var linha ="'<option value="+item.id+">"+ item.descricao+ "</option>";
                        $('select[name=SelContaAttLan]').append(linha);
                    });
                }
            });
    })

    $('#SelLancamentoAttLan').click(function () {
        $('select[name=SelLancamentoAttLan]').empty()
        var urlLancamenos = "./lancamentos"; //pode ser o endereço relativo

        $.ajax(
            {
                type: 'GET', // metodo usado
                url: urlLancamenos, // url 
                dataType: 'json', // tipo do retorno

                success: function (data)  // função executada ao receber o conteúdo da API, sendo que 'data' é o conteúdo de retorno
                {
                   
                    data.forEach(item => {
                        var linha ="'<option value="+item.id+">"+ item.descricao+" "+item.valor+ "</option>";
                        $('select[name=SelLancamentoAttLan]').append(linha);
                    });
                }
            });
    })  

    function buscarConta(id, response, err){
        var urlLancamenos = "./contas/"+id; //pode ser o endereço relativo
        var retorno;
        $.ajax(
            {
                type: 'GET', // metodo usado
                url: urlLancamenos, // url 
                dataType: 'json', // tipo do retorno
                success: function (data)  // função executada ao receber o conteúdo da API, sendo que 'data' é o conteúdo de retorno
                {
                   /* data.forEach(item => {*/
                        //retorno = item.descricao;
                        response(data.descricao);
                   /* });*/
                    
                },
                error: function (request, status, error) {
                    err(request.responseText);
                }
            });
            //return retorno;
    }
    $('#btnBuscar').click(function () {
        var urlLancamenos = "./lancamentos"; //pode ser o endereço relativo
        var descricao;
        $.ajax(
            {
                type: 'GET', // metodo usado
                url: urlLancamenos, // url 
                dataType: 'json', // tipo do retorno
                success: function (data)  // função executada ao receber o conteúdo da API, sendo que 'data' é o conteúdo de retorno
                {
                    //limpa tabela
                    $("#tableContent").empty();
                    //cria linhas na tabela
                    data.forEach(item => {
                        descricao =  buscarConta(item.id_conta, function (response) {
                            var linha = "<tr><td>" + item.descricao + "</td><td>" + "R$ " + item.valor + "</td><td>" + response + "</td></tr>";
                            $("#tableContent").append(linha);
                            console.log(response);
                            }, function (error) {
                           console.log(error);
                        });
                        
                    });
                }
            });
    })
    $('#btnBuscarContas').click(function () {
        var urlLancamenos = "./contas"; //pode ser o endereço relativo

        $.ajax(
            {
                type: 'GET', // metodo usado
                url: urlLancamenos, // url 
                dataType: 'json', // tipo do retorno

                success: function (data)  // função executada ao receber o conteúdo da API, sendo que 'data' é o conteúdo de retorno
                {
                    //limpa tabela
                    $("#tableContentConta").empty();

                    //cria linhas na tabela
                    data.forEach(item => {
                        var linha = "<tr><td>" + item.descricao + "</td><td>" + item.saldo+" R$" + "</td></tr>";
                        $("#tableContentConta").append(linha);
                    });
                }
            });
    })

    $('#btnAdicionar').click(function() {    
        var lancamento = {
            'descricao': $('input[name=descricao]').val(),
            'valor': $('input[name=valor]').val(),
            'id_conta': $('select[name=SelConta]').val()
        };
        
        //converte esse objeto em json
        var jsonString = JSON.stringify(lancamento);

        var urlLancamenos = "./lancamentos"; //pode ser o endereço relativo

        $.ajax(
            {
                type: 'POST',
                url: urlLancamenos,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8', //tipo de dados de envio
                data: jsonString, // conteúdo a ser enviado à api
                success: function (data)  // executado ao receber o retorno da api. Sendo que 'data' é o que veio da api, nesse caso um objeto 'Lancamento'
                {
                    //alert("Lancamento adicionado com o código " + data.id);
                }
            });
    });
    $('#btnAdicionarConta').click(function() {
        //criar um objeto (em javastript) com as informações que serão enviadas
        var conta = {
            'descricao': $('input[name=descricaoConta]').val(),
            'saldo': $('input[name=saldo]').val(),
        };
        
        //converte esse objeto em json
        var jsonString = JSON.stringify(conta);

        var urlLancamenos = "./contas"; //pode ser o endereço relativo

        $.ajax(
            {
                type: 'POST',
                url: urlLancamenos,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8', //tipo de dados de envio
                data: jsonString, // conteúdo a ser enviado à api
                success: function (data)  // executado ao receber o retorno da api. Sendo que 'data' é o que veio da api, nesse caso um objeto 'Lancamento'
                {
                    alert("Conta adicionada com o código " + data.id);
                }
            });
    });  
    $('#SelContaDeEnvio').click(function () {
        $('select[name=SelContaDeEnvio]').empty()
        var urlLancamenos = "./contas"; //pode ser o endereço relativo
        $.ajax(
            {
                type: 'GET', // metodo usado
                url: urlLancamenos, // url 
                dataType: 'json', // tipo do retorno

                success: function (data)  // função executada ao receber o conteúdo da API, sendo que 'data' é o conteúdo de retorno
                {
                   
                    data.forEach(item => {
                        var linha ="'<option value="+item.id+">"+ item.descricao+ "</option>";
                        $('select[name=SelContaDeEnvio]').append(linha);
                    });
                }
            });
    })  
    $('#SelContaDeRecebimento').click(function () {
        $('select[name=SelContaDeRecebimento]').empty()
        var urlLancamenos = "./contas"; //pode ser o endereço relativo
        $.ajax(
            {
                type: 'GET', // metodo usado
                url: urlLancamenos, // url 
                dataType: 'json', // tipo do retorno

                success: function (data)  // função executada ao receber o conteúdo da API, sendo que 'data' é o conteúdo de retorno
                {
                   
                    data.forEach(item => {
                        var linha ="'<option value="+item.id+">"+ item.descricao+ "</option>";
                        $('select[name=SelContaDeRecebimento]').append(linha);
                    });
                }
            });
    })
    $('#btnTransferencia').click(function() {    
        var lancamento = {
            'deConta':{   
                'descricao': $('input[name=descricaoTransferencia]').val(),
                'valor': $('input[name=valorTransferencia]').val(),
                'id_conta': $('select[name=SelContaDeEnvio]').val()
            },
            'paraConta':{
                'descricao': $('input[name=descricaoTransferencia]').val(),
                'valor': $('input[name=valorTransferencia]').val(),
                'id_conta': $('select[name=SelContaDeRecebimento]').val()
            }
        };

        //converte esse objeto em json
        var jsonString = JSON.stringify(lancamento);

        var urlLancamenos = "./lancamentos/trans"; //pode ser o endereço relativo

        $.ajax(
            {
                type: 'POST',
                url: urlLancamenos,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8', //tipo de dados de envio
                data: jsonString, // conteúdo a ser enviado à api
                success: function (data)  // executado ao receber o retorno da api. Sendo que 'data' é o que veio da api, nesse caso um objeto 'Lancamento'
                {
                    alert("Transferencia efetuada");
                }
            });
    });  

});