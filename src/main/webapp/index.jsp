<%@ page import="com.example.lab9_base.Bean.Partido" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="lista" scope="request" type="java.util.ArrayList<com.example.lab9_base.Bean.Partido>"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
        <title>LAB 9</title>
    </head>
    <body>
        <div class='container'>
            <div class="row mb-5 mt-4">
                <div class="col-lg-6">
                    <h1 class=''>Lista de Partidos</h1>
                </div>
                <div class="col-lg-6 my-auto text-lg-right">
                    <a href="<%= request.getContextPath()%>/PartidoServlet?action=crear" class="btn btn-primary">Crear Partido</a>
                </div>
            </div>
            <table class="table">
                <tr>
                    <th>#</th>
                    <th>Jornada</th>
                    <th>Fecha</th>
                    <th>Selección Local</th>
                    <th>Selección Visitante</th>
                    <th>Estadio a jugar</th>
                    <th>Árbitro</th>
                </tr>
                <%for (Partido partido : lista) {%>
                <tr>
                    <td><%=partido.getIdPartido()%></td>
                    <td><%=partido.getNumeroJornada()%></td>
                    <td><%=partido.getFecha()%></td>
                    <td><%=partido.getSeleccionLocal().getNombre()%></td>
                    <td><%=partido.getSeleccionVisitante().getNombre()%></td>
                    <td><%=partido.getSeleccionLocal().getEstadio().getNombre()%></td>
                    <td><%=partido.getArbitro().getNombre()%></td>
                </tr>
                <%}%>
            </table>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>