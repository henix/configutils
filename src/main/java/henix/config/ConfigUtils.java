package henix.config;

public class ConfigUtils {

	public static DBSpec db(String host, String schema, String username, String password) {
		return new DBSpec(host, schema, username, password);
	}
}
