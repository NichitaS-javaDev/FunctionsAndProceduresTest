/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author ADMIN
 */
public class Client implements Runnable {
    private Socket client;
    private BufferedReader reader;
    private BufferedWriter writer;
    private JFrame1 pupil;

    Client() throws IOException {
        //createClient();
    }

    public void createClient() throws IOException {
        String ip = new JFrame1().getIp();
        client = new Socket(ip,8000);

        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
    }

        public void sendResult () throws IOException {

            pupil = new JFrame1();
            int points = new JFrame7().getPoints();
            String name = pupil.getName();
            String cls = pupil.getCls();
            writer.write(name + " " + cls + " " + points);
            writer.newLine();
            writer.flush();

            client.close();
        }

    @Override
    public void run() {
        try {
            createClient();
            sendResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
