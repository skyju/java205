package ch03;
import java.util.InputMismatchException;
import java.util.Scanner;

//Calculator 클래스를 정의해봅시다.
//① 정수 두 개를 매개변수의 인자로 전달받아 더하기연산 후 반환하는 메소드를 정의
//② 정수 두 개를 매개변수의 인자로 전달받아 빼기연산 후 반환하는 메소드를 정의
//③ 정수 두 개를 매개변수의 인자로 전달받아 곱하기연산 후 반환하는 메소드를 정의
//④ 정수 두 개를 매개변수의 인자로 전달받아 나누기연산 후 반환하는 메소드를 정의
//⑤ 실수 반지름 하나를 매개변수의 인자로 전달받아 원의 둘레를 구해 반환하는 메소드를 반환하는 메소드를 정의
//⑥ 실수 반지름 하나를 매개변수의 인자로 전달받아 원의 넓이를 구해 반환하는 메소드를 반환하는 메소드를 정의
//원의 둘레 : 2 x π x r , 월의 넓이 : π x r x r
//⑦ main() 메소드를 정의하고 각각의 메소드를 호출해서 결과를 콘솔에 출력해봅시다.
//⑧ 콘솔에서 사용자에게 데이터를 받아 메소드를 호출할 때 사용자에게 받은 데이터를 
//메소드의 매개변수의 인자로 전달하는 코드를 main() 메소드에 추가해봅시다.

public class Calculator {
	
	public static String inputValue() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return input;
	}
	
	long add(int x, int y) {
		return (long)x+y; //하나만 해도 뒤에는 자동형변환 이루어짐
	}
	
	long substract(int x, int y) {
		return (long)x-y;
	}
	
	long multiply(int x, int y) {
		return (long)x*y;
	}
	
	double divide(int x, int y) {
		return x/y;
	}
	
	// 클래스 이름 : 대문자 시작, 케멀 케이스
	// 변수 이름    : 소문자 시작, 케멀 케이스
	// 상수           : 모두 대문자
	double pi1 = 3.14;
	float pi2 = 3.14f;
	final float PI = 3.14f;
	
	float circumference(int r) {
		return 2*PI*r;
	}
	
	float areaOfCircle(int r) {
		return PI*r*r;
	}
	
	public static void main(String[] args) {
		
		try {
		Calculator cal = new Calculator();
		
		System.out.println("연산할 두 값을 입력해주세요.");
		int a = Integer.parseInt(inputValue());
		int b = Integer.parseInt(inputValue());
		long c1 = cal.add(a,b);
		long c2 = cal.substract(a,b);
		long c3 = cal.multiply(a,b);
		double c4 = cal.divide(a,b);
		System.out.printf("%d + %d = %d\n",a,b,c1);
		System.out.printf("%d - %d = %d\n",a,b,c2);
		System.out.printf("%d * %d = %d\n",a,b,c3);
		System.out.printf("%d / %d = %g\n",a,b,c4);
		
		System.out.println();
		System.out.println("원의 반지름을 입력해주세요.");
		int a1 = Integer.parseInt(inputValue());
		float c5 = cal.circumference(a1);
		System.out.printf("원의 둘레는 %g 입니다.\n",c5);
		float c6 = cal.areaOfCircle(a1);
		System.out.printf("원의 넓이는 %g 입니다.\n",c6);
		
		} catch(ArithmeticException e) {
			System.out.println("0으로는 나눌 수 없습니다.");
		} catch(InputMismatchException e) {
			System.out.println("계산할 수 없는 값입니다.");
		} catch(NumberFormatException e) {
			System.out.println("계산할 수 없는 값입니다.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
