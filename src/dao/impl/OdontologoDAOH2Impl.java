package dao.impl;

import dao.OdontologoDAO;

import db.DatabaseConnection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2Impl implements OdontologoDAO {
    private static final Logger logger = Logger.getLogger(OdontologoDAOH2Impl.class);

    @Override
    public void guardarOdontologo(model.Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO odontologos(matricula, nombre, apellido) VALUES(?,?,?)");
            preparedStatement.setInt(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            preparedStatement.executeUpdate();
            logger.info("Odontólogo guardado: " + odontologo.getNombre() + " " + odontologo.getApellido());

        } catch (SQLException e) {
            logger.error("Error al guardar odontólogo", e);
        } finally {
            DatabaseConnection.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        List<Odontologo> odontologos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM odontologos");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Odontologo odontologo = new Odontologo();
                odontologo.setMatricula(resultSet.getInt("matricula"));
                odontologo.setNombre(resultSet.getString("nombre"));
                odontologo.setApellido(resultSet.getString("apellido"));
                odontologos.add(odontologo);
            }

            logger.info("Odontólogos listados: " + odontologos.size());

        } catch (SQLException e) {
            logger.error("Error al listar odontólogos", e);
        } finally {
            DatabaseConnection.closeConnection(connection, preparedStatement, resultSet);
        }

        return odontologos;
    }
}