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
@RequestMapping("lp")
@CrossOrigin(origins = "*")
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

    @GetMapping(value = "/get-ad-detail")
    @ResponseBody
    public AdDetail getAdDetail(@RequestParam("adId") String adId) {
        return advertisementService.getAdDetail(adId);
    }

    @GetMapping(value = "/get-ad-list")
    @ResponseBody
    public List<LandingPage> getAdList() {
        return advertisementService.getAllLandingPages();
    }

    @GetMapping(value = "/get-site-list")
    @ResponseBody
    public List<SiteDetail> getSiteList() {
        return advertisementService.getAllSiteDetails();
    }

    @GetMapping(value = "/get-label-detail")
    @ResponseBody
    public LabelDetail getLabelDetail(@RequestParam("mac") String mac) {return advertisementService.getLabelDetail(mac);}

    @GetMapping(value = "/update-device-name")
    @ResponseBody
    public Boolean updateDeviceName(@RequestParam("mac") String mac,
                                    @RequestParam("newName") String newName){
        return advertisementService.updateDeviceName(mac, newName);
    }

    @GetMapping(value = "/update-site-name")
    @ResponseBody
    public Boolean updateSiteName(@RequestParam("site_id") String siteId,
                                    @RequestParam("name") String name){
        return advertisementService.updateSiteName(siteId, name);
    }

    @GetMapping(value = "/create-new-site")
    @ResponseBody
    public Boolean createNewSite(@RequestParam("site_name") String siteName){
        return advertisementService.createNewSite(siteName);
    }

    @GetMapping(value = "/delete-site")
    @ResponseBody
    public Boolean deleteSite(@RequestParam("site_id") String siteId){
        return advertisementService.deleteSite(siteId);
    }

    @GetMapping(value = "/device-change-site")
    @ResponseBody
    public Boolean deviceChangeSite(@RequestParam("site_id") String siteId,
                                    @RequestParam("mac") String mac){
        return advertisementService.deviceChangeSite(mac, siteId);
    }

    @RequestMapping(value = "/ld", method = RequestMethod.GET)
    public RedirectView land(@RequestParam("mac") String mac
            ,@RequestParam("adId") String adId
    ) {
        advertisementService.recordLanding(adId, mac);
        String targetUrl = advertisementService.getUrlByLandingPageId(adId);
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
