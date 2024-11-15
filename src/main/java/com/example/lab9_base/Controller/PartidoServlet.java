package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.SeleccionNacional;
import com.example.lab9_base.Dao.DaoArbitros;
import com.example.lab9_base.Dao.DaoPartidos;
import com.example.lab9_base.Dao.DaoSelecciones;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


@WebServlet(name = "PartidoServlet", urlPatterns = {"/PartidoServlet", ""})
public class PartidoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        DaoPartidos daoPartidos = new DaoPartidos();
        DaoArbitros daoArbitros = new DaoArbitros();
        switch (action) {
            case "guardar":
                int numeroJornada = Integer.parseInt(request.getParameter("jornada"));
                String fecha = request.getParameter("fecha");
                int idLocal= Integer.parseInt(request.getParameter("local"));
                int idVisitante= Integer.parseInt(request.getParameter("visitante"));
                int idArbitro = Integer.parseInt(request.getParameter("arbitro"));

                Partido partido = new Partido();
                partido.setNumeroJornada(numeroJornada);
                partido.setFecha(fecha);
                SeleccionNacional local = new SeleccionNacional();
                local.setIdSeleccion(idLocal);
                partido.setSeleccionLocal(local);
                SeleccionNacional visitante = new SeleccionNacional();
                visitante.setIdSeleccion(idVisitante);
                partido.setSeleccionVisitante(visitante);
                Arbitro arbitro  = new Arbitro();
                arbitro.setIdArbitro(idArbitro);
                partido.setArbitro(arbitro);

                /*En esta parte se hace la valiación: Ningún partido se puede repetir POR JORNADA (según entiendo del funcionamiento de las clasificatorias)*/
                ArrayList<Partido> historial = daoPartidos.listaDePartidos();
                boolean flag1 = true;
                for (Partido p : historial) {
                    if((p.getSeleccionLocal().getIdSeleccion() == idLocal && p.getSeleccionVisitante().getIdSeleccion() == idVisitante)||(p.getSeleccionLocal().getIdSeleccion()==idVisitante && p.getSeleccionVisitante().getIdSeleccion()==idLocal)){
                        flag1=false;
                        break;
                    }
                }
                /*Validación no pedida?: El árbitro no puede ser del mismo país que de las selecciones que juegan*/
                String paisLocal = daoPartidos.paisSeleccion(idLocal);
                String paisVisitante = daoPartidos.paisSeleccion(idVisitante);
                Arbitro arbitroMapeado = daoArbitros.buscarArbitro(idArbitro);
                String paisArbitro = arbitroMapeado.getPais();
                boolean flag2= paisLocal.equals(paisArbitro) || paisVisitante.equals(paisArbitro);

                /*Validaciones: El local y el visitante no serán la misma seleccion*/
                if (idLocal!=idVisitante && flag1 && flag2) {
                    daoPartidos.crearPartido(partido);
                    response.sendRedirect(request.getContextPath()+"/PartidoServlet");
                }
                else{
                    response.sendRedirect(request.getContextPath()+"/PartidoServlet?action=crear");
                }
                break;
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DaoPartidos daoPartidos = new DaoPartidos();
        DaoSelecciones daoSelecciones = new DaoSelecciones();
        DaoArbitros daoArbitros = new DaoArbitros();
        switch (action) {
            case "lista":
                ArrayList<Partido> list = daoPartidos.listaDePartidos();
                request.setAttribute("lista", list);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "crear":
                request.setAttribute("selecciones",daoSelecciones.listarSelecciones());
                request.setAttribute("arbitros",daoArbitros.listarArbitros());
                request.getRequestDispatcher("partidos/form.jsp").forward(request,response);
                break;
        }
    }
}
