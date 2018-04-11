package com.mubasher.timesheet.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mubasher.timesheet.controller.dto.WorkLog;
import com.mubasher.timesheet.model.User;
import com.mubasher.timesheet.model.Work;
import com.mubasher.timesheet.model.WorkType;
import com.mubasher.timesheet.service.UserService;
import com.mubasher.timesheet.service.WorkLogService;
import com.mubasher.timesheet.utils.BeanMapper;


@Controller
public class LoginPageController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkLogService workLogService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			User u = userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("userDetail", BeanMapper.map(u));
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		
		model.addAttribute("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
		if(!model.containsAttribute("workLog")) {
			WorkLog wl = new WorkLog();
			wl.setCurrentDate(new Date());
			model.addAttribute("workLog", wl);
		} else if (!model.containsAttribute("update")){
			model.addAttribute("update",true);
		}
		
		List<Work> works = workLogService.findWorksByUserIdAndCurrentWeek(user.getId());
		model.addAttribute("workLogs",BeanMapper.mapWork(works));
		
		List<WorkType> workTypes = workLogService.findWorkTypes();
		model.addAttribute("workTypes", workTypes);
		
		return "/home";
	}
	
	@RequestMapping(value="/work_log", method = RequestMethod.POST)
	public ModelAndView  workLog(@Valid WorkLog workLog, BindingResult bindingResult, RedirectAttributes attr){
		if (!bindingResult.hasErrors()) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			Work work = BeanMapper.map(workLog);
			work.setUser(user);
			workLogService.logWork(work);
			attr.addFlashAttribute("update", false);
		} else {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.workLog", bindingResult);
			attr.addFlashAttribute("workLog", workLog);
		}
		
		return new ModelAndView("redirect:/home");
	}
	
	@RequestMapping(value="/work_log", method = RequestMethod.PUT)
	public ModelAndView  updateWorkLog(@Valid WorkLog workLog, BindingResult bindingResult, RedirectAttributes attr){
		if (!bindingResult.hasErrors()) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			Work work = BeanMapper.map(workLog);
			work.setUser(user);
			workLogService.logWork(work);
		} else {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.workLog", bindingResult);
			attr.addFlashAttribute("workLog", workLog);
		}
		
		return new ModelAndView("redirect:/home");
	}
	
	@RequestMapping(value="/delete_work_log", method = RequestMethod.POST)
	public ModelAndView  deleteWorkLog(@RequestParam("id") Integer id, RedirectAttributes attr) throws Exception{
		boolean deleted = workLogService.deleteWork(workLogService.findWork(id));
		if(!deleted) {
			throw new Exception("Work Log not deleted.");
		}
		
		return new ModelAndView("redirect:/home");
	}
	
	@RequestMapping(value="/edit_work_log", method = RequestMethod.POST)
	public ModelAndView  editWorkLog(@RequestParam("id") Integer id, RedirectAttributes attr){
		Work w = workLogService.findWork(id);
		WorkLog workLog = BeanMapper.map(w);
		attr.addFlashAttribute("workLog", workLog);
		return new ModelAndView("redirect:/home");
	}

}
