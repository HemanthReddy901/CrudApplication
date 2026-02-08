package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dao.StudentDAO;
import model.Student;

@Controller
public class StudentController
{
  private final StudentDAO studentDAO;
  public StudentController(StudentDAO studentDAO)
  {
	  this.studentDAO=studentDAO;
  }
  
  @GetMapping("/")
  public String home(Model model)
  {
	  model.addAttribute("student",new Student());
	  model.addAttribute("students", studentDAO.getAllStudents());
	  return "home";
  }
  
  @PostMapping("/save")
  public String save(@ModelAttribute Student s)
  {
	  studentDAO.save(s);
	  return "redirect:/";
  }
  
  @PostMapping("/update")
  public String update(@ModelAttribute Student s)
  {
	  studentDAO.updateStudent(s);
	  return "redirect:/";
  }
  
  @GetMapping("/delete/{stuId}")
  public String delete(@PathVariable int stuId)
  {
	  studentDAO.deleteStudentById(stuId);
	  return "redirect:/";
  }
  @GetMapping("/edit/{stuId}")
  public String edit(Model model, @PathVariable int stuId)
  {
	 model.addAttribute("student",studentDAO.getStudentById(stuId));
	 return "edit";
  }
  
}
