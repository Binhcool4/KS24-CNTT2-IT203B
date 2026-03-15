package Cau6;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class DeadlockDetector implements Runnable {
    @Override
    public void run() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        while (true) {
            long[] threadIds = bean.findDeadlockedThreads();
            if (threadIds != null) {
                System.err.println("\n!!! PHÁT HIỆN DEADLOCK TẠI " + threadIds.length + " THREAD !!!");
            }
            try { Thread.sleep(5000); } catch (InterruptedException e) { break; }
        }
    }
}