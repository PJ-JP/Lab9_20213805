package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.*;

import java.util.ArrayList;
import com.example.lab9_base.Bean.Partido;

import java.sql.*;


public class DaoPartidos extends DaoBase{
    public ArrayList<Partido> listaDePartidos() {

        ArrayList<Partido> partidos = new ArrayList<>();

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select p.idPartido,p.numeroJornada,p.fecha,s.nombre,v.nombre,e.nombre,a.nombre from partido p, seleccion s, estadio e,arbitro a,seleccion v where s.idSeleccion=p.seleccionLocal and s.estadio_idEstadio=e.idEstadio and a.idArbitro=p.arbitro and v.idSeleccion=p.seleccionVisitante;");) {

            while (rs.next()) {
                Partido partido = new Partido();
                SeleccionNacional seleccionLocal = new SeleccionNacional();
                Estadio estadio = new Estadio();
                SeleccionNacional seleccionVisitante = new SeleccionNacional();
                Arbitro arbitro = new Arbitro();
                partido.setIdPartido(rs.getInt(1));
                partido.setNumeroJornada(rs.getInt(2));
                partido.setFecha(rs.getString(3));
                seleccionLocal.setNombre(rs.getString(4));
                seleccionVisitante.setNombre(rs.getString(5));
                partido.setSeleccionVisitante(seleccionVisitante);
                estadio.setNombre(rs.getString(6));
                seleccionLocal.setEstadio(estadio);
                partido.setSeleccionLocal(seleccionLocal);
                arbitro.setNombre(rs.getString(7));
                partido.setArbitro(arbitro);
                partidos.add(partido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partidos;
    }

    public void crearPartido(Partido partido) {

        /*
        Inserte su código aquí
        */
    }
}
