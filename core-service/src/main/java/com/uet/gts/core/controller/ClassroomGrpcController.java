package com.uet.gts.core.controller;

import com.google.protobuf.Empty;
import com.uet.gts.common.proto.*;
import com.uet.gts.core.usecase.ClassroomUseCase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class ClassroomGrpcController extends ClassroomServiceGrpc.ClassroomServiceImplBase {

    @Autowired
    private ClassroomUseCase classroomUseCase;

    @Override
    public void getList(Empty request, final StreamObserver<ClassroomProtobuf> responseObserver) {
        classroomUseCase.findAllAsProto()
                        .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }
}
