package com.clinica.dao;

import com.clinica.dao.impl.OdontologoDAOH2Impl;
import com.clinica.model.Odontologo;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class OdontologoDAOH2ImplTest {
    private OdontologoDAO odontologoDAO;

    @Before
    public void setUp() {
        odontologoDAO = new OdontologoDAOH2Impl();
    }

    @Test
    public void testListarOdontologos() {
        // Guardar algunos odontólogos de prueba
        Odontologo odontologo1 = new Odontologo(1, "Juan", "Pérez");
        Odontologo odontologo2 = new Odontologo(2, "María", "López");
        odontologoDAO.guardarOdontologo(odontologo1);
        odontologoDAO.guardarOdontologo(odontologo2);

        // Listar odontólogos
        List<Odontologo> odontologos = odontologoDAO.listarOdontologos();

        // Verificaciones
        assertFalse(odontologos.isEmpty());
        assertTrue(odontologos.size() >= 2);

        boolean encontradoOdontologo1 = odontologos.stream()
                .anyMatch(o -> o.getMatricula() == odontologo1.getMatricula());
        boolean encontradoOdontologo2 = odontologos.stream()
                .anyMatch(o -> o.getMatricula() == odontologo2.getMatricula());

        assertTrue(encontradoOdontologo1);
        assertTrue(encontradoOdontologo2);
    }
}