
import java.util.Scanner;

public class SelectPolicies {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		outerLoop :
		while(true) {
			System.out.println("=================================================");
			System.out.println("1.FCFS / 2.SJF / 3.SRTF / 4.Round Robin / 5.����");
	        System.out.print("���ϴ� �޴� ��ȣ �Է�: ");
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
	        		
	        default: System.out.printf("�߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.\n\n");
					break;
	        }
		}
		
		sc.close();
	}
}
