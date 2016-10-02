/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.EmailEntry;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.EmailEntryFacade;

/**
 *
 * @author md
 */
@WebServlet(name = "EmailServletDB", urlPatterns = {"/EmailServletDB"})
public class EmailServletDB extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    
    @EJB
    private EmailEntryFacade EmailServletDB;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "Search": {
                String lastName = request.getParameter("lastname");

                List<EmailEntry> guestList = EmailServletDB.findByLastName(lastName);
                request.setAttribute("guestList", guestList);
                break;

            }

            case "Show All": {
                List<EmailEntry> guestList = EmailServletDB.showAllRecords();
                request.setAttribute("guestList", guestList);
                break;
            }
            case "Edit": {
                int id = Integer.parseInt(request.getParameter("guestID"));
                List<EmailEntry> guestList = EmailServletDB.findById(id);
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String Email = request.getParameter("emailAddress");

                guestList.get(0).setId(id);
                guestList.get(0).setFirstName(firstName);
                guestList.get(0).setLastName(lastName);
                guestList.get(0).setEmailAddress(Email);

                try {
                    EmailServletDB.edit(guestList.get(0));
                    String message = "Edit successful";
                    request.setAttribute("message1", message);
                } catch (Exception e) {
                    String message = "Edit failure";
                    request.setAttribute("message1", message);
                }
                break;
            }
            case "Delete": {
                int id = Integer.parseInt(request.getParameter("guestID"));
                List<EmailEntry> guestList = EmailServletDB.findById(id);
                try {
                    EmailServletDB.deleteById(guestList.get(0));
                    String message = "Delete successful";
                    request.setAttribute("message2", message);
                } catch (Exception e) {
                    String message = "Delete failure";
                    request.setAttribute("message2", message);
                }
                break;
            }
            case "Total Records": {
                int count = EmailServletDB.getNumberOfRecords();
                String message = Integer.toString(count);
                request.setAttribute("message3", message);
                break;
            }
            default: {
                int id = Integer.parseInt(request.getParameter("guestID"));
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String Email = request.getParameter("emailAddress");
                EmailServletDB.persistGuestBookData(id, firstName, lastName, Email);
                List<EmailEntry> guestList = EmailServletDB.findAll();
                request.setAttribute("guestList", guestList);
            }

        }
        getServletContext()
                .getRequestDispatcher("/editor.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}