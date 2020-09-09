package com.amigoscode.demo.advertisement;

import com.amigoscode.demo.student.Student;
import com.amigoscode.demo.student.StudentCourse;
import com.amigoscode.demo.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/landingpages")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping
    public List<LandingPage> getAllLandingPages() {
        return advertisementService.getAllLandingPages();
    }

    @GetMapping(value = "/get-action-count")
    public int getActionCount(@RequestParam("mac") String mac
            ,@RequestParam("adId") String adId){
        return advertisementService.getActionCount(mac, adId);
    }

    @RequestMapping(value = "/land", method = RequestMethod.GET)
    public RedirectView land(@RequestParam("landingPageId") String landingPageId
            ,@RequestParam("mac") String mac
            ,@RequestParam("adId") String adId
    ) {

        advertisementService.recordLanding(adId, landingPageId, mac);
        String targetUrl = advertisementService.getUrlByLandingPageId(landingPageId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(targetUrl);
        return redirectView;
    }

//    @GetMapping(path = "{studentId}/courses")
//    public List<StudentCourse> getAllCoursesForStudent(
//            @PathVariable("studentId") UUID studentId) {
//        return studentService.getAllCoursesForStudent(studentId);
//    }
//
//    @PostMapping
//    public void addNewStudent(@RequestBody @Valid Student student) {
//        studentService.addNewStudent(student);
//    }
//
//    @PutMapping(path = "{studentId}")
//    public void updateStudent(@PathVariable("studentId") UUID studentId,
//                              @RequestBody Student student) {
//        studentService.updateStudent(studentId, student);
//    }
//
//    @DeleteMapping("{studentId}")
//    public void deleteStudent(@PathVariable("studentId") UUID studentId) {
//        studentService.deleteStudent(studentId);
//    }

}