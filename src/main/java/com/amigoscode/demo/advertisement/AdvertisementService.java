package com.amigoscode.demo.advertisement;

import com.amigoscode.demo.EmailValidator;
import com.amigoscode.demo.exception.ApiRequestException;
import com.amigoscode.demo.student.Student;
import com.amigoscode.demo.student.StudentCourse;
import com.amigoscode.demo.student.StudentDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdvertisementService {

    private final AdvertisementDataAccessService advertisementDataAccessService;

    @Autowired
    public AdvertisementService(AdvertisementDataAccessService advertisementDataAccessService) {
        this.advertisementDataAccessService = advertisementDataAccessService;
    }

    List<LandingPage> getAllLandingPages() {
        return advertisementDataAccessService.selectAllLandingPage();
    }

    int getActionCount(String mac, String adId){
        return advertisementDataAccessService.getActionCount(mac, adId);
    }

    String getUrlByLandingPageId(String landingPageId){
        return advertisementDataAccessService.getUrlByLandingPageId(landingPageId);
    }

    void recordLanding(String adId, String landingPageId, String mac){
        advertisementDataAccessService.recordLanding(adId, landingPageId, mac);
    }

//    void addNewStudent(Student student) {
//        addNewStudent(null, student);
//    }

//    void addNewStudent(UUID studentId, Student student) {
//        UUID newStudentId = Optional.ofNullable(studentId)
//                .orElse(UUID.randomUUID());
//
//        if (!emailValidator.test(student.getEmail())) {
//            throw new ApiRequestException(student.getEmail() + " is not valid");
//        }
//
//        if (studentDataAccessService.isEmailTaken(student.getEmail())) {
//            throw new ApiRequestException(student.getEmail() + " is taken");
//        }
//
//        studentDataAccessService.insertStudent(newStudentId, student);
//    }
//
//    List<StudentCourse> getAllCoursesForStudent(UUID studentId) {
//        return studentDataAccessService.selectAllStudentCourses(studentId);
//    }
//
//    public void updateStudent(UUID studentId, Student student) {
//        Optional.ofNullable(student.getEmail())
//                .ifPresent(email -> {
//                    boolean taken = studentDataAccessService.selectExistsEmail(studentId, email);
//                    if (!taken) {
//                        studentDataAccessService.updateEmail(studentId, email);
//                    } else {
//                        throw new IllegalStateException("Email already in use: " + student.getEmail());
//                    }
//                });
//
//        Optional.ofNullable(student.getFirstName())
//                .filter(fistName -> !StringUtils.isEmpty(fistName))
//                .map(StringUtils::capitalize)
//                .ifPresent(firstName -> studentDataAccessService.updateFirstName(studentId, firstName));
//
//        Optional.ofNullable(student.getLastName())
//                .filter(lastName -> !StringUtils.isEmpty(lastName))
//                .map(StringUtils::capitalize)
//                .ifPresent(lastName -> studentDataAccessService.updateLastName(studentId, lastName));
//    }
//
//    void deleteStudent(UUID studentId) {
//        studentDataAccessService.deleteStudentById(studentId);
//    }
}
