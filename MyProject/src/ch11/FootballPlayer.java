package ch11;

public class FootballPlayer implements Comparable<FootballPlayer>{
	String name;
	int number;
	String team;
	int age;
	
	public FootballPlayer(String name, int number, String team, int age) {
		this.name = name;
		this.number = number;
		this.team = team;
		this.age = age;
	}
	
	public void showInfo() {
		System.out.println("============");
		System.out.println("이 름: "+name);
		System.out.println("번 호: "+number);
		System.out.println("소속팀: "+team);
		System.out.println("나 이: "+age);
		System.out.println("============");
	}
	
	
	// 문제 2번
	@Override
	public int hashCode() {
		return age%3;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj != null && obj instanceof FootballPlayer) {
			FootballPlayer fp = (FootballPlayer)obj;
			if( this.team.equals(fp.team) && 
				this.name.equals(fp.name) &&
				this.age == fp.age) {
				result = true;
			}
		}
		return result;
	}

	// 문제 3번
	@Override
	public int compareTo(FootballPlayer o) {
		//team 비교
		if(this.team.compareTo(o.team) > 0) {
			return 1;
		} else if (this.team.compareTo(o.team) < 0) {
			return -1;
		} else { //team이 같은경우 name 비교
			if(this.name.compareTo(o.name) > 0) {
				return 1;
			} else if (this.name.compareTo(o.name) < 0) {
				return -1;
			} else { //team과 name이 같은 경우 number비교
				if(this.number > o.number) {
					return 1;
				} else if (this.number < o.number) {
					return -1;
				} else { //모두 같은 경우
					return 0;
				}
			}
		}
	}
	
	
	
}
