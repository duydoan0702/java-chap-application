package Model;

public class Client {
    private String name;
    private String pass;
    private int portClient = 8080;
    private String hostClient = "192.168.2.183";

    public void setClient(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public int getPortClient() {
		return portClient;
	}

	public String getHostClient() {
		return hostClient;
	}

	public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
