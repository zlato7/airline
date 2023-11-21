<%@ page import="java.util.List" %>
<%@ page import="com.airline.models.Flight" %>
<%@ page import="com.airline.models.Pilot" %>
<%@ page import="com.airline.models.Passenger" %><%--
  Created by IntelliJ IDEA.
  User: zlatina
  Date: 10/2/23
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <link rel="stylesheet" href="resources/css/jpaStyles.css">
    <title>Passengers List</title>
  </head>
  <body>

    <h1>List of Passengers</h1>

    <table>
        <tr>
            <th>Id</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Date of birth</th>
            <th>Gender</th>

        </tr>

        <%
            List<Passenger> pList = (List<Passenger>) request.getAttribute("passenger_list");

            for (int i = 0; i < pList.size(); i++)
            {

        %>

            <tr>
                <td><%= pList.get(i).getId() %></td>
                <td><%= pList.get(i).getFirstName() %></td>
                <td><%= pList.get(i).getLastName() %></td>
                <td><%= pList.get(i).getDob() %></td>
                <td><%= pList.get(i).getGender() %></td>
            </tr>

            <tr>
                <td colspan="5">
                    <%
                        if (pList.get(i).getFlights().size() > 0) {

                            List<Flight> fList = pList.get(i).getFlights();

                            for (int j = 0; j < fList.size(); j++) {

                    %>
                            <%= j+1 %>) <%= fList.get(j).getFlightOrigin() %> to <%= fList.get(j).getFlightDestination() %> @ <%= fList.get(j).getFlightTime() %> <br />
                    <%
                            } //for
                        } else {
                    %>
                    No flights tickets yet.
                    <%
                        }
                    %>
                </td>
            </tr>
        <%
            }
        %>

    </table>
  </body>
</html>
