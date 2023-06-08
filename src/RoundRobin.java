
import java.util.Scanner;

public class RoundRobin {
    public void execute() {
        Scanner sc = new Scanner(System.in);

        // �Է� �ޱ�
        System.out.printf("\nRound Robin �˰����Դϴ�.\n");
        System.out.print("���μ����� ���� �Է�: ");
        int n = sc.nextInt();
        System.out.print("Time Quantum �Է�: ");
        int timeQuantum = sc.nextInt();
        Process[] pList = new Process[n];
        
        for (int i = 0; i < n; i++) {
            String id = "P" + (i + 1);
            System.out.printf("%s Burst Time ���� �Է�: ", id);
            double burstTime = sc.nextDouble();
            pList[i] = new Process(id, burstTime);
        }

        // �����ٸ� ����
        double currentTime = 0;
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        int executedCount = 0;
        Process currentProcess = null;
        
        // ��� ���μ����� ������ �Ϸ�Ǹ� while�� ����
        while (executedCount < n) {
        	
            // pList�� ���������� ���ư��� ��� ���μ����� p.remainingTime == 0�� �ɶ����� �ݺ�
            for (Process p : pList) {
            	currentProcess = p;
            	
            	if (currentProcess.remainingTime == 0) {
            		continue;
            	}
            	
            	// �� ���μ����� ó�� burst �Ǿ��� ���� responseTime ���
            	if (currentProcess.responseTime == -1) {
                    currentProcess.responseTime = currentTime;
                    currentProcess.roundCount++;
                }
            	
            	// �Է¹��� Time Quantum�� ���� ���μ����� remaingTime �� �� Burst
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
        
        // ��� ���
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
