
import java.util.Scanner;

public class RoundRobin {
    public void execute() {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        System.out.printf("\nRound Robin 알고리즘입니다.\n");
        System.out.print("프로세스의 개수 입력: ");
        int n = sc.nextInt();
        System.out.print("Time Quantum 입력: ");
        int timeQuantum = sc.nextInt();
        Process[] pList = new Process[n];
        
        for (int i = 0; i < n; i++) {
            String id = "P" + (i + 1);
            System.out.printf("%s Burst Time 정수 입력: ", id);
            double burstTime = sc.nextDouble();
            pList[i] = new Process(id, burstTime);
        }

        // 스케줄링 수행
        double currentTime = 0;
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        int executedCount = 0;
        Process currentProcess = null;
        
        // 모든 프로세스가 실행이 완료되면 while문 종료
        while (executedCount < n) {
        	
            // pList를 순차적으로 돌아가며 모든 프로세스의 p.remainingTime == 0이 될때까지 반복
            for (Process p : pList) {
            	currentProcess = p;
            	
            	if (currentProcess.remainingTime == 0) {
            		continue;
            	}
            	
            	// 각 프로세스가 처음 burst 되었을 때의 responseTime 기록
            	if (currentProcess.responseTime == -1) {
                    currentProcess.responseTime = currentTime;
                    currentProcess.roundCount++;
                }
            	
            	// 입력받은 Time Quantum과 현재 프로세스의 remaingTime 비교 후 Burst
            	if (timeQuantum < currentProcess.remainingTime) {
            		currentTime += timeQuantum;
            		currentProcess.remainingTime -= timeQuantum;
            		currentProcess.roundCount++;
            	}
            	else if (timeQuantum == currentProcess.remainingTime) {
            		currentTime += currentProcess.remainingTime;
            		currentProcess.turnaroundTime = currentTime;
            		
            		currentProcess.waitingTime = currentTime;
            		while (true) {
            			if (currentProcess.roundCount == 1) {
            				currentProcess.waitingTime -= currentProcess.remainingTime;
            				break;
                		}
            			currentProcess.waitingTime -= timeQuantum;
            			currentProcess.roundCount--;
            		}
            		currentProcess.remainingTime = 0;
            		
            		totalTurnaroundTime += currentProcess.turnaroundTime;
                    totalWaitingTime += currentProcess.waitingTime;
            		executedCount++;
            	}
            	else {
            		currentTime += currentProcess.remainingTime;
            		currentProcess.turnaroundTime = currentTime;
            	
            		currentProcess.waitingTime = currentTime;
            		while (true) {
            			if (currentProcess.roundCount == 1) {
            				currentProcess.waitingTime -= currentProcess.remainingTime;
            				break;
                		}
            			currentProcess.waitingTime -= timeQuantum;
            			currentProcess.roundCount--;
            		}
            		currentProcess.remainingTime = 0;
            		
            		totalTurnaroundTime += currentProcess.turnaroundTime;
                    totalWaitingTime += currentProcess.waitingTime;
            		executedCount++;
            	}   	
            }
        }
        
        // 결과 출력
        System.out.printf("\nProcess ID | Burst Time\n");
        for (Process p : pList) {
            System.out.printf("%-10s | %-10.1f\n", p.id, p.burstTime);
        }
        System.out.println();  
        System.out.println("Process ID | Turnaround Time | Waiting Time | Response Time");
        for (Process p : pList) {
            System.out.printf("%-10s | %-15.1f | %-12.1f | %-12.1f\n", p.id, p.turnaroundTime, p.waitingTime, p.responseTime);
        }
        System.out.println();
        System.out.printf("Time Quantum = %d\n", timeQuantum);
        System.out.printf("Execution Time = %.1f\n", currentTime);
        System.out.printf("Total Waiting Time = %.1f\n", totalWaitingTime);
        System.out.printf("Average Waiting Time = %.1f\n", totalWaitingTime / n);
        System.out.printf("Total Turnaround Time = %.1f\n", totalTurnaroundTime);
        System.out.printf("Average Turnaround Time = %.1f\n\n", totalTurnaroundTime / n);
    }
}
