package com.uet.gts.auth.repository;

import com.uet.gts.auth.model.entity.OAuth2Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuth2ClientRepository extends JpaRepository<OAuth2Client, String> {}
