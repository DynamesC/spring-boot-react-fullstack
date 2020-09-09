package com.amigoscode.demo.advertisement;

import com.amigoscode.demo.student.Student;
import com.amigoscode.demo.student.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
                " description " +
                "FROM LandingPage";

        return jdbcTemplate.query(sql, mapLandingPageFromDb());
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
                " ad_id = ?";
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

    void recordLanding(String adId, String landingPageId, String mac){
        String sql = "" +
                " SELECT *" +
                " from recordlanding(?, ?, ?)" ;
        jdbcTemplate.queryForObject(
                sql, new Object[] { adId, landingPageId, mac}, Boolean.class);
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

            return new LandingPage(
                    landingPageId,
                    name,
                    url,
                    description
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
