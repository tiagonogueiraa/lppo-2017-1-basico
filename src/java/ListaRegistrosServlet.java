/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aluno
 */
@WebServlet(urlPatterns = {"/lista.html"})
public class ListaRegistrosServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListaRegistrosServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listagem de Produtos</h1>");
            try {
                //consigo acessar o banco? a linha abaixo, 44 pergunta isso.
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                    out.print("<p>Driver encontrado.<p>");
                    
                     String url ="jdbc:derby://localhost:1527/lpto-2017-1";
                       Connection conexao = DriverManager.getConnection(url, "usuario" , "senha");
                            out.println("Conexao aberta com sucesso!");
                            
                            String sql = "select * from produto";
                            Statement operacao = conexao.createStatement();
                                //executar uma ação ou uma query
                                ResultSet resultado = operacao.executeQuery(sql);
                                out.print("<p>Consulta realizada!<p>");
                                out.println("<pre>");
                                out.print("Nome \t\t\t QTD\t Atualizado");
                                while(resultado.next())
                                {
                                    out.print("\n"+resultado.getString("nome"));
                                    out.print("\t\t"+resultado.getInt("qtd"));
                                    out.print("\t"+resultado.getTimestamp("atualizado"));
                                }
                                out.println("</pre>");
                    
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
                    out.print("<h2>Driver não encontrado.</h2>");
            } catch (SQLException ex) {
                Logger.getLogger(ListaRegistrosServlet.class.getName()).log(Level.SEVERE, null, ex);
                    out.printf("<h2>Erro ao acessar o banco!");
                    
            }
            
            
            
            out.println("</body>");
            out.println("</html>");
        }
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

}
