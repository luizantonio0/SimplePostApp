package com.agora.SimplePostApp.repository;

import com.agora.SimplePostApp.enums.UserRoles;
import com.agora.SimplePostApp.models.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Should return success")
    void findByEmailSuccess() {

        var user = new User("Luiz", "luiz@teste.com", "12345", UserRoles.USER);

        userRepository.save(user);

        var userFounded = userRepository.findByEmail(user.getEmail());

        assertNotNull(userFounded);

    }
}