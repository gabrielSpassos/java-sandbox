package com.gabrielspassos;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;

import java.io.IOException;

@WebServlet("/api")
public class ApiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String f1 = req.getParameter("f1");
        String f2 = req.getParameter("f2");

        var json = new JsonObject();
        json.addProperty("field1", f1);
        json.addProperty("field2", f2);
        json.addProperty("message", "Processed successfully");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write(json.toString());
    }

}
