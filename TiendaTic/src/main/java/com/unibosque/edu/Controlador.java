package com.unibosque.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");
		
		
		switch (menu) {
		case "Principal":
			request.getRequestDispatcher("/Principal.jsp").forward(request, response);
			break;
		case "Usuarios":
			    if (accion.equals("Listar")) { // era minuscula
			    	try {
						ArrayList<Usuarios> lista = TestJSON.getJSON();
						request.setAttribute("lista", lista);
					}catch (Exception e) {
						e.printStackTrace();
					}
			
			    }else if (accion.equals("Agregar")) {
			    	Usuarios usuario = new Usuarios();
					usuario.setCedula_usuario(Long.parseLong(request.getParameter("txtcedula")));
					usuario.setNombre_usuario(request.getParameter("txtnombre"));
					usuario.setEmail_usuario(request.getParameter("txtemail"));
					usuario.setUsuario(request.getParameter("txtusuario"));
					usuario.setPassword(request.getParameter("txtpassword"));
					PrintWriter writer = response.getWriter();
					int respuesta = 0;
					try {
						respuesta = TestJSON.postJSON(usuario);
						if (respuesta == 200) {
							request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request,
									response);

						} else {
							writer.println("Error: " + respuesta);
						}
						writer.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
			    	
			    }
					
		}
	}	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
