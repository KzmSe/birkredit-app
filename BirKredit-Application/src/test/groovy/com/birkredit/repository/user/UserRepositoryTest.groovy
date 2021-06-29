package com.birkredit.repository.user

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import spock.lang.Specification

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest extends Specification {

    @Autowired
    private UserRepository userRepository

    def "FindByUsername should return optional with data when user with given username is exist"() {
        given: "username"
        def username = "sanank"

        when: "calls method with username"
        def result = userRepository.findByUsername(username)

        then: "result should not be empty"
        result.isPresent()

        and: "username must be provided"
        result.get().username == username
    }

    def "FindByUsername should return empty optional when user with given username is not exist"() {
        given: "username"
        def username = "notexist"

        when: "calls method with username"
        def result = userRepository.findByUsername(username)

        then: "result should be empty"
        !result.isPresent()
    }
}
