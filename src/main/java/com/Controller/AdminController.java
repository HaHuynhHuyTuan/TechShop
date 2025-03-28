package com.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Service.AccountService;
import com.entity.Account;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AccountService accountService;

    public AdminController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account")
    public String manageAccounts(@RequestParam(required = false) String mode, 
                                 @RequestParam(required = false) String username, 
                                 Model model) {
        if ("view".equals(mode) || "edit".equals(mode)) {
            Account account = (username != null) ? accountService.getAccountByUsername(username) : new Account();
            model.addAttribute("account", account);
        }
        model.addAttribute("accounts", accountService.getAllAccounts());
        model.addAttribute("mode", mode);
        return "admin/account";
    }


    @PostMapping("/account/add")
    public String addAccount(@ModelAttribute Account account) {
        accountService.saveAccount(account);
        return "redirect:/admin/account";
    }

    @GetMapping("/account/edit/{username}")
    public String editAccount(@PathVariable String username, Model model) {
        model.addAttribute("account", accountService.getAccountByUsername(username));
        return "admin/edit-account";
    }

    @PostMapping("/account/update")
    public String updateAccount(@ModelAttribute Account account) {
        accountService.saveAccount(account);
        return "redirect:/admin/account";
    }

    @GetMapping("/account/delete/{username}")
    public String deleteAccount(@PathVariable String username) {
        accountService.deleteAccount(username);
        return "redirect:/admin/account";
    }
    
    @GetMapping("/account/view/{username}")
    public String viewAccount(@PathVariable String username, Model model) {
        model.addAttribute("account", accountService.getAccountByUsername(username));
        return "admin/view-account"; // Tạo file view-account.html
    }

}
