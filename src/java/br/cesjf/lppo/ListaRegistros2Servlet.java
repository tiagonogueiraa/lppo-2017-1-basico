package br.cesjf.lppo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.Clock.system;
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
//caminho da aplicação
@WebServlet(name = "Listaregistros2Sevlet", urlPatterns = {"/lista2.html"})
public class ListaRegistros2Servlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
                       try {
                       Class.forName("org.apache.derby.jdbc.ClientDriver");
                       String url ="jdbc:derby://localhost:1527/lpto-2017-1";
                       Connection conexao = DriverManager.getConnection(url, "usuario" , "senha");
                       System.out.println("Conexao aberta com sucesso!");
                       
                           
                            //mandar comandos após a conexão ter sido feita
                            //comando, apenas diminuir os que estiverem acima de 100
                            String sql = "select nome, (200-qtd) as quantidade from produto where qtd<200";
                            Statement operacao = conexao.createStatement();
                                //executar uma ação ou uma query
                                ResultSet resultado = operacao.executeQuery(sql);
                                out.print("Nome\t\t\tA Comprar\tAtualizado");
                                while(resultado.next())
                                {
                                    out.print("\n"+resultado.getString("nome"));
                                    out.print("\t\t"+resultado.getInt("quantidade"));
                                    //System.out.print("\t"+resultado.getTimestamp("atualizado"));
                                }
                       
                   } catch (ClassNotFoundException ex) {
                       Logger.getLogger(ListaRegistros2Servlet.class.getName()).log(Level.SEVERE, null, ex);
                            System.err.println("Driver não indisponível");
                            
                   } catch (SQLException ex) {
                       Logger.getLogger(ListaRegistros2Servlet.class.getName()).log(Level.SEVERE, null, ex);
                            System.err.println("Problema ao acessar o banco!");
                   }
    }
}


