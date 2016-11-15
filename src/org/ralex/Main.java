package org.ralex;

import java.util.Random;

/**
 * Если убрать комментарии, то в консоле будут отображаться моменты, когда оба потока заняты и не успевают обработать заявку.
 */

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        MyThread first = new MyThread();
        MyThread second = new MyThread();
        Thread one = new Thread(first);
        Thread two = new Thread(second);
        int lost = 0;

        first.name = "=1=";
        second.name = "=2=";
/**
 * .isAlive() - проверяет состояние потока. Если поток занят - true.
 */
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= 100; i++) {
            Thread.sleep(random.nextInt(200) + 20);
            if (!one.isAlive() && !two.isAlive()) {
                first.client = i;
                one = new Thread(first);
                one.start();
            } else if (one.isAlive() && !two.isAlive()) {
                second.client = i;
                two = new Thread(second);
                two.start();
            } else if (!one.isAlive() && two.isAlive()) {
                first.client = i;
                one = new Thread(first);
                one.start();
            } else if (one.isAlive() && two.isAlive()) {
//                System.out.println("LOST! CLIENT: " + i);
                lost++;
            }
        }
        Thread.sleep(250);

        System.out.println("PROCESSED first: " + first.getProcessed() + ";\nPROCESSED second: " + second.getProcessed() + ";\nLOST: " + lost + ";\nMEAN TIME: " + first.getWork_time() / (100 - lost) + ";\nWAITING TIME: "
                + (System.currentTimeMillis() - startTime - first.getWork_time() / (100 - lost)) + ";\nALL TIME: "
                + (System.currentTimeMillis() - startTime));
        first.setWork_time(0);
        first.setProcessed(0);
        second.setProcessed(0);
    }
}
