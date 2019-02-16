package tainersoft.raiz.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tainersoft.raiz.data.Conexao;

@Service //Anotation responsável pela injeção de dependência
public class ContaRepository implements IContaRepository
{
    public Conta Add(Conta conta) throws Exception {

        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = Conexao.abrir();
        
        /* buscar qual deve ser o próximo Id */
        String sqlString  = "SELECT (max(id_conta)+1) as next_id FROM appgastos.conta;";
        PreparedStatement comandoNextId = conn.prepareStatement(sqlString);
        ResultSet nextIdResult = comandoNextId.executeQuery();
        int nextId = 1;
        while (nextIdResult.next()) {
            nextId = nextIdResult.getInt("next_id");
        }
        comandoNextId.close();
        nextIdResult.close();

        /* Define a SQL */
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO conta (id_conta,descricao,saldo) VALUES (?,?,?);");

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());
        comando.setInt(1, nextId);
        comando.setString(2, conta.getDescricao());
        comando.setFloat(3, conta.getSaldo());

        /* Executa a SQL e captura o resultado da consulta */
        int linhasAfetadas  = comando.executeUpdate();

        comando.close();
        conn.close();

        return getById(nextId);
    }

    public Conta update(int id, Conta conta) throws Exception {
        
        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = Conexao.abrir();
        
        /* Define a SQL */
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE conta SET ");
        sql.append("descricao = ?, ");
        sql.append("saldo = ? ");
        sql.append("WHERE (id_conta = ?);");

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());
        comando.setInt(3, id);
        comando.setString(1, conta.getDescricao());
        comando.setFloat(2, conta.getSaldo());
        

        /* Executa a SQL e captura o resultado da consulta */
        int linhasAfetadas  = comando.executeUpdate();

        comando.close();
        conn.close();

        return getById(conta.getId());

    }

    public Conta remove(Conta conta) throws Exception
    {
        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = Conexao.abrir();
        
        /* Define a SQL */
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM conta ");
        sql.append("WHERE (id_conta = ?);");

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());
        comando.setInt(1, conta.getId());

        /* Executa a SQL e captura o resultado da consulta */
        int linhasAfetadas  = comando.executeUpdate();

        comando.close();
        conn.close();

        return conta;
    }

    public List<Conta> getAll() throws Exception {
        /* Define a SQL */
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id_conta, descricao, saldo ");
        sql.append("FROM conta");

        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = Conexao.abrir();

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());

        /* Executa a SQL e captura o resultado da consulta */
        ResultSet resultado = comando.executeQuery();

        /* Converte a ResultSet em uma lista de Lancamaneos */
        List<Conta> lista = converterResultSetParaConta(resultado);

        /* Fecha a conexão */
        resultado.close();
        comando.close();
        conn.close();

        /* Retorna a lista contendo o resultado da consulta */
        return lista;

    }

	public Conta getById(int id) throws Exception {

        /* Define a SQL */
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id_conta, descricao, saldo ");
        sql.append("FROM conta ");
        sql.append("WHERE (id_conta = ?);");

        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = Conexao.abrir();

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());
        comando.setInt(1, id);

        /* Executa a SQL e captura o resultado da consulta */
        ResultSet resultado = comando.executeQuery();

        /* Converte a ResultSet em uma lista de Lancamaneos */
        List<Conta> lista = converterResultSetParaConta(resultado);

        /* Fecha a conexão */
        resultado.close();
        comando.close();
        conn.close();

        /* Retorna a lista contendo o resultado da consulta */
        return lista.get(0);

    }

    private List<Conta> converterResultSetParaConta(ResultSet resultado) throws SQLException {
        /* Cria uma lista para armazenar o resultado da consulta */
        List<Conta> lista = new ArrayList<Conta>();

        /* Percorre o resultado armazenando os valores em uma lista */
        while (resultado.next()) {
            /* Cria um objeto para armazenar uma linha da consulta */
            Conta conta = new Conta();
            conta.setId(resultado.getInt("id_conta"));
            conta.setDescricao(resultado.getString("descricao"));
            conta.setSaldo(resultado.getFloat("saldo"));
            /* Armazena a linha lida em uma lista */
            lista.add(conta);
        }

        return lista;
    }
}