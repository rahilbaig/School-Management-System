package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class SchoolManagementController {

	@Autowired
	StudentRepo studentRepo;
	@Autowired
	TeacherRepo teacherRepo;
	@Autowired
	ManagerRepo managerRepo;
	
	@RequestMapping("/")
	public String managerLogin() {
		return "managerlogin.jsp";
	}
	
	@RequestMapping("managerlogin")
	public String managerlogin(String data,String password) {
		Manager manager=managerRepo.validate(data, data);
		if(manager!=null) {
			if(manager.getEmail().equals(data) && manager.getPassword().equals(password)) {
				return "main.jsp"; 
			}
			else {
				return "error.jsp";
			}
		}
		else {
			return "error.jsp";
		}
	}
	
	@RequestMapping("addStudent")
	public String addStudent() {
		return "addstudent.jsp";
	}
	
	@RequestMapping("studentadd")
	public String studentadd(Student student) {
		studentRepo.save(student);
		return "main.jsp";
	}
	
	@RequestMapping("addTeacher")
	public String addTeacher() {
		return "addTeacher.jsp";
	}
	
	@RequestMapping("teacheradd")
	public String teacheradd(Teacher teacher) {
		teacherRepo.save(teacher);
		return "main.jsp";
	}
	
	
	
	@RequestMapping("teacherlogin")
	public String teacherlogin(String data,String password) {
		Teacher teacher=teacherRepo.validate(data, data);
		if(teacher!=null) {
			if(teacher.getEmail().equals(data) && teacher.getPassword().equals(password)) {
				return "teacher.jsp"; 
			}
			else {
				return "error.jsp";
			}
		}
		else {
			return "error.jsp";
		}
	}
	
	@RequestMapping("teacherpage")
	public String teacherPage() {
		return "teacherpage.jsp";
	}
	
	@RequestMapping("findStudent")
	public String findStudent() {
		return "findStudent.jsp";
	}
	
	@RequestMapping("studentdetails")
	public String getStudent(HttpSession s, String data,Integer id) {
		Student student=studentRepo.getByIdOrName(data, id);
		
		if(student!=null) {
			
			double fees=100000;
			
			if(fees==student.getFees()) {
				s.setAttribute("id", student.getId());
				s.setAttribute("name", student.getName());
				s.setAttribute("age", student.getAge());
				s.setAttribute("gender", student.getGender());
				s.setAttribute("fees", student.getFees());
				s.setAttribute("course", student.getCourse());
				s.setAttribute("balfees", 0);
				return "studentdetails.jsp";
			}
			else {
				fees=(fees-student.getFees());
				s.setAttribute("id", student.getId());
				s.setAttribute("name", student.getName());
				s.setAttribute("age", student.getAge());
				s.setAttribute("gender", student.getGender());
				s.setAttribute("fees", student.getFees());
				s.setAttribute("course", student.getCourse());
				s.setAttribute("balfees", fees);
				return "studentdetails.jsp";
			}
		}
		else {
			return "error.jsp";
		}
		
	}
	
	@RequestMapping("deletestd")
	public String deletestd() {
		return "deletestd.jsp";
	}
	
	@RequestMapping("deleteStudent")
	public String deleteStudent(HttpSession s, String name,Integer id) {
		Student student=studentRepo.deletestu(name, id);
		if(student!=null) {
			s.setAttribute("sname", student.getName());
			studentRepo.delete(student);
			return "main.jsp";
		}
		else {
			return "error.jsp";
		}
		
	}
	
	@RequestMapping("getTeacher")
	public String getTeacher() {
		return "getTeacher.jsp";
	}
	
	@RequestMapping("techerDetails")
	public String techerDetails(HttpSession s, Integer id) {
		Teacher teacher=teacherRepo.findById(id).orElse(null);
		if(teacher!=null) {
			s.setAttribute("id", teacher.getId());
			s.setAttribute("name", teacher.getUsername());
			s.setAttribute("email", teacher.getEmail());
			s.setAttribute("password", teacher.getPassword());
			return "techerDetails.jsp";
		}
		else {
			return "error.jsp";
		}
	}
	
	@RequestMapping("deletetech")
	public String deletetech() {
		return "deletetech.jsp";
	}
	
	@RequestMapping("deleteTeacher")
	public String deleteTeacher(HttpSession s,Integer id) {
		Teacher teacher=teacherRepo.findById(id).orElse(null);
		if(teacher!=null) {
			teacherRepo.delete(teacher);
			return "main.jsp";
		}
		else {
			return "error.jsp";
		}
		
	}
	
	
	
}