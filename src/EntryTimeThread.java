public class EntryTimeThread extends Thread {
    private long startTime;
    private long elapsedTime;

    public EntryTimeThread() {
        this.startTime = System.currentTimeMillis(); // 프로그램이 시작된 절대 시간
        this.elapsedTime = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                elapsedTime = System.currentTimeMillis() - startTime; // 현재시간 - 프로그램이 시작된 절대시간
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public String getElapsedTime() {
        long hours = (elapsedTime / (1000 * 60 * 60)) % 24;
        long minutes = (elapsedTime / (1000 * 60)) % 60;
        return "현재 편의점에 입장하신지 " + hours + "시간 " + minutes + "분이 지났습니다.";
    }
}