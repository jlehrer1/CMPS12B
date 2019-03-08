import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Simulation {

    // The following function assembles the initial backup and/or storage queues.
    public static Job getJob(Scanner in) {
        String[] s = in.nextLine().split(" ");
        int a = Integer.parseInt(s[0]);
        int d = Integer.parseInt(s[1]);
        return new Job(a, d);
    }

    public static void main(String[] args) throws IOException {

        boolean traceCheck = false; // flag for traceFile prints

        // check number of command line arguments is 1
        if (args.length != 1) {
            System.out.println("Usage: Simulation <input file>");
            System.out.println();
            System.exit(1);
        }

        // open files
        Scanner inputFile = new Scanner(new File(args[0]));
        PrintWriter report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
        PrintWriter trace = new PrintWriter(new FileWriter(args[0] + ".trc"));

        // read in jobs from input file
        int m = inputFile.nextInt();
        inputFile.nextLine();

        Queue jobQueue = new Queue();
        Queue tempQueue = new Queue();

        // enqueue jobs from input file into jobQueue and tempQueue
        while (inputFile.hasNextLine()) {
            Job getJob = getJob(inputFile);
            jobQueue.enqueue(getJob);
            tempQueue.enqueue(getJob); // record original order of jobs
        }

        //format the files
        report.println("Report file: " + args[0] + ".rpt");
        report.println(m + " Jobs:");
        report.println(jobQueue.toString());
        report.println();
        report.println("***********************************************************");

        trace.println("Trace file: " + args[0] + ".trc");
        trace.println(m + " Jobs:");
        trace.println(jobQueue.toString());
        trace.println();

        for (int n = 1; n <= m - 1; n++) {
            int time = 0;
            int totalWait = 0;
            int maxWait = 0;
            double averageWait = 0.00;


            // print processor heading in trace file
            trace.println("*****************************");
            if (n == 1) {
                trace.println((n + " processor:"));
            } else {
                trace.println((n + " processors:"));
            }
            trace.println("*****************************");

            Queue[] processorQueue = null;
            processorQueue = new Queue[n];

            for (int i = 0; i < n; i++) {
                processorQueue[i] = new Queue();
            }

            Queue completeQueue = new Queue(); // queue for completed jobs

            while (completeQueue.length() != m) {

                // complete all processes finishing now
                int k = 0;
                while (k < n) {
                    if (processorQueue[k].length() > 0) {
                        Job currJob = (Job) processorQueue[k].peek();
                        if (currJob.getFinish() == time) {
                            int waitTime = currJob.getWaitTime();
                            if (waitTime > maxWait) {
                                maxWait = waitTime;
                            }
                            totalWait += waitTime;

                            completeQueue.enqueue(processorQueue[k].dequeue());

                            if (processorQueue[k].isEmpty() != true) {
                                Job frontJob = (Job) processorQueue[k].peek();
                                frontJob.computeFinishTime(time);
                            }

                            traceCheck = true;

                        }
                    }
                    k++;
                }

                // If there are any jobs arriving now, assign them to a processor
                if (jobQueue.isEmpty() != true) {

                    Job jobRecord = (Job) jobQueue.peek();
                    int jobArrival = jobRecord.getArrival();

                    while (jobArrival == time) {

                        traceCheck = true; // set flag to true for arrival event

                        Job newJob = (Job)jobQueue.dequeue();
                        int minQueueLength = processorQueue[0].length();
                        int minQueueLengthCount = 0;

                        // find the processor Queue with the least num of jobs
                        for (int i = 0; i < n; i++) {
                            if (processorQueue[i].length() < minQueueLength) {
                                minQueueLength = processorQueue[i].length();
                                minQueueLengthCount = i;
                            }
                        }

                        processorQueue[minQueueLengthCount].enqueue(newJob);

                        if (processorQueue[minQueueLengthCount].length() == 1) {
                            Job frontJob = (Job) processorQueue[minQueueLengthCount].peek();
                            frontJob.computeFinishTime(time);
                        }

                        if (jobQueue.isEmpty()) {
                            jobArrival = 0;
                        } else {
                            jobRecord = (Job) jobQueue.peek();
                            jobArrival = jobRecord.getArrival();
                        }

                    }

                }

                if ((traceCheck == true) || (time == 0)) {
                    trace.println("time=" + time);
                    trace.println("0: " + jobQueue.toString() + completeQueue.toString());
                    for (int i = 0; i < n; i++) {
                        trace.println(i + 1 + ": " + processorQueue[i].toString());
                    }
                    trace.println();
                }

                traceCheck = false;
                time++;

            }

            averageWait = ((double)totalWait / m);
            DecimalFormat df = new DecimalFormat("#0.00");

            // update report file
            if (n != 1) {
                report.println(n + " processors: totalWait=" + totalWait + ", maxWait=" + maxWait + ", averageWait=" + df.format(averageWait));
            } else {
                report.println("1 processor: totalWait=" + totalWait + ", maxWait=" + maxWait + ", averageWait=" + df.format(averageWait));
            }

            //put original jobs back in main queue after process is done
            for (int i = 1; i <= m; i++) {
                Job resetJob = (Job)tempQueue.dequeue();
                resetJob.resetFinishTime();
                jobQueue.enqueue(resetJob);
                tempQueue.enqueue(resetJob);
            }

        }

        // close input and output files
        inputFile.close();
        report.close();
        trace.close();
    }

}
