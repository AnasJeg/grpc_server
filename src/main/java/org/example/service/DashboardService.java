package org.example.service;

import io.grpc.stub.StreamObserver;
import org.example.grpc_stubs.GrpcServerGrpc;
import org.example.grpc_stubs.GrpcServerOuterClass;

public class DashboardService extends GrpcServerGrpc.GrpcServerImplBase {

    @Override
    public void dashbordMachines(GrpcServerOuterClass.DashbordRequest request, StreamObserver<GrpcServerOuterClass.DashbordResponse> responseObserver) {
        String ref=request.getRef();
        String marque=request.getMarque();
        double prix=request.getPrix();
        GrpcServerOuterClass.DashbordResponse response=GrpcServerOuterClass.DashbordResponse.newBuilder()
                .setRef(ref)
                .setMarque(marque)
                .setPrix(prix)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
