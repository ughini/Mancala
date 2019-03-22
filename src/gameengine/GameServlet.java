package gameengine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GameHandler game;

    public GameServlet() {
    	super();
    	
    	game = new GameHandler();        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String command = request.getParameter("command");
		int pitnumber = new Integer(request.getParameter("pitnumber"));
		
		if ( command.equals("reset")) {
			 this.handleReset();
		}
		if ( command.equals("pick")) {
			this.handlePick(pitnumber);
		}

		response.getWriter().append( this.buildGameInterface());
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void handlePick(int number) {		
		if (!game.getGameBoard().emptySide()) {
			game.clickPit(number);
		}
	}
	
	private void handleReset() {
		game.reset();     
	}
	
	private String buildGameInterface() {
		return 
		"<!DOCTYPE html> \n" +
		"<html> \n" +
		"<script src='js/gamescripts.js'></script> \n" +
		"<link  rel='StyleSheet' type='text/css' href='css/gamestyles.css'/> \n" +
		"<head> \n" +
		"<title>Mancala Game</title> \n" +
		"</head> \n" +
		"<body> \n" +
		game.getMessage() + "\n" +
		"	<BR> \n" +
		"	<form name='formGameBoard'  id='formGameBoard' method='post' action='GameServlet'> \n" +
		"		<input type='hidden' id='pitnumber' name='pitnumber' value='0'/>  \n" +
		"		<input type='hidden' id='command' name='command' value='pick'/> \n" +
		game.getGameBoard().print() +
		game.getGameBoard().printPits() +
		game.getGameBoard().printStones() +
		"		<input type='submit' value='Reset Game' onclick='resetGame()'> \n" +
		"	</form> \n" +
		"</body> \n" +
		"</html> \n"
		;
	}

}
