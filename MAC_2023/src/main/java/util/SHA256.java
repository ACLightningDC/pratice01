package util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Date;

//단방향 암호화(SHA256)로 암호화하면 복호화가 불가능함
public class SHA256 {
	/*암호를 보호나는 가장 좋은 방법은 소금을 친 해싱을 사용하는것이다.
	 * 소금이 고정이 아니면 로그인할 때 비밀번호의 소금도 변경되어 로그인이 안됨(따라서, 소금값을 고정시킴)
	 */
	private static final String salt= "맥카페";
	
	private SHA256(){}
	
	public static String encodeSHA256(String password){
		String result="";
		
		try {
			byte[] saltByte= salt.getBytes();
			byte[] passwordByte= password.getBytes("utf-8");
			
			/*
			 * 기본값으로 채워진 byte객체에 바이트로 변경된 소금값과 비번으로 재움
			 */
			byte[] saltPassword = new byte[saltByte.length + passwordByte.length];
			
			//소금값을 암호 앞, 뒤 상관없다. 앞에 붙이는게 일반적 
			//앞:소금값 , 뒤 :비밀번호값
			System.arraycopy(saltByte, 0, saltPassword, 0, saltByte.length);
			System.arraycopy(passwordByte, 0, saltPassword, saltByte.length, passwordByte.length);
			
			//SHA-256알고리즘 사용하여 단방향 암호화
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			//md.update()에 바로 입력한 값을 해싱하는 함수다. 이값은 반드시 바이트 배열이어야 함 
			md.update(saltPassword);
			
			byte[] saltPasswordDigest=md.digest();//md객체의 다이제스트를 얻어 saltPassword를 갱신
			
			/**
			 * StringBuffer(멀티스레드) StringBuilder(싱글스레드)사용
			 * 문자열 연산이 많을 때	String 대신 사용하여 메모리의 효율을 높인다. 
			 */
			
			//String str="";//대신
			StringBuffer sb = new StringBuffer();
			
			for(int i=0 ;i<saltPasswordDigest.length ; i++) {
				//(saltPasswordDigest[i]&0xFF)+0x100
				//str += Integer.toString((saltPasswordDigest[i]&0xFF)+256,16).substring(1);
				
				sb.append(Integer.toString((saltPasswordDigest[i]&0xFF)+0x100,16).substring(1));
				sb.append(Integer.toString((saltPasswordDigest[i]&0xFF)+256,16).substring(1));
			}
			
			result = sb.toString();
			/* 바이트 배열값 하나하나를 16진수로 만드는 방법
			 이때 ,&0xFF 하면 (이때, 이 연산을 하는 이유? 연산시 CPU의 연산장치는 정수 Int 기준으로 한다.)
			 	(예) 나머지 부분은 부호비트로 채워짐 
			 		byte 10001010 (양수) => int 0000000000000000000000 '10001010'(양수)
			 		byte 10001010 (음수) => int 1111111111111111111111 '10001010'(음수)
			 	위 결과처럼 음수값의 1로 채워진 부분을 전부 0 으로 바꾸기 위해 &0xFF 연산함
			 		& 00000000000000000000000000 ' 11111111'
			 		----------------------------------------------
			 		  00000000000000000000000000 '10001010'
			 	
			 	
			 1-> 1
			 2-> 2
			 -----
			 10-> a
			 11-> b
			 12-> c
			 13-> d
			 14-> e
			 15-> f
			 16-> 10(여기서부터 2자리가 됨
			  
			  
			 10진수 100(0x100) 더하는 이유? 더하여 강제로 3자리의 String으로 변경하기 위해
			 1-> 101
			 2-> 102
			 ----
			 10 ->10a
			 11 ->10b
			 12 ->10c
			 13 ->10d
			 14 ->10e
			 15 ->
			 
			 위 결과에서 제일앞의 1을 제외시킨 나머지 2자리로 된 16진수가됨
			subString()메서드 이용하여 제일앞의 1을 제외시킨 나머지가 바로 2자로 된 16진수가 됨
			 */
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("encodeSHA256 : " + result);
		return result;
	}
	
	//임시 비밀번호 생성 : 8자리의 '영어대소문자+숫자'가 결합
	
	public static String getRandomPassword(int size) { //size가 8이면 8자리 임시 비밀번호가 생성
//		
//		char[] charSet =  { 
//				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
//				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
//				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
//				'~','!','#'
//		         };//특수문자 포함
//		
//		
		char[] charSet =  { 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
		};//특수문자 제외
		
		
		
		StringBuffer sb = new StringBuffer();//'A'
		
		//강력한 난수 발생시키기 위해 SecureRandom 사용
		SecureRandom sr = new SecureRandom();
		sr.setSeed(new Date().getTime());
		
		int len = charSet.length;
		int idx = 0;
		for(int i = 0 ; i<size ; i++) {
			
			//방법-1
			//idx= (int)(Math.random() * len);
			
			//방법-2 : 강력한 난수 발생
			//idx = sr.nextInt(len);
			idx = sr.nextInt(charSet.length);//예:idx = 10 =>idx=0
			sb.append(charSet[idx]);//charSet[11]의 값은 'A' => charSet[0]의 값은 '0'
		}
		
		return sb.toString();
		
	}
	
}












