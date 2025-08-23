package edu.eci.arsw.threads;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

public class BlackListThread extends Thread {

    private static final int BLACK_LIST_ALARM_COUNT = 5;
    private static final Logger LOG = Logger.getLogger(BlackListThread.class.getName());
    private List<Integer> blackListOcurrences = new LinkedList<>();

    private int ocurrencesCount;
    private String ipaddress;
    private int start;
    private int end;

    public BlackListThread(int start, int end, String ipaddress) {
        this.ipaddress = ipaddress;
        this.ocurrencesCount = 0;
        this.start = start;
        this.end = end;
    }

    public int getOcurrencesCount() {
        return ocurrencesCount;
    }

    public void incrementOcurrencesCount() {
        this.ocurrencesCount++;
    }

    public List<Integer> getBlackListOcurrences() {
        return blackListOcurrences;
    }

    @Override
    public void run() {
        System.out.println("\nBlackListThread is running...");
        checkHost(start, end, ipaddress);
    }

    public void checkHost(int start, int end, String ipaddress) {

        HostBlacklistsDataSourceFacade skds = HostBlacklistsDataSourceFacade.getInstance();
        int checkedListsCount = 0;

        for (int i = start; i <= end && ocurrencesCount < BLACK_LIST_ALARM_COUNT; i++) {
            checkedListsCount++;

            if (skds.isInBlackListServer(i, ipaddress)) {

                blackListOcurrences.add(i);

                incrementOcurrencesCount();
            }
        }

        LOG.log(Level.INFO, "Thread start: {0} - end: {1}, Checked Black Lists:{2} of {3}",
                new Object[] { start, end, checkedListsCount, end + 1 - start });

    }

}
