package com.anshu.httpserver.internal.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {
    private final ServerSocket serverSocket;
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final Handler handler;

    public Server(ServerSocket serverSocket, Handler handler) {
        this.serverSocket = serverSocket;
        this.handler = handler;
    }

    public static Server serve(int port, Handler handler) throws Exception {
        ServerSocket serverSock = new ServerSocket(port);
        Server server = new Server(serverSock, handler);

        Thread listenerThread = new Thread(server::listen);
        listenerThread.start();

        return server;
    }

    public void listen() {
        while (!closed.get()) {
            try {
                Socket conn = serverSocket.accept();
                System.out.println("Accepted connection from " + conn.getInetAddress().getHostAddress());

                // handle connection in a new Thread
                new Thread(() -> {
                    try {
                        InputStream in = conn.getInputStream();
                        OutputStream out = conn.getOutputStream();


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            } catch (Exception e) {
                if (!closed.get()) {
                    System.err.println("Error accepting client connection: " + e.getMessage());
                }
            }
        }
    }

    public void close() throws IOException {
        closed.set(true);
        serverSocket.close();
    }
}
