package com.Controller;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.DAO.AccountDAO;
import com.Service.CookieService;
import com.Service.ParamService;
import com.Service.SessionService;
import com.entity.Account;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private CookieService cookieService;

	@Autowired
	private ParamService paramService;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private AccountDAO accountRepository;

	// Hiển thị form đăng nhập
	@GetMapping("/login")
	public String loginForm() {
		return "Login"; // trỏ đến file account/login.html
	}

	@PostMapping("/login")
	public String loginSubmit(HttpSession session) {
		String username = paramService.getString("username", "");
		String password = paramService.getString("password", "");
		boolean remember = paramService.getBoolean("remember", false);

		// Kiểm tra trong database
		Optional<Account> userOpt = accountRepository.findByUsername(username);
		if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
			Account user = userOpt.get();

			// Lưu thông tin vào session
			sessionService.set("username", user.getUsername());
			sessionService.set("role", user.isRole()); // true = Admin, false = User

			// Xử lý cookie nhớ đăng nhập
			if (remember) {
				cookieService.add("user", username, 10);
			} else {
				cookieService.remove("user");
			}

			return user.isRole() ? "redirect:/admin/account" : "redirect:/home/index";
		}

		return "Login"; // Đăng nhập thất bại
	}

	// Hiển thị form đăng ký
	@GetMapping("/register")
	public String registerForm() {
		return "Register"; // trỏ đến file account/register.html
	}

	// Xử lý đăng ký có upload hình
	@PostMapping("/register")
	public ModelAndView registerSubmit(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("file") MultipartFile file) {
		ModelAndView mav = new ModelAndView("account/register");

		// Kiểm tra xem username đã tồn tại chưa
		if (accountRepository.existsById(username)) {
			mav.addObject("message", "Username already exists!");
			return mav;
		}

		try {
			// Lưu file ảnh đại diện
			File savedFile = ParamService.save(file, "uploads/");

			// Tạo tài khoản mới
			Account newUser = new Account();
			newUser.setUsername(username);
			newUser.setPassword(password);
			newUser.setPhoto(savedFile.getName()); // Lưu tên file ảnh
			newUser.setRole(false); // Mặc định đăng ký là User

			// Lưu vào database
			accountRepository.save(newUser);
			mav.addObject("message", "User registered successfully!");
		} catch (Exception e) {
			mav.addObject("message", "Registration failed: " + e.getMessage());
		}

		return mav;
	}

	  @GetMapping("/logout") //  Chắc chắn có mapping này
	    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
	        // Xóa session đăng nhập
	        sessionService.remove("username");

	        // Xóa cookie nếu có
	        Cookie cookie = new Cookie("user", null);
	        cookie.setMaxAge(0);
	        cookie.setPath("/"); // Xóa trên toàn hệ thống
	        response.addCookie(cookie);

	        // Thêm thông báo logout thành công
	        redirectAttributes.addFlashAttribute("message", "Bạn đã đăng xuất thành công!");

	        return "redirect:/account/login"; // Chuyển hướng về trang đăng nhập
	    }

}
