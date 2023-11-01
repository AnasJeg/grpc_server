package org.example.service;


import io.grpc.stub.StreamObserver;
import org.example.grpc_stubs.GrpcServer;
import org.example.grpc_stubs.ServertestGrpc;

public class TerrainService extends ServertestGrpc.ServertestImplBase {


    @Override
    public void terrainCalcul(GrpcServer.TerrainRequest request, StreamObserver<GrpcServer.TerrainResponse> responseObserver) {
        double surf=request.getSurface();
        double taux=request.getTaux();
        GrpcServer.TerrainResponse response= GrpcServer.TerrainResponse.newBuilder()
                .setPrix(surf * taux)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
