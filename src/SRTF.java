
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SRTF {
    public void execute() {
        Scanner sc = new Scanner(System.in);

        // �Է� �ޱ�
        System.out.printf("\nSRTF �˰����Դϴ�.\n");
        System.out.print("���μ����� ���� �Է�: ");
        int n = sc.nextInt();
        Process[] pList = new Process[n];
        
        for (int i = 0; i < n; i++) {
            String id = "P" + (i + 1);
            System.out.printf("%s Burst Time ���� �Է�: ", id);
            double burstTime = sc.nextDouble();
            System.out.printf("%s Arrival Time ���� �Է�: ", id);
            double arrivalTime = sc.nextDouble();
            pList[i] = new Process(id, burstTime, arrivalTime);
        }

        // ���� �ð������� ����
        Arrays.sort(pList, Comparator.comparingDouble(p -> p.burstTime));
        
        // �����ٸ� ����
        double currentTime = 0;
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        int executedCount = 0;
        Process currentProcess = null;
        
        // ��� ���μ����� ������ �Ϸ�Ǹ� while�� ����
        while (executedCount < n) {
        	
            // ���� ���� ������ ���μ��� �� burst time�� ���� ���� ���μ��� ����
            for (Process p : pList) {
                if ((p.remainingTime > 0) && (p.arrivalTime <= currentTime)) {
                	
                    if (currentProcess == null) {
                        currentProcess = p;
                    }
                    else {
                        if (currentProcess.remainingTime > p.remainingTime) {
                            currentProcess = p;
                        }
                    }
                    
                }
            }

            // Arrival Time ���� CPU�� ��� ��� currentTime++
            if (currentProcess == null) {
                currentTime++;
            }
            else {
                if (currentProcess.responseTime == -1) {
                    currentProcess.responseTime = currentTime;
                }
                
                currentProcess.remainingTime--;
                currentTime++;
                
                if (currentProcess.remainingTime == 0) {
                    executedCount++;
                    currentProcess.turnaroundTime = currentTime - currentProcess.arrivalTime;
                    currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
                    totalTurnaroundTime += currentProcess.turnaroundTime;
                    totalWaitingTime += currentProcess.waitingTime;
                }
            }

            currentProcess = null;
        }
        
        // ��� ���
        System.out.printf("\nProcess ID | Arrival Time | Burst Time\n");
        for (Process p : pList) {
            System.out.printf("%-10s | %-12.1f | %-10.1f\n", p.id, p.arrivalTime, p.burstTime);
        }
        System.out.println();
        System.out.println("Process ID | Turnaround Time | Waiting Time | Response Time");
        for (Process p : pList) {
            System.out.printf("%-10s | %-15.1f | %-12.1f | %-12.1f\n", p.id, p.turnaroundTime, p.waitingTime, p.responseTime);
        }
        System.out.println();
        System.out.printf("Execution Time = %.1f\n", currentTime);
        System.out.printf("Total Waiting Time = %.1f\n", totalWaitingTime);
        System.out.printf("Average Waiting Time = %.1f\n", totalWaitingTime / n);
        System.out.printf("Total Turnaround Time = %.1f\n", totalTurnaroundTime);
        System.out.printf("Average Turnaround Time = %.1f\n\n", totalTurnaroundTime / n);
    }
}
