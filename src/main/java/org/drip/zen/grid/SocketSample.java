
package org.drip.zen.grid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1) Review of IP Addresses, Port Number, Ping Command, Router, Gateway/Router.
 * 2) Client/Server Run Through Song and Dance - Slow Detail.
 * 3) Server Startup - Bind to Port. Caveats.
 * 4) Client Startup, and Connection to Server.
 * 5) Server Acceptance of a Connection and ready to Process it.
 * 6) Client then sends Server a Message.
 * 7) Server reads it, sends Client a Response.
 * 8) Both Client and Server display the Message.
 * 9) Passing "Client"/"Server" Arguments into the "Main".
 * 10) Running this in a Command Line.
 * 
 * @author Spooky
 */

public class SocketSample {

	@SuppressWarnings ("resource") static void RunServer (int listenerPort)
		throws Exception
	{
		ServerSocket mySocketListener = new ServerSocket (listenerPort);

		System.out.println ("[Server] => The Server is listening on port " + listenerPort);

    	Socket s = mySocketListener.accept();

		System.out.println ("[Server] => Received a Connection from Client " + s);

		while (true) {
			InputStream inputStream = s.getInputStream();

			InputStreamReader inputReader = new InputStreamReader (inputStream);

			BufferedReader bufferedReader = new BufferedReader (inputReader);

			java.lang.String request = bufferedReader.readLine();

        	System.out.println ("[" + s.toString() + "] => " + request);

    		OutputStream outputStream = s.getOutputStream();

    		PrintWriter pw = new PrintWriter (outputStream, true);

    		String response = System.console().readLine();

        	pw.write (response + "\n");

        	pw.flush();
		}
	}

	@SuppressWarnings ("resource") static void RunClient (String serverAddress, int serverPort)
		throws Exception
	{
		Socket clientSocket = new Socket (serverAddress, serverPort);

		System.out.println ("[Client] => The Client connected to " + serverAddress + "/" + serverPort);

		boolean listenForMessages = true;

		while (listenForMessages) {
			OutputStream outputStream = clientSocket.getOutputStream();

			String message = System.console().readLine();

			PrintWriter pw = new PrintWriter (outputStream, true);

	    	pw.write (message + "\n");

	    	pw.flush();

			InputStream inputStream = clientSocket.getInputStream();

			InputStreamReader inputReader = new InputStreamReader (inputStream);

			BufferedReader bufferedReader = new BufferedReader (inputReader);

			java.lang.String response = bufferedReader.readLine();

	    	System.out.println ("[" + serverAddress + ":" + serverPort + "] => " + response);

	    	if (response.equalsIgnoreCase ("bye") || response.equalsIgnoreCase ("quit"))
	    		listenForMessages = false;
		}
	}

	public static final void main (String[] inputArray)
		throws Exception
	{
		String server = "127.0.0.1";
		int listenerPort = 9090;

		String command = inputArray[0];

		if (command.equalsIgnoreCase ("SERVER"))
			RunServer (listenerPort);

		if (command.equalsIgnoreCase ("CLIENT"))
			RunClient (server, listenerPort);
	}
}
