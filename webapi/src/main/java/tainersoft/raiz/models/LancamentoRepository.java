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
public class LancamentoRepository implements ILancamentoRepository
{
    public Lancamento Add(Lancamento lancamento) throws Exception {

        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = Conexao.abrir();
        
        /* buscar qual deve ser o próximo Id */
        String sqlString  = "SELECT (max(id_lancamento)+1) as next_id FROM appgastos.lancamento;";
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
        sql.append("INSERT INTO lancamento (id_lancamento,id_conta, descricao, valor) VALUES (?,?,?,?);");

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());
        comando.setInt(1, nextId);
        comando.setInt(2, lancamento.getId_conta());
        comando.setString(3, lancamento.getDescricao());
        comando.setFloat(4, lancamento.getValor());

        /* Executa a SQL e captura o resultado da consulta */
        int linhasAfetadas  = comando.executeUpdate();

        comando.close();
        conn.close();

        return getById(nextId);
    }

    public Lancamento update(Lancamento lancamento) throws Exception {
        
        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = Conexao.abrir();
        
        /* Define a SQL */
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE lancamento SET ");
        sql.append("descricao = ?, ");
        sql.append("valor = ? ");
        sql.append("WHERE (id_lancamento = ?);");

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());
        comando.setString(1, lancamento.getDescricao());
        comando.setFloat(2, lancamento.getValor());
        comando.setInt(3, lancamento.getId());

        /* Executa a SQL e captura o resultado da consulta */
        int linhasAfetadas  = comando.executeUpdate();

        comando.close();
        conn.close();

        return getById(lancamento.getId());

    }

    public Lancamento remove(Lancamento lancamento) throws Exception
    {
        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = Conexao.abrir();
        
        /* Define a SQL */
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM lancamento ");
        sql.append("WHERE (id_lancamento = ?);");

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());
        comando.setInt(1, lancamento.getId());

        /* Executa a SQL e captura o resultado da consulta */
        int linhasAfetadas  = comando.executeUpdate();

        comando.close();
        conn.close();

        return lancamento;
    }

    public List<Lancamento> getAll() throws Exception {
        /* Define a SQL */
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id_lancamento, id_conta, descricao, valor ");
        sql.append("FROM lancamento ");

        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = Conexao.abrir();

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());

        /* Executa a SQL e captura o resultado da consulta */
        ResultSet resultado = comando.executeQuery();

        /* Converte a ResultSet em uma lista de Lancamaneos */
        List<Lancamento> lista = converterResultSetParaLancamentos(resultado);

        /* Fecha a conexão */
        resultado.close();
        comando.close();
        conn.close();

        /* Retorna a lista contendo o resultado da consulta */
        return lista;

    }

	public Lancamento getById(int id) throws Exception {

        /* Define a SQL */
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id_lancamento, id_conta, descricao, valor ");
        sql.append("FROM lancamento ");
        sql.append("WHERE (id_lancamento = ?);");

        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = Conexao.abrir();

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());
        comando.setInt(1, id);

        /* Executa a SQL e captura o resultado da consulta */
        ResultSet resultado = comando.executeQuery();

        /* Converte a ResultSet em uma lista de Lancamaneos */
        List<Lancamento> lista = converterResultSetParaLancamentos(resultado);

        /* Fecha a conexão */
        resultado.close();
        comando.close();
        conn.close();

        /* Retorna a lista contendo o resultado da consulta */
        return lista.get(0);

    }

    private List<Lancamento> converterResultSetParaLancamentos(ResultSet resultado) throws SQLException {
        /* Cria uma lista para armazenar o resultado da consulta */
        List<Lancamento> lista = new ArrayList<Lancamento>();

        /* Percorre o resultado armazenando os valores em uma lista */
        while (resultado.next()) {
            /* Cria um objeto para armazenar uma linha da consulta */
            Lancamento lancamento = new Lancamento();
            lancamento.setId(resultado.getInt("id_lancamento"));
            lancamento.setDescricao(resultado.getString("descricao"));
            lancamento.setValor(resultado.getFloat("valor"));
            lancamento.setId_conta(resultado.getInt("id_conta"));
            /* Armazena a linha lida em uma lista */
            lista.add(lancamento);
        }

        return lista;
    }
}