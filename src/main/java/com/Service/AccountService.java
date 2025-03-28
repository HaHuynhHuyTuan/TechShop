package com.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DAO.AccountDAO;
import com.entity.Account;

@Service
public class AccountService {
    @Autowired
    private AccountDAO accountRepository;

    // Đăng ký tài khoản mới
    public boolean register(Account newAccount) {
        if (accountRepository.existsByUsername(newAccount.getUsername())) {
            return false; // Username đã tồn tại
        }
        accountRepository.save(newAccount);
        return true;
    }

    // Đăng nhập
    public Optional<Account> login(String username, String password) {
        Optional<Account> userOpt = accountRepository.findByUsername(username);
        return userOpt.filter(user -> user.getPassword().equals(password)); // Kiểm tra password
    }
    
    public AccountService(AccountDAO accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountByUsername(String username) {
        Optional<Account> account = accountRepository.findById(username);
        return account.orElse(null);
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccount(String username) {
        accountRepository.deleteById(username);
    }
}

