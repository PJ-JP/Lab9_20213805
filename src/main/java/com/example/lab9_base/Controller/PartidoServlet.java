package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Dao.DaoPartidos;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "PartidoServlet", urlPatterns = {"/PartidoServlet", ""})
public class PartidoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        RequestDispatcher view;

        switch (action) {

            case "guardar":
                /*
                Inserte su código aquí
                */
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        DaoPartidos daoPartidos = new DaoPartidos();
        switch (action) {
            case "lista":
                ArrayList<Partido> list = daoPartidos.listaDePartidos();
                request.setAttribute("lista", list);
                RequestDispatcher view = request.getRequestDispatcher("1.jsp");
                view.forward(request, response);
                break;
            case "crear":
                /*
                Inserte su código aquí
                 */
                break;

        }

    }
}
