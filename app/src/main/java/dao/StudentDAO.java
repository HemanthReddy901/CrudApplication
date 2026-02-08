package dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import model.Student;

@Repository
public class StudentDAO 
{
  private final JdbcTemplate jdbcTemplate;
  public StudentDAO(JdbcTemplate jdbcTemplate)
  {
	  this.jdbcTemplate=jdbcTemplate;
  }
  
  public void save(Student stu)
  {
	  String sql="INSERT INTO students_info(stu_name,stu_dept,stu_marks) VALUES(?,?,?)";
	  jdbcTemplate.update(sql, stu.getStuName(),stu.getStuDept(),stu.getStuMarks());
	  
  }
  
  public Student getStudentById(int stuId)
  {
	  String sql="SELECT * FROM students_info WHERE stu_id=?";
	  
	  RowMapper<Student> rowMapper=(rs, rowcount) -> {
		  Student stu=new Student();
		  stu.setStuId(rs.getInt("stu_id"));
		  stu.setStuName(rs.getString("stu_name"));
		  stu.setStuDept(rs.getString("stu_dept"));
		  stu.setStuMarks(rs.getDouble("stu_marks"));
		   return stu;
	  };
	   return jdbcTemplate.queryForObject(sql, rowMapper,stuId);  
  }
  
  public List<Student> getAllStudents()
  {
	  String sql="SELECT * FROM students_info";
	  RowMapper<Student> rowMapper=(rs,rowcount)->{
		  Student stu=new Student();
		  stu.setStuId(rs.getInt("stu_id"));
		  stu.setStuName(rs.getString("stu_name"));
		  stu.setStuDept(rs.getString("stu_dept"));
		  stu.setStuMarks(rs.getDouble("stu_marks"));
		  return stu;
	  };
	  return jdbcTemplate.query(sql, rowMapper);
	  }
  
  public void updateStudent(Student s)
  {
	  String sql="UPDATE students_info set stu_name=?,stu_dept=?,stu_marks=? WHERE stu_id=?";
	  jdbcTemplate.update(sql, s.getStuName(),s.getStuDept(),s.getStuMarks(),s.getStuId());
  }
  
  public void deleteStudentById(int stuId)
  {
	  String sql="DELETE FROM students_info WHERE stu_id=?";
	  jdbcTemplate.update(sql,stuId);
  }
  
}
