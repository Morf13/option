package com.hello.opa.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hello.opa.domain.Exercise;
import com.hello.opa.domain.User;
import com.hello.opa.repos.ExerciseRepository;

@Controller
public class MainController {

	@Autowired // This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private ExerciseRepository exerciseRepository;

	@Value("${upload.path}")
	private String uploadPath;

//	@GetMapping("/greeting")
//	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
//			Model model) {
//		model.addAttribute("name", name);
//		return "greeting";
//	}

	@GetMapping("/")
	public String greeting(Map<String, Object> model) {
		return "greeting";
	}

	@GetMapping("/main")
	public String main(Model model) {
		Iterable<Exercise> exercises = exerciseRepository.findAll();

		model.addAttribute("exercises", exercises);

		return "main";
	}

	@GetMapping("/addExercise")
	public String addEx(Model model) {
		model.addAttribute("exerciseForm", new Exercise());

		return "addExercise";
	}

	@PostMapping("/addExercise")
	public String add(@AuthenticationPrincipal User user, @RequestParam String title, 
			@RequestParam("file") MultipartFile file, Model model) throws IOException {

		Exercise exercise = new Exercise(title, user);

		
		
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
		exerciseRepository.save(exercise);

		Iterable<Exercise> exercises = exerciseRepository.findAll();

		model.addAttribute("exercises", exercises);

		return "main";
	}
//	@GetMapping("/add")
//	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
//			Model model) {
//		model.addAttribute("name", name);
//		return "addUser";
//	}

//	@PostMapping("/add")
//	public String add(@RequestParam String name,@RequestParam String email, Map<String, Object> model) {
//		User user = new User(name, email);
//		userRepository.save(user);
//		return "addUser";
//		

	// }
//	@PostMapping("/filter")
//	public String filter(@RequestParam String filter, Map<String, Object> model) {
//		List<User> users = (List<User>) userRepository.findByUsername(filter);
//		((Model) model).addAttribute("users", users);
//		return "filter";
//		
//		
	// }

}