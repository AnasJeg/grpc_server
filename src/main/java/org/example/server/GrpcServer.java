package org.example.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.service.DashboardService;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) {
        Server server= ServerBuilder.forPort(5050).addService(new DashboardService()).build();
        try {
            server.start();
            System.out.println("grpc server runing");
            server.awaitTermination();  // thread dyal server et quite app
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
