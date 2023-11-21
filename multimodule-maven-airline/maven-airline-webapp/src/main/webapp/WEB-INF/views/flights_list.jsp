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
    <title>Flight List</title>
  </head>
  <body>

    <h1>List of Flights</h1>

    <table>
        <tr>
            <th>Id</th>
            <th>From</th>
            <th>To</th>
            <th>Time</th>
            <th>Price</th>
            <th>Airplane</th>
            <th>Seating</th>
            <th>Number of pilots</th>
            <th>Pilot names</th>
        </tr>

        <%
            List<Flight> fList = (List<Flight>) request.getAttribute("flight_list");

            for (int i = 0; i < fList.size(); i++)
            {

        %>

            <tr>
                <td><%= fList.get(i).getId() %></td>
                <td><%= fList.get(i).getFlightOrigin() %></td>
                <td><%= fList.get(i).getFlightDestination() %></td>
                <td><%= fList.get(i).getFlightTime() %></td>
                <td><%= fList.get(i).getPrice() %></td>

                <td><%= fList.get(i).getAirplaneDetail().getPlaneMake() + " " + fList.get(i).getAirplaneDetail().getModelName() %></td>
                <td><%= fList.get(i).getAirplaneDetail().getSeatCapacity() %></td>

                <td>
                    <%
                        if (fList.get(i).getPilots() != null){
                    %>
                    <%= fList.get(i).getPilots().size() %> pilots
                    <%
                        }
                        else {

                    %>
                        No pilots yet
                    <%
                        }
                    %>
                </td>

                <td>
                    <%
                        if (fList.get(i).getPilots() != null){
                            List<Pilot> pList = fList.get(i).getPilots();

                            for (int j = 0; j < pList.size(); j++)
                            {

                    %>
                        <%=
                        (j+1) + ") " + pList.get(j).getFirstName() + " " + pList.get(j).getLastName()
                        + " (" + pList.get(j).getPilotRank() + ")" + "<br />"
                        %>
                    <%
                        }//for

                        }//if
                    %>
                </td>

            </tr>

            <tr>
                <td colspan="9">
                    <%
                        if (fList.get(i).getPassengers().size() > 0){

                            List<Passenger> passengerList = fList.get(i).getPassengers();

                            for (int j = 0; j < passengerList.size(); j++)
                            {
                    %>
                             <%= j+1 %>) <%= passengerList.get(j).getFirstName() %> <%= passengerList.get(j).getLastName()%> <br />
                    <%
                            } //for
                        } else {
                    %>
                      No passengers on this flight yet.
                    <%
                        } //else
                    %>
                </td>
            </tr>
        <%
            }
        %>

    </table>
  </body>
</html>
