package Sever;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Model.Client;
import Tags.Decode;
import Tags.Tags;
import View.Login;




public class SeverHandler {
	private ServerSocket server;
	private ArrayList<Client> clients;
	private Socket socket = new Socket();
	private ObjectOutputStream output;
	private ObjectInputStream input;
	public boolean isStop = false, isExit = false;
	
	public SeverHandler(int PORT) {
		try {
			server = new ServerSocket(PORT);
			clients = new ArrayList<Client>();
			(new WaitForConnect()).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop() throws IOException {
		isStop = true;
		server.close();
		socket.close();
	}
	
	private boolean isExsistName(String name) throws Exception {
		if (clients == null)
			return false;
		int size = clients.size();
		for (int i = 0; i < size; i++) {
			Client client = clients.get(i);
			if (client.getName().equals(name))
				return true;
		}
		return false;
	}
	
	private void saveNewClient(String name, String pass) throws Exception {
		Client newClient = new Client();		
		if (clients.size() == 0)				
			clients = new ArrayList<Client>();
		newClient.setClient(name, pass);		
		clients.add(newClient);					
	}
	
//	show status of state
	private String sendSessionAccept() throws Exception {
		String message = Tags.SESSION_ACCEPT_OPEN_TAG;
		int size = clients.size();				
		for (int i = 0; i < size; i++) {		
			Client client = clients.get(i);	
			message += Tags.CLIENT_OPEN_TAG;			
			message += Tags.CLIENT_NAME_OPEN_TAG;
			message += client.getName();
			message += Tags.CLIENT_NAME_CLOSE_TAG;
			message += Tags.PASSWORD_OPEN_TAG;
			message += client.getPass();
			message += Tags.PASSWORD_CLOSE_TAG;
			message += Tags.CLIENT_CLOSE_TAG;			
		}
		message += Tags.SESSION_ACCEPT_CLOSE_TAG;	
		return message;
	}
	
	//clinet connect to server 
	public boolean waitForConnection() throws Exception {
		socket = server.accept();
		input = new ObjectInputStream(socket.getInputStream());
		String message = (String) input.readObject();
		ArrayList<String> getData = Decode.getUser(message);
		AdminGUI.updateMessage(message);
		if(getData != null) {
			if (!isExsistName(getData.get(0))) {						
				saveNewClient(Login.getUsernameTF(), Login.getPassTF());			
				AdminGUI.updateMessage(getData.get(0));	
				AdminGUI.updateNumberClient();
			} else
				return false;
		}else {
			int size = clients.size();
			
			Decode.updateClientOnline(clients, message);
			if(size != clients.size()) {
				isExit = true;
				AdminGUI.decreaseNumberClient();
			}
		}
		return true;
	}
	
	public class WaitForConnect extends Thread {
		@Override
		public void run() {
			super.run();
			try {
				while(!isStop) {
					if(waitForConnection()) {
						if(isExit) {
							isExit = false;
						} else {
							output = new ObjectOutputStream(socket.getOutputStream());
							output.writeObject(sendSessionAccept());
							output.flush();
							output.close();
						}
					} else {
						output = new ObjectOutputStream(socket.getOutputStream());
						output.writeObject(Tags.SESSION_DENY_TAG);
						output.flush();
						output.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

