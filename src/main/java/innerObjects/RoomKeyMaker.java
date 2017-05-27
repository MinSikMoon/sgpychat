package innerObjects;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RoomKeyMaker {
	private static String byteArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder();
		for(final byte b: a)
			sb.append(String.format("%02x", b&0xff));
		return sb.toString();
	}
	public static String makeRoomKey(){
		//1. local variables
		SecureRandom random;
		byte[] temp = null;
		//2. logic
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
			temp = random.getSeed(3);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteArrayToHex(temp);
	}
}
