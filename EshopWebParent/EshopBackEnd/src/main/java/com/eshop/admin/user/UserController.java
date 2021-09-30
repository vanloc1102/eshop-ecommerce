package com.eshop.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eshop.common.entity.Role;
import com.eshop.common.entity.User;

@Controller
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("")
	public String listAll(Model model) {
		List<User> listAllUsers = service.listAll();
		model.addAttribute("listAllUsers", listAllUsers);
		return "users";
	}

	@GetMapping("/new")
	public String newUser(Model model) {
		List<Role> listRoles = service.listAllRoles();
		User user = new User();
		user.setEnable(true);
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("pageTitle", "Create New User");
		return "users_form";
	}

	@PostMapping("/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		service.save(user);

		redirectAttributes.addFlashAttribute("message", "The user has been save successfully.");
		return "redirect:/users";
	}

	@GetMapping("/edit/{id}")
	public String editUser(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		try {
			User user = service.getUserById(id);
			List<Role> listRoles = service.listAllRoles();
			model.addAttribute("listRoles", listRoles);
			model.addAttribute("user", user);
			model.addAttribute("pageTitle", "Edit User (ID:" + id + ")");
			return "users_form";
		} catch (UserNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			return "redirect:/users";
		}
	}
}
