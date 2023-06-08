
import java.util.Scanner;

public class SelectPolicies {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		outerLoop :
		while(true) {
			System.out.println("=================================================");
			System.out.println("1.FCFS / 2.SJF / 3.SRTF / 4.Round Robin / 5.종료");
	        System.out.print("원하는 메뉴 번호 입력: ");
	        int n = sc.nextInt();
			
	        switch(n) {
	        case 1: FCFS fcfs = new FCFS();
					fcfs.execute();
					break;
					
	        case 2: SJF sjf = new SJF();
	        		sjf.execute();
	        		break;
	        		
	        case 3: SRTF srtf = new SRTF();
					srtf.execute();
					break;
					
	        case 4: RoundRobin rr = new RoundRobin();
	        		rr.execute();
					break;
					
	        case 5: System.out.println("=================================================");
	        		break outerLoop;
	        		
	        default: System.out.printf("잘못된 입력입니다. 다시 입력하세요.\n\n");
					break;
	        }
		}
		
		sc.close();
	}
}
