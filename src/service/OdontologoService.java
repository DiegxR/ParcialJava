package com.clinica.service;

import com.clinica.dao.OdontologoDAO;
import com.clinica.model.Odontologo;
import java.util.List;

public class OdontologoService {
    private OdontologoDAO odontologoDAO;

    public OdontologoService(OdontologoDAO odontologoDAO) {
        this.odontologoDAO = odontologoDAO;
    }

    public void guardarOdontologo(Odontologo odontologo) {
        odontologoDAO.guardarOdontologo(odontologo);
    }

    public List<Odontologo> listarOdontologos() {
        return odontologoDAO.listarOdontologos();
    }
}