package com.hello.opa.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hello.opa.domain.Exercise;
import com.hello.opa.domain.User;
import com.hello.opa.repos.ExerciseRepository;
import com.hello.opa.service.ExerciseService;
import com.hello.opa.service.MultipleChoice;
import com.hello.opa.service.MyCell;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Controller
public class ExerciseController {

	@Autowired 
	private ExerciseRepository exerciseRepository;

	@Value("${upload.path}")
	private String uploadPath;
	
	@Autowired
	ExerciseService exerciseService;
	
	@GetMapping("/")
	public String greeting(Map<String, Object> model) {
		return "greeting";
	}

	@GetMapping("/main")
	public String main(Model model,
			@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
		
		Page<Exercise> page = exerciseService.exerciseList(pageable);

		model.addAttribute("page", page);
		model.addAttribute("url", "/main");

		return "main";
	}

	@GetMapping("/addExercise")
	public String addEx(Model model) {
		Iterable<Exercise> exercises = exerciseRepository.findAll();

		model.addAttribute("exercises", exercises);
		

		return "addExercise";
	}

	@PostMapping("/addExercise")
	public String add(@AuthenticationPrincipal User user, @Valid Exercise exercise, BindingResult bindingResult,
			Model model, @RequestParam("file") MultipartFile file

	) throws IOException {
		exercise.setAuthor(user);
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = ControllerUtils.getErrors(bindingResult);
			model.mergeAttributes(errorMap);
			model.addAttribute("exercise", exercise);
		} else {
			saveFile(exercise, file);
			model.addAttribute("exercise", null);

			exerciseRepository.save(exercise);
		}

		return "redirect:/user-exercises/" + user.getId();

	}

	private void saveFile(@Valid Exercise exercise, @RequestParam("file") MultipartFile file) throws IOException {
		if (file != null && !file.getOriginalFilename().isEmpty()) {
			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			String uuidFile = UUID.randomUUID().toString();
			String resultFilename = uuidFile + "." + file.getOriginalFilename();

			file.transferTo(new File(uploadPath + "/" + resultFilename));

			exercise.setFileName(resultFilename);
		}
	}


	@GetMapping("/user-exercises/{user}")
	public String userMessges(@AuthenticationPrincipal User currentUser, @PathVariable User user, Model model,
			@RequestParam(required = false) Exercise exercise, @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Exercise> page = exerciseService.exerciseListForUser(pageable, currentUser, user);

		model.addAttribute("page", page);
		model.addAttribute("exercise", exercise);
		model.addAttribute("isCurrentUser", currentUser.equals(user));
		model.addAttribute("url", "/user-exercises/" + user.getId());

		return "userExercises";
	}

	@PostMapping("/user-exercises/{user}")
	public String updateMessage(@AuthenticationPrincipal User currentUser, @PathVariable Long user,
			@RequestParam("id") Exercise exercise, @RequestParam("title") String title,
			@RequestParam("file") MultipartFile file) throws IOException {
		if (exercise.getAuthor().equals(currentUser)) {
			if (!StringUtils.isEmpty(title)) {
				exercise.setTitle(title);
			}


			saveFile(exercise, file);

			exerciseRepository.save(exercise);
		}

		return "redirect:/user-exercises/" + user;
	}
	
	@GetMapping("/exercise/{exercise}")
	public String exercise(@PathVariable Exercise exercise, Model model) throws IOException {
		ArrayList<MultipleChoice> data = exerciseService.getExercise(exercise.getId());
		model.addAttribute("exercise", data);
		

		return "exercise";
	}
	
	@PostMapping("/exercise/{exercise}")
	public String checkExercise(@PathVariable Exercise exercise, Model model,@RequestParam Map<String, String> form) throws IOException {
		
		
		ArrayList<MultipleChoice> data = exerciseService.getExercise(exercise.getId());
		model.addAttribute("exercise", data);
		
		double result  = exerciseService.checkExercise(form, data);
		model.addAttribute("result", result);
		return "exercise";
	}
	
	
	}

