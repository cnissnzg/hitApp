package com.example.controller;

import com.example.entity.*;
import com.example.service.*;
import com.example.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/exams")
public class ExamController {
  @Autowired
  private ExamService examService;
  @Autowired
  private ProblemService problemService;

  @RequestMapping("getOne")
  public Exam getOneExam(int eid){
    return examService.getOneExam(eid);
  }

  @RequestMapping("getDetail")
  public List<Problem> getProblemDetail(int eid){
    Exam exam = examService.getOneExam(eid);
    String t = exam.getProblemList();
    List<Integer> plist = ListAdaptor.toList(t);
    List<Problem> res = new ArrayList<>();
    for(Integer tmp:plist){
      res.add(problemService.getOneProblem(tmp));
    }
    return res;
  }

  @RequestMapping("getAll")
  public List<Exam> getAllExam(){
    return examService.getAllExam();
  }

  @RequestMapping("addOne")
  public int addOneExam(@RequestBody Exam exam){
    return examService.addExam(exam);
  }

  @RequestMapping("updOne")
  public int updateOneExam(@RequestParam("eid") int eid,@RequestBody Exam exam){
    return examService.updateExam(eid,exam);
  }

  @RequestMapping("delOne")
  public int delOneExam(int eid){
    return examService.delExam(eid);
  }
}
