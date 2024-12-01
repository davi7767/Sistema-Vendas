package com.jdenner.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por abrir a conexão com o banco de dados
 *
 * @author Juliano
 */
public class Conexao {

    private static final String SERVIDOR = "localhost";
    private static final String PORTA = "3306";
    private static final String BANCO_DE_DADOS = "dbsistemavenda";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    private static final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA + "/" + BANCO_DE_DADOS + "?useSSL=false&serverTimezone=UTC";

    private Connection conexao;

    public Conexao() throws SQLException {
        try {
            // Estabelece a conexão com o banco de dados
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            conexao.setAutoCommit(false);
        } catch (SQLException e) {
            throw new SQLException("Problemas ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }

    public Connection getConexao() {
        return conexao;
    }

    public void confirmar() throws SQLException {
        if (conexao != null) {
            try {
                conexao.commit();
            } catch (SQLException e) {
                throw new SQLException("Erro ao confirmar a transação: " + e.getMessage(), e);
            } finally {
                fechar();
            }
        }
    }

    public void fechar() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}
