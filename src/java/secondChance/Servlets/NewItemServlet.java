/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondChance.Servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import secondChance.Entities.ITEMS;

/**
 *
 * @author jose_
 */
@WebServlet(name = "NewItemServlet", urlPatterns = {"/NewItem"})
public class NewItemServlet extends HttpServlet {

    @PersistenceContext(unitName = "SecondChancePU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        
        if(areCorrectData(request))   // Check all data is correct.
        {
            String cat = request.getParameter("categorySelect");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String ZC = request.getParameter("ZC");
            String age = request.getParameter("age");
            String state = request.getParameter("stateSelect");
            String description = request.getParameter("description");
            
            ITEMS i = new ITEMS(Integer.valueOf(ZC), name, Float.valueOf(price), cat, state, description, Integer.valueOf(age));
            this.persist(i);
            
            rd = request.getRequestDispatcher("/ViewItems.jsp");
        }
        
        else
            rd = request.getRequestDispatcher("/NewItem.jsp");
       
        rd.forward(request, response);
    }
    
    boolean areCorrectData(HttpServletRequest request)
    {
        String cat = request.getParameter("categorySelect");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String ZC = request.getParameter("ZC");
        String age = request.getParameter("age");
        
        return !("".equals(cat) || "".equals(name) || "".equals(price) || !isNumeric(ZC) || !isNumeric(age));
    }
    
    public static boolean isNumeric(String str)
    {
        for(char c : str.toCharArray())
            if(!Character.isDigit(c)) 
                return false;
        
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}
