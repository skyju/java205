package ch03;
import java.util.InputMismatchException;
import java.util.Scanner;

//Calculator 클래스를 정의해봅시다. 
public class Calculator {
	
	public static String inputValue() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return input;
	}
	
	//① 정수 두 개를 매개변수의 인자로 전달받아 더하기연산 후 반환하는 메소드를 정의
	//② 정수 두 개를 매개변수의 인자로 전달받아 빼기연산 후 반환하는 메소드를 정의
	//③ 정수 두 개를 매개변수의 인자로 전달받아 곱하기연산 후 반환하는 메소드를 정의
	//④ 정수 두 개를 매개변수의 인자로 전달받아 나누기연산 후 반환하는 메소드를 정의
	static int add(int x, int y) {
		int z = x+y;
		return z;
	}
	static int substract(int x, int y) {
		int z = x-y;
		return z;
	}
	static int multiply(int x, int y) {
		int z = x*y;
		return z;
	}
	static double divide(int x, int y) {
		double z = x/y;
		return z;
	}
	
	//⑤ 실수 반지름 하나를 매개변수의 인자로 전달받아 원의 둘레를 구해 반환하는 메소드를 반환하는 메소드를 정의
	//⑥ 실수 반지름 하나를 매개변수의 인자로 전달받아 원의 넓이를 구해 반환하는 메소드를 반환하는 메소드를 정의
	//원의 둘레 : 2 x π x r , 월의 넓이 : π x r x r
	static double circumference(double x) {
		double y = 2*x*3.141;
		return y;
	}
	
	static double areaOfCircle(double x) {
		double y = 3.141*x*x;
		return y;
	}
	
	public static void main(String[] args) {
		
	//⑦ main() 메소드를 정의하고 각각의 메소드를 호출해서 결과를 콘솔에 출력해봅시다.
	//⑧ 콘솔에서 사용자에게 데이터를 받아 메소드를 호출할 때 사용자에게 받은 데이터를 
	//메소드의 매개변수의 인자로 전달하는 코드를 main() 메소드에 추가해봅시다.
		
		try {
		System.out.println("더할 두 값을 입력해주세요.");
		int a1 = Integer.parseInt(inputValue());
		int b1 = Integer.parseInt(inputValue());
		int c1 = add(a1,b1);
		System.out.println("두 값을 더한 값은 "+c1+"입니다.\n");
		
		System.out.println("뻴 두 값을 입력해주세요.");
		int a2 = Integer.parseInt(inputValue());
		int b2 = Integer.parseInt(inputValue());
		int c2 = substract(a2,b2);
		System.out.println("두 값을 뺀 값은 "+c2+"입니다.\n");
		
		System.out.println("곱할 두 값을 입력해주세요.");
		int a3 = Integer.parseInt(inputValue());
		int b3 = Integer.parseInt(inputValue());
		int c3 = multiply(a3,b3);
		System.out.println("두 값을 곱한 값은 "+c3+"입니다.\n");
		
		System.out.println("나눌 두 값을 입력해주세요.");
		int a4 = Integer.parseInt(inputValue());
		int b4 = Integer.parseInt(inputValue());
		double c4 = divide(a4,b4);
		System.out.println("두 값을 나눈 값은 "+c4+"입니다.\n");
		
		System.out.println("원의 반지름을 입력해주세요.");
		double a5 = Double.parseDouble(inputValue());
		double b5 = circumference(a5);
		System.out.println("원의 둘레는 "+b5+"입니다.");
		double c5 = areaOfCircle(a5);
		System.out.println("원의 넓이는 "+c5+"입니다.");
		
		} catch(ArithmeticException e) {
			System.out.println("나눌 수 없는 값입니다.");
		} catch(InputMismatchException e) {
			System.out.println("계산할 수 없는 값입니다.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}