package com.euphoria_ecommerce.repository;

import com.euphoria_ecommerce.model.ResetPassword;
import com.euphoria_ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ResetPasswordRepository extends JpaRepository<ResetPassword, Integer> {

    @Query("select fp from ResetPassword fp where fp.otp = ?1 and fp.user = ?2")
    Optional<ResetPassword> findByOtpAndUser(Integer otp, User user);
}
