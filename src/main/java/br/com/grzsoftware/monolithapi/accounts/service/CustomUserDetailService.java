package br.com.grzsoftware.monolithapi.accounts.service;

import br.com.grzsoftware.monolithapi.accounts.model.Account;
import br.com.grzsoftware.monolithapi.accounts.repository.AccountRepository;
import br.com.grzsoftware.monolithapi.roles.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public CustomUserDetailService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(account.getEmail())
                .password(account.getPassword())
                .roles(account.getRoles().stream().map(Role::getName).toArray(String[]::new))
                .disabled(!account.isEnabled())
                .build();
    }
}
