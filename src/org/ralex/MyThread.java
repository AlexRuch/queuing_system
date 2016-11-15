package org.ralex;

import java.util.Random;

/**
 * Если убрать комментарии, то в консоле будут отображаться моменты, когда поток начинает и заканчивает работать.
 */

class MyThread implements Runnable {
    private Random random = new Random();
    private int processed = 0;
    public int client;
    String name;
    private int work_time = 0;

    @Override
    public void run() {
        try {
//            System.out.println(name + "START! CLIENT: " + client);

/**
 time - Время обслуживания заявки. random.nextInt(2000) - генерирует случайное число от 0 до 2000, random.nextInt(2000)+ 100 - генерирует случайное число от 100 до 2100.
 */
            int time = random.nextInt(200) + 20;
            work_time = work_time + time;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a();
    }

    public void a() {
//        System.out.println(name + " FINISH! CLIENT: " + client);

/**
 processed - количество задач обработанных потоком.
 */
        processed++;
    }


    /*
    --------------------------------GETTERS AND SETTERS------------------------------------------

     */
    public int getProcessed() {
        return processed;
    }

    public void setProcessed(int processed) {
        this.processed = processed;
    }

    public int getWork_time() {
        return work_time;
    }

    public void setWork_time(int work_time) {
        this.work_time = work_time;
    }
}
