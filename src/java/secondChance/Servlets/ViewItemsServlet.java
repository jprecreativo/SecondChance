package secondChance.Servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

@WebServlet(name = "ViewItemsServlet", urlPatterns = {"/ViewItems/*"})
public class ViewItemsServlet extends HttpServlet 
{
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
     * @throws java.io.IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        Enumeration<String> param = request.getParameterNames();
        
        if(param.hasMoreElements())   // La petición contiene parámetros.
        {
            String cat = request.getParameter(param.nextElement());
            String price = request.getParameter(param.nextElement());
            String zipCode = request.getParameter(param.nextElement());
            
            
        }
        
        else
        {
            TypedQuery<ITEMS> query = em.createNamedQuery("ITEMS.findAll", ITEMS.class);
            List<ITEMS> li = query.getResultList();

            request.setAttribute("items", li);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/ViewItems.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws java.io.IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() 
    {
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
