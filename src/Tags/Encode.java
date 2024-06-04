package Tags;

public class Encode {
	
	public static String getCreateAccount(String name, String pass) {
		return Tags.SESSION_OPEN_TAG + Tags.CLIENT_NAME_OPEN_TAG + name
				+ Tags.CLIENT_NAME_CLOSE_TAG + Tags.PASSWORD_OPEN_TAG + pass
				+ Tags.PASSWORD_CLOSE_TAG + Tags.SESSION_CLOSE_TAG;
	}

}
