package noobchain;

//Generate a digital signature with SHA256 algorithm
import java.security.MessageDigest;

public class StringUtil {
	// Apply Sha256 to an input string and return a string
	// as a digital signature.
	public static String applySha256(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) buffer.append('0');
				buffer.append(hex);
			}
			return buffer.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	} 

}
