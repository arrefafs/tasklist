package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DButil;

/**
 * Servlet implementation class UpdateServiet
 */
@WebServlet("/update")
public class UpdateServiet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServiet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em =DButil.createEntityManager();


            Task m = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));


            String title = request.getParameter("title");
            m.setTitle(title);

            String content = request.getParameter("content");
            m.setContent(content);


            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            m.setUpdate_at(currentTime);


            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();


            request.getSession().removeAttribute("task_id");


            response.sendRedirect(request.getContextPath() + "/index");





        }




    }

}
