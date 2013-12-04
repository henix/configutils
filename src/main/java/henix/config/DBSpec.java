package henix.config;

public class DBSpec {

	public final String host, schema, username, password;

	public DBSpec(String host, String schema, String username, String password) {
		this.host = host;
		this.schema = schema;
		this.username = username;
		this.password = password;
	}
}
