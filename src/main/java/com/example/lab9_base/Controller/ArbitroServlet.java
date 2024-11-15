package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Dao.DaoArbitros;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ArbitroServlet", urlPatterns = {"/ArbitroServlet"})
public class ArbitroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");
        DaoArbitros daoArbitros = new DaoArbitros();
        switch (action) {
            case "buscar":
                String tipo = request.getParameter("tipo");
                String buscar = request.getParameter("buscar");
                ArrayList<Arbitro> list = new ArrayList<>();
                if(tipo.equals("nombre")){
                    list =daoArbitros.busquedaNombre(buscar);
                } else if (tipo.equals("pais")) {
                    list =daoArbitros.busquedaPais(buscar);
                }
                request.setAttribute("lista",list);
                request.setAttribute("busqueda",buscar);
                request.setAttribute("opciones",opciones);
                request.getRequestDispatcher("/arbitros/list.jsp").forward(request, response);
                break;

            case "guardar":
                Arbitro arbitro = new Arbitro();
                arbitro.setNombre(request.getParameter("nombre"));
                arbitro.setPais(request.getParameter("pais"));
                daoArbitros.crearArbitro(arbitro);
                response.sendRedirect(request.getContextPath()+"/ArbitroServlet");
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        ArrayList<String> paises = new ArrayList<>();
        paises.add("Peru");
        paises.add("Chile");
        paises.add("Argentina");
        paises.add("Paraguay");
        paises.add("Uruguay");
        paises.add("Colombia");
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");
        DaoArbitros daoArbitros = new DaoArbitros();
        switch (action) {
            case "lista":
                ArrayList<Arbitro> list = daoArbitros.listarArbitros();
                request.setAttribute("lista", list);
                request.setAttribute("opciones", opciones);
                request.getRequestDispatcher("/arbitros/list.jsp").forward(request, response);
                break;
            case "crear":
                request.setAttribute("paises", paises);
                request.getRequestDispatcher("/arbitros/form.jsp").forward(request, response);
                break;
            case "borrar":
                String idd = request.getParameter("id");
                int id=Integer.parseInt(idd);
                Arbitro arbitro = daoArbitros.buscarArbitro(id);
                if(arbitro != null){
                    daoArbitros.borrarArbitro(id);
                }
                response.sendRedirect(request.getContextPath() + "/ArbitroServlet");
                break;
        }
    }
}
