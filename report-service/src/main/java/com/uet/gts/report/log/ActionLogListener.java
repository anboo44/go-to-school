package com.uet.gts.report.log;

import com.uet.gts.report.model.ClassroomEvent;
import com.uet.gts.report.model.EventMessage;
import com.uet.gts.report.model.StudentEvent;
import com.uet.gts.report.model.TeacherEvent;
import com.uet.gts.report.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ActionLogListener implements ApplicationListener<EventMessage> {

    @Autowired
    private LogRepository logRepository;

    @Async
    @Override
    public void onApplicationEvent(EventMessage event) {
        var data = event.getData();

        System.out.println("Send action log: " + event.getData());
        if (data instanceof StudentEvent) {
            StudentEvent studentEvent = (StudentEvent) data;
            logRepository.logStudentReport(studentEvent.getData());
        }

        if (data instanceof TeacherEvent) {
            TeacherEvent teacherEvent = (TeacherEvent) data;
            logRepository.logTeacherReport(teacherEvent.getData());
        }

        if (data instanceof ClassroomEvent) {
            ClassroomEvent classroomEvent = (ClassroomEvent) data;
            logRepository.logClassroomReport(classroomEvent.getData());
        }
    }
}
