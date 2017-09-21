package pe.com.starcom.util;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncriptacionAES {

	public static void main(String[] args) throws Exception {

		String text = "112233";

		Long tiempoTrascurrido = System.currentTimeMillis();
		byte[] codedtext = new EncriptacionAES().encrypt(text);
		Long tiempoTrascurrido2 = System.currentTimeMillis();
		Long result = tiempoTrascurrido2 - tiempoTrascurrido;
		int tiempoTrascurridoSeg = (int) (result / 1000);// millisegundos a segundos
		System.out.println("TIEMPO TRANSCURRIDO1=" + tiempoTrascurridoSeg);
		
		
		
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());

//	ip: prueba database
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/starcom", "root", "admin");
//	for(int i=1;i<=18;i++){	
		
    
	String sql = "update usuario set password = ? where idusuario= 2";
    
    PreparedStatement preparedStatement = conn.prepareStatement(sql);	            
    preparedStatement.setBytes(1, codedtext);	            
    preparedStatement.executeUpdate();
    
    
//	}
    conn.close();
		
		
		
		
		
		/*Long tiempoTrascurrido3 = System.currentTimeMillis();
		String decodedtext = new EncriptacionAES().decrypt(codedtext);
		Long tiempoTrascurrido4 = System.currentTimeMillis();
		Long result2 = tiempoTrascurrido4 - tiempoTrascurrido3;
		int tiempoTrascurridoSeg2 = (int) (result / 1000);// millisegundos a segundos
		System.out.println("TIEMPO TRANSCURRIDO2=" + tiempoTrascurridoSeg2);*/
		
		System.out.println(codedtext); // this is a byte array, you'll just see
										// a reference to an array
		//System.out.println(decodedtext); // This correctly shows "kyle boon"
	}

	public static byte[] encrypt(String message) throws Exception {
		final MessageDigest md = MessageDigest.getInstance("md5");
		final byte[] digestOfPassword = md.digest("S3rv1r"
				.getBytes("utf-8"));
		final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		for (int j = 0, k = 16; j < 8;) {
			keyBytes[k++] = keyBytes[j++];
		}

		final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
		final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		//System.out.println("ENCRIPT:"+keyBytes.toString());
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);

		final byte[] plainTextBytes = message.getBytes("utf-8");
		final byte[] cipherText = cipher.doFinal(plainTextBytes);
		// final String encodedCipherText = new sun.misc.BASE64Encoder()
		// .encode(cipherText);

		return cipherText;
	}

	public static String decrypt(byte[] message) throws Exception {
		final MessageDigest md = MessageDigest.getInstance("md5");
		final byte[] digestOfPassword = md.digest("S3rv1r"
				.getBytes("utf-8"));
		final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		for (int j = 0, k = 16; j < 8;) {
			keyBytes[k++] = keyBytes[j++];
		}

		final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
		final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		
		//System.out.println("DECRIPT:"+keyBytes.toString());
		
		decipher.init(Cipher.DECRYPT_MODE, key, iv);

		// final byte[] encData = new
		// sun.misc.BASE64Decoder().decodeBuffer(message);
		final byte[] plainText = decipher.doFinal(message);

		return new String(plainText, "UTF-8");
	}
	
	public String generarLlave(){
		
		return"";
	}
}
