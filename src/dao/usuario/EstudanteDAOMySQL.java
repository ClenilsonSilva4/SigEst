package dao.usuario;

import exception.ChangeNotMade;
import exception.DBUnavailable;
import exception.UserNotFoundException;
import entities.Estudante;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EstudanteDAOMySQL extends UsuarioDAOMySQL {
    @Override
    public void inserirUsuario(String nome, String email, String senha) throws ChangeNotMade {
        try {
            conectar();

            String sqlComando = "INSERT INTO usuario (emailUsuario, nomeUsuario, senhaUsuario, tipoUsuario) VALUES (" +
                    stringBD(nome) + ", " + stringBD(email) + ", " + stringBD(senha) + ", 1);";

            int resultado = comandos.executeUpdate(sqlComando);

            if(resultado != 1) {
                throw new ChangeNotMade("Não foi possível concluir a inserção no banco de dados");
            }

            encerrarConexao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
