package br.com.ultraworks.erp.core.util;

public interface EncryptionService {
	String decrypt(String strToDecrypt, String secret, String salt);
}
