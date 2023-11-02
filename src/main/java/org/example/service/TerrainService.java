package org.example.service;


import io.grpc.stub.StreamObserver;
import org.example.grpc_stubs.GrpcServer;
import org.example.grpc_stubs.ServertestGrpc;

import java.util.Timer;
import java.util.TimerTask;

public class TerrainService extends ServertestGrpc.ServertestImplBase {


    @Override
    public void terrainCalcul(GrpcServer.TerrainRequest request, StreamObserver<GrpcServer.TerrainResponse> responseObserver) {
        GrpcServer.TerrainResponse response= GrpcServer.TerrainResponse.newBuilder()
                .setLabel(request.getLabel())
                .setPrix(request.getSurface() * request.getTaux())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("terrainCalcul Completed");
    }

    @Override
    public StreamObserver<GrpcServer.TerrainRequest> clientStreamTerrainCalcul(StreamObserver<GrpcServer.TerrainResponse> responseObserver) {
        return new StreamObserver<GrpcServer.TerrainRequest>() {
            double total = 0;
            String lable;

            @Override
            public void onNext(GrpcServer.TerrainRequest value) {
                double res = value.getTaux() * value.getSurface();
                total += res;
                lable=value.getLabel();
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("clientStreamTerrainCalcul onCompleted = " + total);
                GrpcServer.TerrainResponse response = GrpcServer.TerrainResponse.newBuilder()
                        .setLabel(lable)
                        .setPrix(total)
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }



    @Override
    public StreamObserver<GrpcServer.TerrainRequest> fullStreamTerrainCalcul(StreamObserver<GrpcServer.TerrainResponse> responseObserver) {
        return new StreamObserver<GrpcServer.TerrainRequest>() {
            @Override
            public void onNext(GrpcServer.TerrainRequest request) {
                GrpcServer.TerrainResponse response= GrpcServer.TerrainResponse.newBuilder()
                        .setLabel(request.getLabel())
                        .setPrix(request.getTaux()*request.getSurface())
                        .build();
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("fullStreamTerrainCalcul Completed");
                responseObserver.onCompleted();
            }
        };
    }
}
