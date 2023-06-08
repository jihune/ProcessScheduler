
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SJF {
    public void execute() {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        System.out.printf("\nSJF 알고리즘입니다.\n");
        System.out.print("프로세스의 개수 입력: ");
        int n = sc.nextInt();
        Process[] pList = new Process[n];
        
        for (int i = 0; i < n; i++) {
            String id = "P" + (i + 1);
            System.out.printf("%s Burst Time 정수 입력: ", id);
            double burstTime = sc.nextDouble();
            System.out.printf("%s Arrival Time 정수 입력: ", id);
            double arrivalTime = sc.nextDouble();
            pList[i] = new Process(id, burstTime, arrivalTime);
        }

        // 실행 시간순으로 정렬
        Arrays.sort(pList, Comparator.comparingDouble(p -> p.burstTime));

        // 스케줄링 수행
        double currentTime = 0;
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        for (int i = 0; i < n; i++) {
            Process p = pList[i];

            if ( (currentTime < p.arrivalTime) && (0 < p.arrivalTime) ) {
            	currentTime = p.arrivalTime;
            }
            
            p.responseTime = currentTime; 
            p.waitingTime = Math.max(0, currentTime - p.arrivalTime);
            totalWaitingTime += p.waitingTime;
            currentTime += p.burstTime;
            p.turnaroundTime = p.waitingTime + p.burstTime;
            totalTurnaroundTime += p.turnaroundTime;
            p.remainingTime = 0;
        }

        // 결과 출력
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
