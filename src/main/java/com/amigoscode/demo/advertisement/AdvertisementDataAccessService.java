package com.amigoscode.demo.advertisement;

import com.amigoscode.demo.student.Student;
import com.amigoscode.demo.student.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AdvertisementDataAccessService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdvertisementDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<LandingPage> selectAllLandingPage() {
        String sql = "" +
                "SELECT " +
                " landing_page_id, " +
                " name, " +
                " url, " +
                " description," +
                " small_demo_id," +
                " large_demo_id " +
                "FROM LandingPage";

        return jdbcTemplate.query(sql, mapLandingPageFromDb());
    }

    List<SiteDetail> getAllSiteDetails(){
        String sql = "" +
                "SELECT s.site_id , s.site_name, count(distinct d.mac) as device_count, count(distinct l.landing_record_id) as scan_count " +
                " from site s full join device d on s.site_id = d.site_id " +
                " full join landingrecord l on d.mac = l.device_id " +
                " group by s.site_id ";

        return jdbcTemplate.query(sql, mapSiteDetailFromDb());

    }


    int insertLandingPage(String landingPageId, LandingPage landingPage) {
        String sql = "" +
                "INSERT INTO student (" +
                " landing_page_id, " +
                " name, " +
                " url, " +
                " description) " +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                landingPageId,
                landingPage.getName(),
                landingPage.getUrl(),
                landingPage.getDescription()
        );
    }

    int getActionCount(String mac, String adId){
        String sql = "" +
                " SELECT COUNT (landing_record_id)" +
                " from landingRecord" +
                " where" +
                " device_id = ?" +
                " and " +
                " landing_page_id = ?";
        return jdbcTemplate.queryForObject(
                sql, new Object[] { mac, adId}, Integer.class);
    }

    String getUrlByLandingPageId(String landingPageId){
        String sql = "" +
                " SELECT url" +
                " from landingPage" +
                " where" +
                " landing_page_id = ?";
        return jdbcTemplate.queryForObject(
                sql, new Object[] { landingPageId}, String.class);
    }

    void recordLanding(String adId, String mac){
        String sql = "" +
                " SELECT *" +
                " from recordlanding(?, ?)" ;
        jdbcTemplate.queryForObject(
                sql, new Object[] { adId, mac}, Boolean.class);
    }

    AdDetail getAdDetail(String adId){
        String getScanCountSql = "" +
                " SELECT count(landing_time)" +
                " FROM landingRecord" +
                " WHERE landing_page_id = ?";
        int scanCount = jdbcTemplate.queryForObject(
                getScanCountSql, new Object[] { adId}, Integer.class);

        String getScanCount24HSql = "" +
                " SELECT count(landing_time)" +
                " FROM landingRecord" +
                " WHERE landing_page_id = ?" +
                " AND landing_time >= NOW() - interval '1 day'";

        int scanCount24H = jdbcTemplate.queryForObject(
                getScanCount24HSql, new Object[] { adId}, Integer.class);

        String getLastScanTime = "" +
                " SELECT max(landing_time)" +
                " FROM landingrecord" +
                " WHERE landing_page_id = ?";

        java.sql.Timestamp lastScanTime = jdbcTemplate.queryForObject(
                getLastScanTime, new Object[] {adId}, java.sql.Timestamp.class);

        if (lastScanTime == null){
            return new AdDetail(adId, adId, scanCount, "N/A", scanCount24H);
        }

        Date date = new Date();
        date.setTime(lastScanTime.getTime());
        String lastScanTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        return new AdDetail(adId, adId, scanCount, lastScanTimeString, scanCount24H);


    }

    LabelDetail getLabelDetail(String mac){
        String sql = "" +
                "SELECT " +
                " mac, " +
                " name, " +
                " device.site_id, " +
                " site_name" +
                " FROM device join site" +
                " on device.site_id = site.site_id"+
                " where mac = ?";

        return (LabelDetail) jdbcTemplate.queryForObject(sql, new Object[] { mac }, mapLabelDetailFromDb());
    }

    Boolean updateDeviceName(String mac, String newName){
        try{
            String sql = "" +
                    " update device" +
                    " set name = ?" +
                    " where mac = ?";
            jdbcTemplate.update(sql, new Object[] {newName, mac});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    Boolean createNewSite(String siteName){
        try{
            String sql = "" +
                    " insert into site values " +
                    " (LEFT(MD5(RANDOM()::text), 8), ?)" ;
            jdbcTemplate.update(sql, new Object[] {siteName});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    Boolean deleteSite(String siteId){
        try{
            String sql1 = "" +
                    " update device " +
                    " set site_id = 'NULL' " +
                    " where site_id = ? ";

            jdbcTemplate.update(sql1, new Object[] {siteId});

            String sql2 = "" +
                    " delete from site " +
                    " where site_id = ?" ;
            jdbcTemplate.update(sql2, new Object[] {siteId});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    Boolean deviceChangeSite(String mac, String siteId){
        try{
            String sql1 = "" +
                    " update device " +
                    " set site_id = ? " +
                    " where mac = ? ";

            jdbcTemplate.update(sql1, new Object[] {siteId, mac});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    Boolean updateSiteName(String siteId, String name){
        try{
            String sql1 = "" +
                    " update site " +
                    " set site_name = ? " +
                    " where site_id = ? ";

            jdbcTemplate.update(sql1, new Object[] {name, siteId});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    Boolean isDeviceInDB(String mac){
        String sql = "" +
                " SELECT COUNT (mac)" +
                " from device" +
                " where" +
                " mac = ?" ;
        int count =  jdbcTemplate.queryForObject(
                sql, new Object[] { mac}, Integer.class);

        return count != 0;
    }

    Boolean recordNewDevice(String mac){
        try{
            String sql = "" +
                    " insert into device values " +
                    " (?, '', 'NULL')" ;
            jdbcTemplate.update(sql, new Object[] {mac});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

//    @SuppressWarnings("ConstantConditions")
//    boolean isEmailTaken(String email) {
//        String sql = "" +
//                "SELECT EXISTS ( " +
//                " SELECT 1 " +
//                " FROM student " +
//                " WHERE email = ?" +
//                ")";
//        return jdbcTemplate.queryForObject(
//                sql,
//                new Object[]{email},
//                (resultSet, i) -> resultSet.getBoolean(1)
//        );
//    }

//    List<StudentCourse> selectAllStudentCourses(UUID studentId) {
//        String sql = "" +
//                "SELECT " +
//                " student.student_id, " +
//                " course.course_id, " +
//                " course.name, " +
//                " course.description," +
//                " course.department," +
//                " course.teacher_name," +
//                " student_course.start_date, " +
//                " student_course.end_date, " +
//                " student_course.grade " +
//                "FROM student " +
//                "JOIN student_course USING (student_id) " +
//                "JOIN course         USING (course_id) " +
//                "WHERE student.student_id = ?";
//        return jdbcTemplate.query(
//                sql,
//                new Object[]{studentId},
//                mapStudentCourseFromDb()
//        );
//    }
//
//    private RowMapper<StudentCourse> mapStudentCourseFromDb() {
//        return (resultSet, i) ->
//                new StudentCourse(
//                        UUID.fromString(resultSet.getString("student_id")),
//                        UUID.fromString(resultSet.getString("course_id")),
//                        resultSet.getString("name"),
//                        resultSet.getString("description"),
//                        resultSet.getString("department"),
//                        resultSet.getString("teacher_name"),
//                        resultSet.getDate("start_date").toLocalDate(),
//                        resultSet.getDate("end_date").toLocalDate(),
//                        Optional.ofNullable(resultSet.getString("grade"))
//                                .map(Integer::parseInt)
//                                .orElse(null)
//                );
//    }

    private RowMapper<LandingPage> mapLandingPageFromDb() {
        return (resultSet, i) -> {
            String landingPageId = resultSet.getString("landing_page_id");

            String name = resultSet.getString("name");
            String url = resultSet.getString("url");
            String description = resultSet.getString("description");
            String smallDemoId = resultSet.getString("small_demo_id");
            String largeDemoId = resultSet.getString("large_demo_id");

            return new LandingPage(
                    landingPageId,
                    name,
                    url,
                    description,
                    largeDemoId,
                    smallDemoId
            );
        };
    }

    private RowMapper<LabelDetail> mapLabelDetailFromDb() {
        return (resultSet, i) -> {
            String mac = resultSet.getString("mac");
            String name = resultSet.getString("name");
            String site_id = resultSet.getString("site_id");
            String site_name = resultSet.getString("site_name");

            return new LabelDetail(
                    mac,
                    name,
                    site_id,
                    site_name
            );
        };
    }

    private RowMapper<SiteDetail> mapSiteDetailFromDb() {
        return (resultSet, i) -> {
            String site_id = resultSet.getString("site_id");
            String site_name = resultSet.getString("site_name");
            int device_count = resultSet.getInt("device_count");
            int scan_count = resultSet.getInt("scan_count");

            return new SiteDetail(
                    site_id,
                    site_name,
                    device_count,
                    scan_count
            );
        };
    }

//    int updateEmail(UUID studentId, String email) {
//        String sql = "" +
//                "UPDATE student " +
//                "SET email = ? " +
//                "WHERE student_id = ?";
//        return jdbcTemplate.update(sql, email, studentId);
//    }
//
//    int updateFirstName(UUID studentId, String firstName) {
//        String sql = "" +
//                "UPDATE student " +
//                "SET first_name = ? " +
//                "WHERE student_id = ?";
//        return jdbcTemplate.update(sql, firstName, studentId);
//    }
//
//    int updateLastName(UUID studentId, String lastName) {
//        String sql = "" +
//                "UPDATE student " +
//                "SET last_name = ? " +
//                "WHERE student_id = ?";
//        return jdbcTemplate.update(sql, lastName, studentId);
//    }
//
//    @SuppressWarnings("ConstantConditions")
//    boolean selectExistsEmail(UUID studentId, String email) {
//        String sql = "" +
//                "SELECT EXISTS ( " +
//                "   SELECT 1 " +
//                "   FROM student " +
//                "   WHERE student_id <> ? " +
//                "    AND email = ? " +
//                ")";
//        return jdbcTemplate.queryForObject(
//                sql,
//                new Object[]{studentId, email},
//                (resultSet, columnIndex) -> resultSet.getBoolean(1)
//        );
//    }
//
//    int deleteStudentById(UUID studentId) {
//        String sql = "" +
//                "DELETE FROM student " +
//                "WHERE student_id = ?";
//        return jdbcTemplate.update(sql, studentId);
//    }
}
