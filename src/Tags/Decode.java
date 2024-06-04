package Tags;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Client;



public class Decode {
    
    private static Pattern createAccount = Pattern
            .compile(Tags.SESSION_OPEN_TAG 
                     + Tags.CLIENT_NAME_OPEN_TAG + ".*" + Tags.CLIENT_NAME_CLOSE_TAG
                     + Tags.PASSWORD_OPEN_TAG + ".*" + Tags.PASSWORD_CLOSE_TAG 
                     + Tags.SESSION_CLOSE_TAG);
    
    private static Pattern request = Pattern
			.compile(Tags.SESSION_KEEP_ALIVE_OPEN_TAG + Tags.CLIENT_NAME_OPEN_TAG
					+ "[^<>]+" + Tags.CLIENT_NAME_CLOSE_TAG
					+ Tags.STATUS_OPEN_TAG + "(" + Tags.SERVER_ONLINE + "|"
					+ Tags.SERVER_OFFLINE + ")" + Tags.STATUS_CLOSE_TAG
					+ Tags.SESSION_KEEP_ALIVE_CLOSE_TAG);
    
    public static ArrayList<String> getUser(String message) {
        ArrayList<String> user = new ArrayList<String>();
        Matcher matcher = createAccount.matcher(message);
        if (matcher.matches()) {   
            Pattern findName = Pattern.compile(Tags.CLIENT_NAME_OPEN_TAG + ".*" + Tags.CLIENT_NAME_CLOSE_TAG);          
            Pattern findPass = Pattern.compile(Tags.PASSWORD_OPEN_TAG + ".*" + Tags.PASSWORD_CLOSE_TAG);
                 
            Matcher nameMatcher = findName.matcher(message);
            if (nameMatcher.find()) {
                String name = nameMatcher.group(0);
                user.add(name.substring(Tags.CLIENT_NAME_OPEN_TAG.length(), name.length() - Tags.CLIENT_NAME_CLOSE_TAG.length()));
            } else {
                return null; 
            }
                     
            Matcher passMatcher = findPass.matcher(message);
            if (passMatcher.find()) {
                String pass = passMatcher.group(0);
                user.add(pass.substring(Tags.PASSWORD_OPEN_TAG.length(), pass.length() - Tags.PASSWORD_CLOSE_TAG.length()));
            } else {
                return null; 
            }
        } else {
            return null; 
        }
        return user;
    }
    
    public static ArrayList<Client> updateClientOnline(ArrayList<Client> clientList, String message){
    	Pattern alive = Pattern.compile(Tags.STATUS_OPEN_TAG
				+ Tags.SERVER_ONLINE + Tags.STATUS_CLOSE_TAG);
    	Pattern findName = Pattern.compile(Tags.CLIENT_NAME_OPEN_TAG + "[^<>]*" 
    			+ Tags.CLIENT_NAME_CLOSE_TAG);
    	
    	if(request.matcher(message).matches()) {
    		Matcher findState = alive.matcher(message);
    		if(findState.find()) {
    			return clientList;
    		}
    		findState = findName.matcher(message);
    		if(findState.find()) {
    			String findClient = findState.group(0);
    			int size = clientList.size();
    			String name = findClient.substring(11, findClient.length() - 12);
    			for(int i = 0 ; i < size ; i++) {
    				if(name.equals(clientList.get(i).getName())) {
    					clientList.remove(i);
    					break;
    				}
    			}
    		}
    	}
    	return clientList;
    	
    }

}
