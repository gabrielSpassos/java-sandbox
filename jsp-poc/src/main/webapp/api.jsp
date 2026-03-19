<%@ page contentType="application/json;charset=UTF-8" %>
<%@ page import="org.json.JSONObject" %>

<%
    String f1 = request.getParameter("f1");
    String f2 = request.getParameter("f2");

    JSONObject json = new JSONObject();
    json.put("field1", f1);
    json.put("field2", f2);
    json.put("message", "Processed successfully");

    out.print(json.toString());
%>
