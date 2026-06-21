/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardo-silva
 */
public class Criptografia {
    
    public String criptografar(char[] dado) {
        try {
            String resultado;
            MessageDigest cript = MessageDigest.getInstance("SHA-512");
            String salt = "suamaeaqueladisgracadagordaperua";
            cript.update(salt.getBytes());
            byte[] nova = cript.digest(
                    Arrays.toString(dado).getBytes("UTF-8"));
            StringBuilder builder = new StringBuilder();
            
            for(byte b : nova){
                builder.append(String.format("%02X", b  & 0xFF));
            }
            
            resultado = builder.toString();
            
            System.out.println(resultado);
            
            return resultado;
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "error:" + e);
            return null;
        }
    }
    
}
