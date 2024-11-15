package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.SeleccionNacional;

import java.sql.*;
import java.util.ArrayList;

public class DaoArbitros extends DaoBase{

    public ArrayList<Arbitro> listarArbitros() {
        ArrayList<Arbitro> arbitros = new ArrayList<>();
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from arbitro;");) {
            while (rs.next()) {
                Arbitro arbitro = new Arbitro();
                arbitro.setIdArbitro(rs.getInt(1));
                arbitro.setNombre(rs.getString(2));
                arbitro.setPais(rs.getString(3));
                arbitros.add(arbitro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arbitros;
    }

    public void crearArbitro(Arbitro arbitro) {
        String sql = "insert into arbitro (nombre,pais) value (?,?);";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, arbitro.getNombre());
            pstmt.setString(2, arbitro.getPais());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Arbitro> busquedaPais(String pais) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        String sql = "select * from arbitro where lower(pais) like lower(?);";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1,"%" +pais+ "%");
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Arbitro arbitro = new Arbitro();
                    arbitro.setIdArbitro(rs.getInt(1));
                    arbitro.setNombre(rs.getString(2));
                    arbitro.setPais(rs.getString(3));
                    arbitros.add(arbitro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arbitros;
    }

    public ArrayList<Arbitro> busquedaNombre(String nombre) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        String sql = "select * from arbitro where lower(nombre) like lower(?);";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1,"%" +nombre+ "%");
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Arbitro arbitro = new Arbitro();
                    arbitro.setIdArbitro(rs.getInt(1));
                    arbitro.setNombre(rs.getString(2));
                    arbitro.setPais(rs.getString(3));
                    arbitros.add(arbitro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arbitros;
    }

    public Arbitro buscarArbitro(int id) {
        Arbitro arbitro = new Arbitro();
        String sql = "select * from arbitro where idArbitro = ?;";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1,id);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    arbitro.setIdArbitro(rs.getInt(1));
                    arbitro.setNombre(rs.getString(2));
                    arbitro.setPais(rs.getString(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arbitro;
    }

    public void borrarArbitro(int id) {

        /*Aquí quiero borrarlos de todas formas*/
        String sql = "delete from partido where arbitro=?;";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        sql = "delete from arbitro where idArbitro = ?;";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
